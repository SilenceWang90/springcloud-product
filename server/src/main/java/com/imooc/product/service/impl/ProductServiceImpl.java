package com.imooc.product.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.ProductException;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream().map(obj -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(obj, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
    }

    @Override

    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) throws ProductException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        //发送mq消息
        try {
            List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(obj -> {
                ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                BeanUtils.copyProperties(obj, productInfoOutput);
                return productInfoOutput;
            }).collect(Collectors.toList());
            amqpTemplate.convertAndSend("productInfo", objectMapper.writeValueAsString(productInfoOutputList));
        } catch (JsonProcessingException e) {
            log.error("消息转换json异常:{}", e);
        }
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) throws ProductException {
        List<ProductInfo> productInfoList = Lists.newArrayList();
        decreaseStockInputList.forEach(obj -> {
            Optional<ProductInfo> opt = productInfoRepository.findById(obj.getProductId());
            //查询不到结果，抛出异常，提示产品不存在
            opt.orElseThrow(() -> new ProductException(ResultEnum.PRODUCT_NOT_EXIST));
            //如果产品存在则减少库存
            opt.ifPresent(productInfo -> {
                Integer result = productInfo.getProductStock() - obj.getProductQuantity();
                //如果库存不够则提示库存不足，不作任何操作
                Optional<Integer> optional = Optional.ofNullable(result);
                optional.orElseThrow(() -> new ProductException(ResultEnum.PRODUCT_STOCK_ERROR));
                productInfo.setProductStock(result);
                productInfoRepository.save(productInfo);
                productInfoList.add(productInfo);
            });
        });
        return productInfoList;
    }
}
