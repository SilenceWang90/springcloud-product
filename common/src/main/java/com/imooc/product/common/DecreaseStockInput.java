package com.imooc.product.common;

import lombok.Data;

/**
 * @Classname DecreaseStockInput
 * @Description 减库存对象
 * @Date 2019/11/20 16:31
 * @Created by wangpeng116
 */
@Data
public class DecreaseStockInput {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;
}
