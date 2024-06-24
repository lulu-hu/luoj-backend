package com.lulu.luoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lulu.luoj.annotation.AuthCheck;
import com.lulu.luoj.common.BaseResponse;
import com.lulu.luoj.common.ErrorCode;
import com.lulu.luoj.common.ResultUtils;
import com.lulu.luoj.constant.UserConstant;
import com.lulu.luoj.exception.BusinessException;
import com.lulu.luoj.model.dto.question.QuestionQueryRequest;
import com.lulu.luoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lulu.luoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.lulu.luoj.model.entity.Question;
import com.lulu.luoj.model.entity.QuestionSubmit;
import com.lulu.luoj.model.entity.User;
import com.lulu.luoj.model.vo.QuestionSubmitVO;
import com.lulu.luoj.service.QuestionSubmitService;
import com.lulu.luoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                         HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表（除管理员外，普通用户只能看到非答案，提交代码等公开信息）
     *
     * @param questionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionQueryRequest.getCurrent();
        long size = questionQueryRequest.getPageSize();
        // 从数据中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
    }

}
