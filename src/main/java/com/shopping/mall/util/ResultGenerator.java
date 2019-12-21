package com.shopping.mall.util;

import com.shopping.mall.constant.HttpStatusConstants;

/**
 * 响应结果生成工具
 *
 * @author nanjie
 */
public class ResultGenerator {

    private ResultGenerator(){}

    public static <T> Result<T> getResultByHttp(HttpStatusConstants constants, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(constants.getStatus());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> getResultByHttp(HttpStatusConstants constants, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(constants.getStatus());
        result.setMessage(constants.getContent());
        result.setData(data);
        return result;
    }

    public static Result<String> getResultByHttp(HttpStatusConstants constants, String msg) {
        Result<String> result = new Result<>();
        result.setResultCode(constants.getStatus());
        result.setMessage(msg);
        return result;
    }

    /**
     * @Description: 根据传入的常量返回对应result
     * @Param: [constants] http状态
     * @return: com.zhulin.blog.dto.Result
     * @date: 2019/8/24 16:25
     */
    public static Result<String> getResultByHttp(HttpStatusConstants constants) {
        Result<String> result = new Result<>();
        result.setResultCode(constants.getStatus());
        result.setMessage(constants.getContent());
        return result;
    }
}
