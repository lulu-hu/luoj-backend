package com.lulu.luoj.judge.codesandbox.Impl;

import com.lulu.luoj.judge.codesandbox.CodeSandbox;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lulu.luoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author lulu
 * @version 1.0
 * @description 第三方代码沙箱 (调用网上现成的代码沙箱)
 * @date 2024/6/30 16:05
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
