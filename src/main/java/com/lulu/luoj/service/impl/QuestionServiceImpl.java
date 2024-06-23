package com.lulu.luoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lulu.luoj.model.entity.Question;
import com.lulu.luoj.service.QuestionService;
import com.lulu.luoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author lulu
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-06-22 17:07:15
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




