package com.shopping.mall.config;

import com.shopping.mall.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: shopping-mall
 * @classname: MallWebMvcConfigure
 * @description: 自定义MVC配置
 * @author: 朱林
 * @create: 2019-11-26 16:07
 **/
@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${upload.sqlUserImg}")
    private String SQL_USER_IMG;

    @Value("${upload.serverUserImg}")
    private String SERVER_USER_IMG;

    @Value("${upload.sqlCommodityImg}")
    private String SQL_COMMODITY_IMG;

    @Value("${upload.serverCommodityImg}")
    private String SERVER_COMMODITY_IMG;

    @Value("${upload.sqlDetailImg}")
    private String SQL_DETAIL_IMG;

    @Value("${upload.serverDetailImg}")
    private String SERVER_DETAIL_IMG;

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/shoppCart/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/comment/add")
                .addPathPatterns("/gotoCreateOrder")
                .addPathPatterns("/gotoUpdateUser")
                .addPathPatterns("/user/uploadImg");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(SQL_USER_IMG + "**")
                .addResourceLocations("file:" + SERVER_USER_IMG);
        registry.addResourceHandler(SQL_COMMODITY_IMG + "**")
                .addResourceLocations("file:" + SERVER_COMMODITY_IMG);
        registry.addResourceHandler(SQL_DETAIL_IMG + "**")
                .addResourceLocations("file:" + SERVER_DETAIL_IMG);
    }
}
