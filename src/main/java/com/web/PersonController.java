package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WD42700 on 2018/7/18.
 */
@Controller
@RequestMapping("/person")
public class PersonController {


    @RequestMapping("/memberInfo")
    @ResponseBody
    public  String memberInfo() {
        return  "hello,world";
    }


    public void f(){


    }


}
