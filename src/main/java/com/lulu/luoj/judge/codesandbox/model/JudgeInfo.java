package com.lulu.luoj.judge.codesandbox.model;

import lombok.Data;

/**
 * @author lulu
 * @version 1.0
 * @description 判题信息
 * @date 2024/6/23 14:33
 */
@Data
public class JudgeInfo {
    /**
     *  程序执行消息
     */
    private String message;

    /**
     *  消耗内存
     */
    private Long memory;

    /**
     *  消耗时间（KB）
     */
    private Long time;
}
