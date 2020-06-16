package com.lc.tests;

import com.lc.core.StrFormatter;
import com.lc.lang.UConsole;
import com.lc.utils.UDateUtil;

import java.util.Date;

public class UConsoleTest {
    public static void main(String[] args) {
        UConsole.log(1);
        UConsole.log("5213344");
        UConsole.log(new Date());
        UConsole.log(UDateUtil.currentStr());
        UConsole.log("我是{},今年{},忍!", "LC", 18);

        UConsole.print("print");
        UConsole.print("我是{}", "print");
        UConsole.log();
        UConsole.print(StrFormatter.format("我是{},今年{},忍!", "LC2", 19));

        UConsole.log();
        UConsole.error("error");
        UConsole.error("我是{}", "error");
    }
}
