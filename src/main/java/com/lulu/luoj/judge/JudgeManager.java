package com.lulu.luoj.judge;

import com.lulu.luoj.judge.strategy.DefaultJudgeStrategy;
import com.lulu.luoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.lulu.luoj.judge.strategy.JudgeContext;
import com.lulu.luoj.judge.strategy.JudgeStrategy;
import com.lulu.luoj.model.dto.questionsubmit.JudgeInfo;
import com.lulu.luoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @author lulu
 * @version 1.0
 * @description 判题管理（简化调用）
 * @date 2024/7/1 15:44
 */
@Service
public class JudgeManager {


    /**
     *  判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
