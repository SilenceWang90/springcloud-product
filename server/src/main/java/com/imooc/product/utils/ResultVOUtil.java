package com.imooc.product.utils;

import com.imooc.product.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("成功");
        resultVO.setCode(0);
        resultVO.setData(data);
        return resultVO;
    }
}
