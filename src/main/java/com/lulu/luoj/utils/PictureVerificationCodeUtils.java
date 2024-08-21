package com.lulu.luoj.utils;

import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @description: 图片验证码工具类
 */
@Slf4j
@Component
@AllArgsConstructor
public class PictureVerificationCodeUtils {
    @Autowired
    private Producer producer;

    private final ConcurrentHashMap<String, String> verificationCodes = new ConcurrentHashMap<>();


    /**
     * 生成图片验证码
     * key =verifyCodeKey ,这个key对应code 放入redis。 生成后给前端返回key和base64
     */
    public Map<String, String> create() {
        Map<String, String> resultMap = new HashMap<>();
        try {
            String verifyCode = producer.createText();
            log.info("verifyCode;{}", verifyCode);
            BufferedImage bufferedImage = producer.createImage(verifyCode);
            // 将图片转换陈字符串给前端
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", stream);
            String base64 = Base64.getEncoder().encodeToString(stream.toByteArray());
            stream.flush();
            stream.close();
            // 存到缓存中
            String lowerCase = verifyCode.toLowerCase();
            String key = UUID.randomUUID().toString().replace("-", "");
            verificationCodes.put(key, lowerCase);

            resultMap.put("verifyCodeKey", key);
            resultMap.put("base64String", base64);
        } catch (IOException e) {
            log.error("生成图片验证码异常： {}", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 校验图片验证码
     *
     * @param verifyCode    图片验证码code
     * @param verifyCodeKey 图片验证码key
     */
    public boolean verify(String verifyCode, String verifyCodeKey) {
        boolean verifyResult = false;
        String lowerCase = verifyCode.toLowerCase();
        String code = verificationCodes.get(verifyCodeKey);
        if (StringUtils.isEmpty(code)) {
            return verifyResult;
        }
        log.info("verifyCode:{} verifyCodeKey:{}, cacheCode:{}", verifyCode, verifyCodeKey, code);
        if (StringUtils.equals(lowerCase, code)) {
            verifyResult = true;
        }
        // 删除已验证的验证码
        verificationCodes.remove(verifyCodeKey);
        return verifyResult;
    }
}
