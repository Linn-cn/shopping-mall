package com.shopping.mall.util;

import java.io.Serializable;
import java.util.List;

/**
 * @program: FreeMarkeDemo
 * @description: 分页表格数据返回
 * @author: 南街
 * @create: 2019-01-02 15:31
 **/
public class AjaxResultPage<T> implements Serializable {

    //状态码
    private int resultCode;

    //提示消息
    private String message;

    //总条数
    private long count;

    //表格数据
    private List<T> data;

    public int getResultCode() {
        return resultCode;
    }

    public AjaxResultPage<T> setResultCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResultPage<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getCount() {
        return count;
    }

    public AjaxResultPage<T> setCount(long count) {
        this.count = count;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public AjaxResultPage<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AjaxResultPage{");
        sb.append("resultCode=").append(resultCode);
        sb.append(", message='").append(message).append('\'');
        sb.append(", count=").append(count);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
