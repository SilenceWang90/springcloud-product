package com.imooc.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.product.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    //json转换时更名为name
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductInfoVO> productInfoVOList;
}
