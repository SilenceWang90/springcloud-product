package com.imooc.product.service;

import com.imooc.product.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    //in方法实现
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
