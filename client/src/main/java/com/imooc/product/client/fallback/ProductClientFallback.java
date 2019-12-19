package com.imooc.product.client.fallback;

import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;

import java.util.List;

/**
 * @Classname ProductClientFallback
 * @Description TODO
 * @Date 2019/12/19 11:31
 * @Created by wangpeng116
 */
//@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {

    }
}
