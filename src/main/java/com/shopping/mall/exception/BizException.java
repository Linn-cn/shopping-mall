package com.shopping.mall.exception;

/**
 * @program: shopping-mall
 * @classname: BizException
 * @description: 业务异常
 * @author: 朱林
 * @create: 2019-11-26 14:48
 **/
public class BizException extends RuntimeException {

    /**
     * 默认关闭栈追踪信息和挂起参数
     * @param message
     * @return
     * @date 2019/11/9 9:55
     */
    public BizException(String message) {
        super(message, null, false, false);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause, false, false);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
