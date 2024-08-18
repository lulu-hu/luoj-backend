package com.lulu.luoj.aop;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lulu.luoj.common.ErrorCode;
import com.lulu.luoj.exception.BusinessException;
import com.lulu.luoj.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @author lulu
 * @version 1.0
 * @description TODO
 * @date 2024/8/18 16:37
 */
@Slf4j
@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
        // 1. 检验token的有效性
            return JwtUtils.validateToken(token);
        } catch (SignatureVerificationException e) {
            log.error("无效签名 : {}", e.getMessage());
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "无效签名");
        } catch (TokenExpiredException e) {
            log.error("token已经过期: {}", e.getMessage());
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "token已经过期");
        } catch (AlgorithmMismatchException e) {
            log.error("算法不一致: {}", e.getMessage());
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "算法不一致");
        } catch (Exception e) {
            log.error("token无效: {}", e.getMessage());
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "token无效");
        }
    }
}
