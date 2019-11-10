package com.imooc.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "PRODUCT_CATEGORY")
@Data
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    //类目名称
    private String categoryName;
    //类目编号
    private Integer categoryType;

    private String createTime;

    private String updateTime;
}
