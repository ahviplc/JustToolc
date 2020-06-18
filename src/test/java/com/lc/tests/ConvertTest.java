package com.lc.tests;

import com.lc.core.convert.Convert;
import com.lc.lang.UConsole;

public class ConvertTest {
    public static void main(String[] args) {
        UConsole.log(Convert.numberToChinese(123.56d, false)); // 一百二十三点五六
        UConsole.log(Convert.numberToChinese(123.56d, true)); // 壹佰贰拾叁点伍陆

        UConsole.log(Convert.numberToChinese(12345670.89d, false)); // 一千二百三十四万五千六百七十点八九
        UConsole.log(Convert.numberToChinese(12345670.89d, true)); // 壹仟贰佰叁拾肆万伍仟陆佰柒拾点捌玖

        UConsole.log(Convert.numberToChinese(123456700.89d, false)); // 一亿二千三百四十五万六千七百点八九
        UConsole.log(Convert.numberToChinese(123456700.89d, true)); // 壹亿贰仟叁佰肆拾伍万陆仟柒佰点捌玖
    }
}
