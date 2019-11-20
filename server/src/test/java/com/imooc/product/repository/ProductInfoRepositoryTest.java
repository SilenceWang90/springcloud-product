package com.imooc.product.repository;

import com.imooc.product.dataobject.ProductInfo;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findbyProductStatus() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findByProductIdIn() {
        List<String> productIdList = Lists.newArrayList();
        productIdList.add("157875196366160022");
        productIdList.add("157875227953464068");
        List<ProductInfo> list = productInfoRepository.findByProductIdIn(productIdList);
        Assert.assertEquals(2, list.size());
    }
}