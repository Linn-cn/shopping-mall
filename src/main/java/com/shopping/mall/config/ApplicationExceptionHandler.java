package com.shopping.mall.config;

import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.exception.BizException;
import com.shopping.mall.util.Result;
import com.shopping.mall.util.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @program: FruitSales
 * @description: 全局异常统一处理类
 * @author: 南街
 * @create: 2019-01-17 10:24
 **/
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * 方法参数效验
     *
     * @param ex
     * @return com.zhulin.ascentweb.dto.Result
     * @date 2019/9/8 9:58
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result BindException(BindException ex) {
        String message = Optional
                .ofNullable(ex.getBindingResult().getFieldError().getDefaultMessage())
                .orElseGet(()->"参数错误");
/*        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });*/
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, message);
    }


    /**
     * 方法参数效验
     *
     * @param ex
     * @return com.zhulin.ascentweb.dto.Result
     * @date 2019/9/8 9:58
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = Optional
                .ofNullable(ex.getBindingResult().getFieldError().getDefaultMessage())
                .orElseGet(()->"参数错误");
/*        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });*/
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, message);
    }

    /**
     * resultful风格参数验证
     * @param e
     * @return com.shopping.mall.util.Result
     * @date 2019/11/27 12:45
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Result ConstraintViolationException(ConstraintViolationException e) {
        List<String> messageList = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessageTemplate());
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST, StringUtils.join(messageList,";"));
    }

    /**
     * @param e
     * @return com.changda.flea.common.util.Result<java.lang.String>
     * @RequestParam 注解请求参数有误
     * @date 2019/11/8 20:09
     */
/*
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result<String> MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST);
    }
*/

    /**
     * @param e
     * @return com.changda.flea.common.util.Result<java.lang.String>
     * @RequestParam 业务异常
     * @date 2019/11/8 20:09
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result<String> BizException(BizException e) {
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
