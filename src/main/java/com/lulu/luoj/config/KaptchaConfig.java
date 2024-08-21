package com.lulu.luoj.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
@description: KaptchaConfig
 */

@Configuration
public class KaptchaConfig {

    @Bean
    public Producer producer() {
        Properties properties = new Properties();
        // 设置图片边框
        properties.setProperty("kaptcha.border", "no");
        // 设置图片边框为蓝色
        // properties.setProperty("kaptcha.border.color", "white");
        // 背景颜色渐变开始
        properties.put("kaptcha.background.clear.from", "white");
        // 背景颜色渐变结束
        properties.put("kaptcha.background.clear.to", "white");
        // 字体颜色
        properties.put("kaptcha.textproducer.font.color", "5,115,217");
        // 文字间隔
        properties.put("kaptcha.textproducer.char.space", "10");
        // 干扰线颜色配置
        properties.put("kaptcha.noise.color", "5,115,217");
        // 如果需要去掉干扰线
        // properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        // 字体
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        // 图片宽度
        properties.setProperty("kaptcha.image.width", "200");
        // 图片高度
        properties.setProperty("kaptcha.image.height", "50");
        // 从哪些字符中产生
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrsduvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // 字符个数
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }

}
