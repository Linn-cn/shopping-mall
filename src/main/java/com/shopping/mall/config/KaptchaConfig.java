package com.shopping.mall.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.shopping.mall.constant.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.image.width", "145");
        properties.put("kaptcha.image.height", "38");
        properties.put("kaptcha.textproducer.font.size", "30");
        properties.put("kaptcha.session.key", ApplicationConstants.LOGIN_VERIFY_CODE_KEY);
        properties.put("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}