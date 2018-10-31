package com.queue;

import java.util.concurrent.*;

/**
 * Created by WD42700 on 2018/10/31.
 */
public class TestLinkedQueue {

    public static void main(String[] args) {

    final CountDownLatch latch = new CountDownLatch (10000);

        try {
            final   ConcurrentLinkedQueue<Integer>  queue  = new ConcurrentLinkedQueue();
            for (int  i=1;i<=10000;i++){
                queue.offer(i);
            }

            ExecutorService executorService = Executors.newCachedThreadPool();
            for (int i=0;i<=1000;i++){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            Integer i = queue.poll();
                            if(i == null){
                                break;
                            }
                            System.out.println(Thread.currentThread()+"  处理 "+i);
                            latch.countDown();;
                        }
                    }
                });
            }

            System.out.println("~~~~~~~~~~~~~~~开始~~~~~~~~~~~~~~");
            latch.await();
            System.out.println("~~~~~~~~~~~~~~~结束~~~~~~~~~~~~~~");
        }catch (Exception  e){
            e.printStackTrace();
        }






    }

}
