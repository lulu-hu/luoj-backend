package com.lulu.luoj.judge.strategy;

import com.lulu.luoj.judge.codesandbox.model.JudgeInfo;

/**
 * @author lulu
 * @version 1.0
 * @description TODO
 * @date 2024/7/1 15:09
 */
public interface JudgeStrategy {


    JudgeInfo doJudge(JudgeContext judgeContext);


}
