package com.imooc.product.service.impl;

import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.service.ProductService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Classname ProductServiceImplTest
 * @Description ProductServiceImplTest
 * @Date 2019/11/20 10:19
 * @Created by wangpeng116
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void findList() {
        List<String> productIdList = Lists.newArrayList();
        productIdList.add("157875196366160022");
        productIdList.add("157875227953464068");
        List<ProductInfo> findList = productService.findList(productIdList);
        Assert.assertEquals(2, findList.size());
    }
}