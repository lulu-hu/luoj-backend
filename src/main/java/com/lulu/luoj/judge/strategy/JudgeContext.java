package com.lulu.luoj.judge.strategy;

import com.lulu.luoj.model.dto.question.JudgeCase;
import com.lulu.luoj.judge.codesandbox.model.JudgeInfo;
import com.lulu.luoj.model.entity.Question;
import com.lulu.luoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author lulu
 * @version 1.0
 * @description 上下文（用于定义在策略中传递的参数）
 * @date 2024/7/1 15:12
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private  List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;

}
