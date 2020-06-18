package com.lc.tests;

import com.lc.lang.Validator;

public class ValidatorTest {
    public static void main(String[] args) {
        Validator.validateEmail("ahlc@sina.cn", "邮箱格式错误"); // 对的邮箱格式直接通过字段验证器校验

        Validator.validateEmail("ahlc", "邮箱格式错误");
        //Exception in thread "main" com.lc.exception.ValidateException: 邮箱格式错误
        //at com.lc.lang.Validator.validateEmail(Validator.java:542)
        //at com.lc.tests.ValidatorTest.main(ValidatorTest.java:9)
    }
}
