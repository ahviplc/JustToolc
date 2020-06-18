package com.lc.tests;

import com.lc.lang.UConsole;
import com.lc.lang.UDict;

import java.util.Map;

public class UDictTest {
    public static void main(String[] args) {
        UDict uDictMap = UDict.create().set("1", "1").set("2", "2").set("3", "3");
        UConsole.log(uDictMap); // {1=1, 2=2, 3=3}
        UConsole.log(uDictMap.size()); // 3
        UConsole.log(uDictMap.get("1")); // 1
        UConsole.log(uDictMap.containsKey("3")); // true
        UConsole.log(uDictMap.containsKey("6")); // false

        // foreach循环遍历
        for (Map.Entry<String, Object> entry : uDictMap.entrySet()) {
            //System.out.println("key:" + entry.getKey() + "   value:" + entry.getValue());
            UConsole.log("key:{} -> value:{}", entry.getKey(), entry.getValue());
            //key:1 -> value:1
            //key:2 -> value:2
            //key:3 -> value:3
        }
    }
}
