package com.imooc.product.config;

import com.imooc.product.util.HttpServletUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 功能描述:
 *
 * @author: xinghao
 * @create: 2019-01-24 14:43
 */
@Component
@Configuration
@Slf4j
public class FeignHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = HttpServletUtil.getRequest();
        log.info("请求信息：{}", request);
        if (request != null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            log.info("请求头信息：{}", headerNames);
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
            }
        }
    }
}
