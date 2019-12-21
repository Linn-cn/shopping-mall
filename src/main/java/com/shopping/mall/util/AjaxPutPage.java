package com.shopping.mall.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;

/**
 * @program: FreeMarkeDemo
 * @description: 分页查询[带条件]输入映射
 * @author: 南街
 * @create: 2019-01-03 11:40
 **/
public class AjaxPutPage implements Serializable {

    private Integer page;   //当前页码

    private Integer limit;  //每页显示

    private Integer start;  //从多少开始

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        this.start = (this.page - 1) * this.limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }


    /**
     * 将符合Layui的格式转成mybtais-plus分页的page
     * @return
     */
    public <T> Page<T> putPageToPage(){
        return new Page<T>(this.page,this.limit);
    }

    @Override
    public String toString() {
        return "AjaxPutPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                '}';
    }
}
