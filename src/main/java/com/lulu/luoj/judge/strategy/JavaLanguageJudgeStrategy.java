package com.lulu.luoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.lulu.luoj.model.dto.question.JudgeCase;
import com.lulu.luoj.model.dto.question.JudgeConfig;
import com.lulu.luoj.judge.codesandbox.model.JudgeInfo;
import com.lulu.luoj.model.entity.Question;
import com.lulu.luoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.Optional;

import static org.checkerframework.checker.nullness.Opt.orElse;

/**
 * @author lulu
 * @version 1.0
 * @description Java 程序的判题策略
 * @date 2024/7/1 15:13
 */
public class JavaLanguageJudgeStrategy implements JudgeStrategy {


    /**
     *  执行判题
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L);
        Long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);
        if(outputList.size() != inputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 依次判断每一项输出和输入是否相等
        for ( int i = 0; i < judgeCaseList.size(); i++){
            JudgeCase judgeCase = judgeCaseList.get(i);
            if(!judgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        // 判断题目限制
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        Long needTimeLimit = judgeConfig.getTimeLimit();
        if(memory > needMemoryLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if(time > needTimeLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
