package com.imooc.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname ServerClient
 * @Description server
 * @Date 2019/12/19 15:38
 * @Created by wangpeng116
 */
@FeignClient(name = "product")
@RequestMapping("/server")
public interface ServerClient {
    @RequestMapping("/msg")
    String msg();
}
