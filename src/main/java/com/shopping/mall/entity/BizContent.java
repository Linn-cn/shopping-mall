package com.shopping.mall.entity;

/**
 * @program: shopping-mall
 * @classname: BizContent
 * @description: 支付宝支付接口请求参数
 * @author: 朱林
 * @create: 2019-12-01 17:30
 **/
public class BizContent {

    /**
     * 商品订单号
     */
    private String out_trade_no;
    /**
     * 订单总金额
     */
    private String total_amount;
    /**
     * 订单标题
     */
    private String subject;
    /**
     * 订单描述
     */
    private String body;
    /**
     * 超时时间
     */
    private String timeout_express;
    /**
     * 销售产品号
     */
    private String product_code;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public BizContent setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public BizContent setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public BizContent setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        return body;
    }

    public BizContent setBody(String body) {
        this.body = body;
        return this;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public BizContent setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
        return this;
    }

    public String getProduct_code() {
        return product_code;
    }

    public BizContent setProduct_code(String product_code) {
        this.product_code = product_code;
        return this;
    }

    @Override
    public String toString() {
        return "BizContent{" +
                "out_trade_no='" + out_trade_no + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", timeout_express='" + timeout_express + '\'' +
                ", product_code='" + product_code + '\'' +
                '}';
    }
}
