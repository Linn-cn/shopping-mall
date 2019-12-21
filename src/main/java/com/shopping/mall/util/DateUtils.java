package com.shopping.mall.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: shopping-mall
 * @classname: DateUtils
 * @description:
 * @author: 朱林
 * @create: 2019-11-26 15:52
 **/
public class DateUtils {

    private DateUtils(){}

    /**
     * 获取本地时间,转成SQL格式存储
     * @param
     * @return java.sql.Timestamp
     * @date 2019/11/26 15:53
     */
    public static Date getLocalDateTime(){
        return new Timestamp(new Date().getTime());
    }

    /**
     * 返回当前时间+四位随机数
     * @param
     * @return java.lang.Integer
     * @date 2019/12/1 14:35
     */
    public static String getRandomNumber(){
        String number = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        Integer random = (int)(Math.random() * 10000);
        return number + random;
    }
}
