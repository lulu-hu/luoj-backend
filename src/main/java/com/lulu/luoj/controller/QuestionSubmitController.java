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
@Deprecated
public class QuestionSubmitController {



}
