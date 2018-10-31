package com.fork_join;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by WD42700 on 2018/10/29.
 */
public class Main {

    private static int[] genArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(500);
        }
        return array;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 下面以一个有返回值的大任务为例，介绍一下RecursiveTask的用法。
         大任务是：计算随机的100个数字的和。
         小任务是：每次只能20个数值的和。
         */
        int[] array = genArray();

        int total = 0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }
        System.out.println("目标和：" + total);

        // 1. 创建任务
        SumTask sumTask = new SumTask(array, 0, array.length - 1);

        // 2. 创建线程池
        // 设置并行计算的个数
        int processors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(processors * 2);

        // 3. 提交任务到线程池
        forkJoinPool.submit(sumTask);
//        forkJoinPool.shutdown();

        long begin = System.currentTimeMillis();
        // 4. 获取结果
        Integer result = sumTask.get();// wait for result
        long end = System.currentTimeMillis();
        System.out.println(String.format("结果 %s ，耗时 %sms", result, end - begin));

        if (result == total) {
            System.out.println("测试成功");
        } else {
            System.out.println("fork join 使用失败！！！！");
        }
    }

}
