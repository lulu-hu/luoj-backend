package com.lulu.luoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lulu.luoj.model.dto.question.QuestionQueryRequest;
import com.lulu.luoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lulu.luoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.lulu.luoj.model.entity.Question;
import com.lulu.luoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lulu.luoj.model.entity.User;
import com.lulu.luoj.model.vo.QuestionSubmitVO;
import com.lulu.luoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param request
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
