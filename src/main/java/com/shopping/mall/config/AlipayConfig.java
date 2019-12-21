package com.shopping.mall.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * 类名：AlipayConfig
 * 功能：基础配置类
 * 详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static final String app_id = "";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static final String merchant_private_key = "";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static final String alipay_public_key = "";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String notify_url = "http://localhost:8080/alipay/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String return_url = "http://localhost:8080/alipay/alipayReturnNotice";

    // 签名方式
    public static final String sign_type = "RSA2";

    // 字符编码格式
    public static final String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


    /**
     * 获得初始化的AlipayClient
     *
     * @return com.alipay.api.AlipayClient
     */
    public static AlipayClient getDefaultAlipayClient() {
        return new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
    }

    /**
     * 设置请求参数
     *
     * @param
     * @return com.alipay.api.request.AlipayTradePagePayRequest
     * @date 2019/12/1 16:57
     */
    public static AlipayTradePagePayRequest getAlipayTradePagePayRequest() {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        return alipayRequest;
    }

}

