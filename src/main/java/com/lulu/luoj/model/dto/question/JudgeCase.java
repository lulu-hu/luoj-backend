package com.lulu.luoj.model.dto.question;

import lombok.Data;

/**
 * @author lulu
 * @version 1.0
 * @description TODO
 * @date 2024/6/23 14:33
 */
@Data
public class JudgeCase {
    /**
     *  输入用例
     */
    private String input;

    /**
     *  输出用例
     */
    private String output;
}
