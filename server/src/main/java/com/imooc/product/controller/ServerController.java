package com.imooc.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/server")
@Slf4j
public class ServerController {
    @RequestMapping("/msg")
    public String msg(HttpServletRequest httpServletRequest) {
        log.info("请求头：{}",httpServletRequest.getHeader("token"));
        log.info("cookie：{}",httpServletRequest.getCookies());
        return "this is product msg";
    }
}
