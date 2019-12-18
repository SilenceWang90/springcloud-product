package com.imooc.product.fallback;

import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname ProductClientFallBack
 * @Description 降级服务
 * @Date 2019/12/18 18:37
 * @Created by wangpeng116
 */
@Component
public class ProductClientFallBack implements ProductClient {
    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {

    }
}
