package com.lulu.luoj.service;

import com.lulu.luoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lulu.luoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lulu.luoj.model.entity.User;

/**
* @author lulu
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-06-22 17:08:30
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
