package com.shopping.mall.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.mall.constant.ApplicationConstants;
import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.entity.User;
import com.shopping.mall.util.Result;
import com.shopping.mall.util.ResultGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program: shopping-mall
 * @classname: UserLoginInterceptor
 * @description: 判断是否登录放行拦截器
 * @author: 朱林
 * @create: 2019-11-26 17:04
 **/
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        if (user == null){
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Result result = ResultGenerator.getResultByHttp(HttpStatusConstants.UNAUTHORIZED,"请先登录！");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(result));
            System.out.println(new ObjectMapper().writeValueAsString(result));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
