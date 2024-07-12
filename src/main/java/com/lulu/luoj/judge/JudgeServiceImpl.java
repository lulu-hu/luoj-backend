package com.lulu.luoj.judge;

import cn.hutool.json.JSONUtil;
import com.lulu.luoj.common.ErrorCode;
import com.lulu.luoj.exception.BusinessException;
import com.lulu.luoj.judge.codesandbox.CodeSandbox;
import com.lulu.luoj.judge.codesandbox.CodeSandboxFactory;
import com.lulu.luoj.judge.codesandbox.CodeSandboxProxy;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.lulu.luoj.judge.strategy.JudgeContext;
import com.lulu.luoj.model.dto.question.JudgeCase;
import com.lulu.luoj.judge.codesandbox.model.JudgeInfo;
import com.lulu.luoj.model.entity.Question;
import com.lulu.luoj.model.entity.QuestionSubmit;
import com.lulu.luoj.model.enums.QuestionSubmitStatusEnum;
import com.lulu.luoj.service.QuestionService;
import com.lulu.luoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lulu
 * @version 1.0
 * @description TODO
 * @date 2024/7/1 14:05
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;


    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        // 2) 如果题目提交状态不为等待中，就不用重复执行了
        if(!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }
        // 3) 更改判题（题目提交）的状态为"判题中",防止重复执行
        QuestionSubmit updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新错误");
        }
        // 4) 调用沙箱，获取到判题结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5) 根据沙箱的执行结果，设置题目的判题信息和状态
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeContext.setJudgeCaseList(judgeCaseList);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 修改数据库中的判题结果
        updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        updateQuestionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新错误");
        }
        return questionSubmitService.getById(questionId);
    }
}
