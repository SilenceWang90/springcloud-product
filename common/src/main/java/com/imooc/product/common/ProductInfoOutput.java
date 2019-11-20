package com.imooc.product.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname ProductInfoOutput
 * @Description 产品信息输出对象
 * @Date 2019/11/20 16:24
 * @Created by wangpeng116
 */
@Data
public class ProductInfoOutput {
    //id
    private String productId;
    //名称
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //状态：0正常；1下架
    private Integer productStatus;
    //类目编号
    private Integer categoryType;
}
