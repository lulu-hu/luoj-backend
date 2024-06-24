package com.lulu.luoj.model.dto.question;

import lombok.Data;

/**
 * @author lulu
 * @version 1.0
 * @description 题目配置
 * @date 2024/6/23 14:33
 */
@Data
public class JudgeConfig {
    /**
     *  时间限制
     */
    private Long timeLimit;

    /**
     *  内存限制
     */
    private Long memoryLimit;

    /**
     *  堆栈限制
     */
    private Long stackLimit;
}
