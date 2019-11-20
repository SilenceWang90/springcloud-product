package com.imooc.product.service.impl;

import com.imooc.product.DTO.CartDTO;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.ProductException;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) throws ProductException {
        cartDTOList.forEach(obj -> {
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
            });
        });
    }
}
