package com.lulu.luoj.judge.codesandbox;

import com.lulu.luoj.judge.codesandbox.Impl.ExampleCodeSandbox;
import com.lulu.luoj.judge.codesandbox.Impl.RemoteCodeSandbox;
import com.lulu.luoj.judge.codesandbox.Impl.ThirdPartyCodeSandbox;

/**
 * @author lulu
 * @version 1.0
 * @description 代码沙箱工厂 （根据字符串参数创建指定的代码沙箱实例）
 * @date 2024/6/30 16:26
 */
public class CodeSandboxFactory {

    /**
     *  创建代码沙箱实例
     * @param type
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        if (type == null) {
            return new ExampleCodeSandbox();
        }
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
