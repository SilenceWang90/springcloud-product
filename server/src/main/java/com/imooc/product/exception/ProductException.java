package com.imooc.product.exception;

import com.imooc.product.enums.ResultEnum;

/**
 * @Classname ProductException
 * @Description 产品异常类
 * @Date 2019/11/20 11:30
 * @Created by wangpeng116
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
