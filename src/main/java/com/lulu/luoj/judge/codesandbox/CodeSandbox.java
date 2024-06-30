package com.lulu.luoj.judge.codesandbox;

import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author lulu
 * @version 1.0
 * @description 代码沙箱接口定义
 * @date 2024/6/30 15:52
 */
public interface CodeSandbox {

    /**
     *  执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
