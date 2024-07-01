package com.lulu.luoj.judge.codesandbox.Impl;
import java.util.List;
import com.lulu.luoj.model.dto.questionsubmit.JudgeInfo;

import com.lulu.luoj.judge.codesandbox.CodeSandbox;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.lulu.luoj.model.enums.JudgeInfoMessageEnum;
import com.lulu.luoj.model.enums.QuestionSubmitStatusEnum;

/**
 * @author lulu
 * @version 1.0
 * @description 示例代码沙箱 (为了跑通流程)
 * @date 2024/6/30 16:05
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        List<String> inputList = executeCodeRequest.getInputList();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
