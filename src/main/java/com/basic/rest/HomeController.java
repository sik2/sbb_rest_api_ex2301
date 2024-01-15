package com.basic.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "안녕하세요";
    }

}
