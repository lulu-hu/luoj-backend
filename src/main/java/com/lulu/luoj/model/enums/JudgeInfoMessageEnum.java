package com.lulu.luoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息消息枚举
 *
 */
public enum JudgeInfoMessageEnum {

    ACCEPTED("成功", "Accepted"),
    WAIT_ING("等待中", "Waiting"),
    WRONG_ANSWER("答案错误", "Wrong Answer"),
    TIME_LIMIT_EXCEEDED("超出时间限制", "Time Limit Exceeded"),
    MEMORY_LIMIT_EXCEEDED("超出内存限制", "Memory Limit Exceeded"),
    RUNTIME_ERROR("运行时错误", "Runtime Error"),
    SYSTEM_ERROR("系统错误", "System Error"),
    COMPILE_ERROR("编译错误", "Compile Error"),
    SUBMITTED_FAILED("提交失败", "Submitted Failed");



    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
