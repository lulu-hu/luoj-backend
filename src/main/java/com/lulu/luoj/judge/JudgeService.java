package com.lulu.luoj.judge;

import com.lulu.luoj.model.entity.QuestionSubmit;


/**
 * @author lulu
 * @version 1.0
 * @description 判题服务
 * @date 2024/7/1 13:57
 */
public interface JudgeService {

    /**
     *  判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);

}
