package com.imooc.product.client;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Classname ProductClient
 * @Description ProductClient
 * @Date 2019/11/20 16:45
 * @Created by wangpeng116
 */
@FeignClient(name = "product")
@RequestMapping("/product")
public interface ProductClient {
    @RequestMapping("/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @RequestMapping("/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);


}
