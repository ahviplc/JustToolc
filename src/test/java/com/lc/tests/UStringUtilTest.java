package com.lc.tests;

import com.lc.lang.UConsole;
import com.lc.lang.UDict;
import com.lc.utils.UStringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UStringUtilTest {
    public static void main(String[] args) {
        System.out.println(UStringUtil.toUpper("lc")); // LC
        System.out.println(UStringUtil.toLower("LC")); // lc
        // 过期方法
        //UConsole.log(UStringUtil.padEnd("3", 8, '0')); // 30000000
        //UConsole.log(UStringUtil.padEnd("3", 8, "8".charAt(0))); // 38888888
    }

    @Test
    public void UStringUtilTest1() {
        System.out.println(UStringUtil.toUpper("lc")); // LC
    }

    @Test
    public void isBlankTest() {
        String blank = "	  　";
        Assert.assertTrue(UStringUtil.isBlank(blank));
    }

    @Test
    public void isBlankTest2() {
        String blank = "\u202a";
        Assert.assertTrue(UStringUtil.isBlank(blank));
    }

    @Test
    public void trimTest() {
        String blank = "	 哈哈 　";
        String trim = UStringUtil.trim(blank);
        Assert.assertEquals("哈哈", trim);
    }

    @Test
    public void cleanBlankTest() {
        // 包含：制表符、英文空格、不间断空白符、全角空格
        String str = "	 你 好　";
        String cleanBlank = UStringUtil.cleanBlank(str);
        Assert.assertEquals("你好", cleanBlank);
    }

    @Test
    public void cutTest() {
        String str = "aaabbbcccdddaadfdfsdfsdf0";
        String[] cut = UStringUtil.cut(str, 4);
        Assert.assertArrayEquals(new String[]{"aaab", "bbcc", "cddd", "aadf", "dfsd", "fsdf", "0"}, cut);
    }

    @Test
    public void splitTest() {
        String str = "a,b ,c,d,,e";
        List<String> split = UStringUtil.split(str, ',', -1, true, true);
        // 测试空是否被去掉
        Assert.assertEquals(5, split.size());
        // 测试去掉两边空白符是否生效
        Assert.assertEquals("b", split.get(1));

        final String[] strings = UStringUtil.splitToArray("abc/", '/');
        Assert.assertEquals(2, strings.length);
    }

    @Test
    public void splitToLongTest() {
        String str = "1,2,3,4, 5";
        long[] longArray = UStringUtil.splitToLong(str, ',');
        Assert.assertArrayEquals(new long[]{1, 2, 3, 4, 5}, longArray);

        longArray = UStringUtil.splitToLong(str, ",");
        Assert.assertArrayEquals(new long[]{1, 2, 3, 4, 5}, longArray);
    }

    @Test
    public void splitToIntTest() {
        String str = "1,2,3,4, 5";
        int[] intArray = UStringUtil.splitToInt(str, ',');
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, intArray);

        intArray = UStringUtil.splitToInt(str, ",");
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, intArray);
    }

    @Test
    public void formatTest() {
        UDict set = UDict.create().set("name", "张三").set("phone", "13888881111");
        UConsole.log(set);

        String template = "你好，我是{name}，我的电话是：{phone}";
        String result = UStringUtil.format(template, UDict.create().set("name", "张三").set("phone", "13888881111"));
        UConsole.log(result);
        Assert.assertEquals("你好，我是张三，我的电话是：13888881111", result);

        String result2 = UStringUtil.format(template, UDict.create().set("name", "张三").set("phone", null));
        UConsole.log(result2);
        Assert.assertEquals("你好，我是张三，我的电话是：{phone}", result2);
    }

    @Test
    public void stripTest() {
        String str = "abcd123";
        String strip = UStringUtil.strip(str, "ab", "23");
        Assert.assertEquals("cd1", strip);

        str = "abcd123";
        strip = UStringUtil.strip(str, "ab", "");
        Assert.assertEquals("cd123", strip);

        str = "abcd123";
        strip = UStringUtil.strip(str, null, "");
        Assert.assertEquals("abcd123", strip);

        str = "abcd123";
        strip = UStringUtil.strip(str, null, "567");
        Assert.assertEquals("abcd123", strip);

        Assert.assertEquals("", UStringUtil.strip("a", "a"));
        Assert.assertEquals("", UStringUtil.strip("a", "a", "b"));
    }

    @Test
    public void stripIgnoreCaseTest() {
        String str = "abcd123";
        String strip = UStringUtil.stripIgnoreCase(str, "Ab", "23");
        Assert.assertEquals("cd1", strip);

        str = "abcd123";
        strip = UStringUtil.stripIgnoreCase(str, "AB", "");
        Assert.assertEquals("cd123", strip);

        str = "abcd123";
        strip = UStringUtil.stripIgnoreCase(str, "ab", "");
        Assert.assertEquals("cd123", strip);

        str = "abcd123";
        strip = UStringUtil.stripIgnoreCase(str, null, "");
        Assert.assertEquals("abcd123", strip);

        str = "abcd123";
        strip = UStringUtil.stripIgnoreCase(str, null, "567");
        Assert.assertEquals("abcd123", strip);
    }

    @Test
    public void indexOfIgnoreCaseTest() {
        Assert.assertEquals(-1, UStringUtil.indexOfIgnoreCase(null, "balabala", 0));
        Assert.assertEquals(-1, UStringUtil.indexOfIgnoreCase("balabala", null, 0));
        Assert.assertEquals(0, UStringUtil.indexOfIgnoreCase("", "", 0));
        Assert.assertEquals(0, UStringUtil.indexOfIgnoreCase("aabaabaa", "A", 0));
        Assert.assertEquals(2, UStringUtil.indexOfIgnoreCase("aabaabaa", "B", 0));
        Assert.assertEquals(1, UStringUtil.indexOfIgnoreCase("aabaabaa", "AB", 0));
        Assert.assertEquals(5, UStringUtil.indexOfIgnoreCase("aabaabaa", "B", 3));
        Assert.assertEquals(-1, UStringUtil.indexOfIgnoreCase("aabaabaa", "B", 9));
        Assert.assertEquals(2, UStringUtil.indexOfIgnoreCase("aabaabaa", "B", -1));
        Assert.assertEquals(2, UStringUtil.indexOfIgnoreCase("aabaabaa", "", 2));
        Assert.assertEquals(-1, UStringUtil.indexOfIgnoreCase("abc", "", 9));
    }

    @Test
    public void lastIndexOfTest() {
        String a = "aabbccddcc";
        int lastIndexOf = UStringUtil.lastIndexOf(a, "c", 0, false);
        Assert.assertEquals(-1, lastIndexOf);
    }

    @Test
    public void lastIndexOfIgnoreCaseTest() {
        Assert.assertEquals(-1, UStringUtil.lastIndexOfIgnoreCase(null, "balabala", 0));
        Assert.assertEquals(-1, UStringUtil.lastIndexOfIgnoreCase("balabala", null));
        Assert.assertEquals(0, UStringUtil.lastIndexOfIgnoreCase("", ""));
        Assert.assertEquals(7, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "A"));
        Assert.assertEquals(5, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "B"));
        Assert.assertEquals(4, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "AB"));
        Assert.assertEquals(2, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 3));
        Assert.assertEquals(5, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 9));
        Assert.assertEquals(-1, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", -1));
        Assert.assertEquals(2, UStringUtil.lastIndexOfIgnoreCase("aabaabaa", "", 2));
        Assert.assertEquals(3, UStringUtil.lastIndexOfIgnoreCase("abc", "", 9));
    }

    @Test
    public void replaceTest() {
        String string = UStringUtil.replace("aabbccdd", 2, 6, '*');
        Assert.assertEquals("aa****dd", string);
        string = UStringUtil.replace("aabbccdd", 2, 12, '*');
        Assert.assertEquals("aa******", string);
    }

    @Test
    public void replaceTest2() {
        String result = UStringUtil.replace("123", "2", "3");
        Assert.assertEquals("133", result);
    }

    @Test
    public void replaceTest3() {
        String result = UStringUtil.replace(",abcdef,", ",", "|");
        Assert.assertEquals("|abcdef|", result);
    }

    @Test
    public void replaceTest4() {
        String a = "1039";
        String result = UStringUtil.padPre(a, 8, "0"); //在字符串1039前补4个0
        Assert.assertEquals("00001039", result);
    }

    @Test
    public void upperFirstTest() {
        StringBuilder sb = new StringBuilder("KEY");
        String s = UStringUtil.upperFirst(sb);
        Assert.assertEquals(s, sb.toString());
    }

    @Test
    public void lowerFirstTest() {
        StringBuilder sb = new StringBuilder("KEY");
        String s = UStringUtil.lowerFirst(sb);
        Assert.assertEquals("kEY", s);
    }

    @Test
    public void subTest() {
        String a = "abcderghigh";
        String pre = UStringUtil.sub(a, -5, a.length());
        Assert.assertEquals("ghigh", pre);
    }

    @Test
    public void subByCodePointTest() {
        // 🤔👍🍓🤔
        String test = "\uD83E\uDD14\uD83D\uDC4D\uD83C\uDF53\uD83E\uDD14";

        // 不正确的子字符串
        String wrongAnswer = UStringUtil.sub(test, 0, 3);
        Assert.assertNotEquals("\uD83E\uDD14\uD83D\uDC4D\uD83C\uDF53", wrongAnswer);

        // 正确的子字符串
        String rightAnswer = UStringUtil.subByCodePoint(test, 0, 3);
        Assert.assertEquals("\uD83E\uDD14\uD83D\uDC4D\uD83C\uDF53", rightAnswer);
    }

    @Test
    public void subBeforeTest() {
        String a = "abcderghigh";
        String pre = UStringUtil.subBefore(a, "d", false);
        Assert.assertEquals("abc", pre);
        pre = UStringUtil.subBefore(a, 'd', false);
        Assert.assertEquals("abc", pre);
        pre = UStringUtil.subBefore(a, 'a', false);
        Assert.assertEquals("", pre);

        //找不到返回原串
        pre = UStringUtil.subBefore(a, 'k', false);
        Assert.assertEquals(a, pre);
        pre = UStringUtil.subBefore(a, 'k', true);
        Assert.assertEquals(a, pre);
    }

    @Test
    public void subAfterTest() {
        String a = "abcderghigh";
        String pre = UStringUtil.subAfter(a, "d", false);
        Assert.assertEquals("erghigh", pre);
        pre = UStringUtil.subAfter(a, 'd', false);
        Assert.assertEquals("erghigh", pre);
        pre = UStringUtil.subAfter(a, 'h', true);
        Assert.assertEquals("", pre);

        //找不到字符返回空串
        pre = UStringUtil.subAfter(a, 'k', false);
        Assert.assertEquals("", pre);
        pre = UStringUtil.subAfter(a, 'k', true);
        Assert.assertEquals("", pre);
    }

    @Test
    public void subSufByLengthTest() {
        Assert.assertEquals("cde", UStringUtil.subSufByLength("abcde", 3));
        Assert.assertEquals("", UStringUtil.subSufByLength("abcde", -1));
        Assert.assertEquals("", UStringUtil.subSufByLength("abcde", 0));
        Assert.assertEquals("abcde", UStringUtil.subSufByLength("abcde", 5));
        Assert.assertEquals("abcde", UStringUtil.subSufByLength("abcde", 10));
    }

    @Test
    public void repeatAndJoinTest() {
        String repeatAndJoin = UStringUtil.repeatAndJoin("?", 5, ",");
        Assert.assertEquals("?,?,?,?,?", repeatAndJoin);

        repeatAndJoin = UStringUtil.repeatAndJoin("?", 0, ",");
        Assert.assertEquals("", repeatAndJoin);

        repeatAndJoin = UStringUtil.repeatAndJoin("?", 5, null);
        Assert.assertEquals("?????", repeatAndJoin);
    }

    @Test
    public void moveTest() {
        String str = "aaaaaaa22222bbbbbbb";
        String result = UStringUtil.move(str, 7, 12, -3);
        Assert.assertEquals("aaaa22222aaabbbbbbb", result);
        result = UStringUtil.move(str, 7, 12, -4);
        Assert.assertEquals("aaa22222aaaabbbbbbb", result);
        result = UStringUtil.move(str, 7, 12, -7);
        Assert.assertEquals("22222aaaaaaabbbbbbb", result);
        result = UStringUtil.move(str, 7, 12, -20);
        Assert.assertEquals("aaaaaa22222abbbbbbb", result);

        result = UStringUtil.move(str, 7, 12, 3);
        Assert.assertEquals("aaaaaaabbb22222bbbb", result);
        result = UStringUtil.move(str, 7, 12, 7);
        Assert.assertEquals("aaaaaaabbbbbbb22222", result);
        result = UStringUtil.move(str, 7, 12, 20);
        Assert.assertEquals("aaaaaaab22222bbbbbb", result);

        result = UStringUtil.move(str, 7, 12, 0);
        Assert.assertEquals("aaaaaaa22222bbbbbbb", result);
    }

    @Test
    public void removePrefixIgnorecaseTest() {
        String a = "aaabbb";
        String prefix = "aaa";
        Assert.assertEquals("bbb", UStringUtil.removePrefixIgnoreCase(a, prefix));

        prefix = "AAA";
        Assert.assertEquals("bbb", UStringUtil.removePrefixIgnoreCase(a, prefix));

        prefix = "AAABBB";
        Assert.assertEquals("", UStringUtil.removePrefixIgnoreCase(a, prefix));
    }

    @Test
    public void maxLengthTest() {
        String text = "我是一段正文，很长的正文，需要截取的正文";
        String str = UStringUtil.maxLength(text, 5);
        Assert.assertEquals("我是一段正...", str);
        str = UStringUtil.maxLength(text, 21);
        Assert.assertEquals(text, str);
        str = UStringUtil.maxLength(text, 50);
        Assert.assertEquals(text, str);
    }

    @Test
    public void toCamelCaseTest() {
        String str = "Table_Test_Of_day";
        String result = UStringUtil.toCamelCase(str);
        Assert.assertEquals("tableTestOfDay", result);

        String str1 = "TableTestOfDay";
        String result1 = UStringUtil.toCamelCase(str1);
        Assert.assertEquals("TableTestOfDay", result1);

        String abc1d = UStringUtil.toCamelCase("abc_1d");
        Assert.assertEquals("abc1d", abc1d);
    }

    @Test
    public void toUnderLineCaseTest() {
        String str = "Table_Test_Of_day";
        String result = UStringUtil.toUnderlineCase(str);
        Assert.assertEquals("table_test_of_day", result);

        String str1 = "_Table_Test_Of_day_";
        String result1 = UStringUtil.toUnderlineCase(str1);
        Assert.assertEquals("_table_test_of_day_", result1);

        String str2 = "_Table_Test_Of_DAY_";
        String result2 = UStringUtil.toUnderlineCase(str2);
        Assert.assertEquals("_table_test_of_DAY_", result2);

        String str3 = "_TableTestOfDAYtoday";
        String result3 = UStringUtil.toUnderlineCase(str3);
        Assert.assertEquals("_table_test_of_DAY_today", result3);

        String str4 = "HelloWorld_test";
        String result4 = UStringUtil.toUnderlineCase(str4);
        Assert.assertEquals("hello_world_test", result4);
    }

    @Test
    public void containsAnyTest() {
        //字符
        boolean containsAny = UStringUtil.containsAny("aaabbbccc", 'a', 'd');
        Assert.assertTrue(containsAny);
        containsAny = UStringUtil.containsAny("aaabbbccc", 'e', 'd');
        Assert.assertFalse(containsAny);
        containsAny = UStringUtil.containsAny("aaabbbccc", 'd', 'c');
        Assert.assertTrue(containsAny);

        //字符串
        containsAny = UStringUtil.containsAny("aaabbbccc", "a", "d");
        Assert.assertTrue(containsAny);
        containsAny = UStringUtil.containsAny("aaabbbccc", "e", "d");
        Assert.assertFalse(containsAny);
        containsAny = UStringUtil.containsAny("aaabbbccc", "d", "c");
        Assert.assertTrue(containsAny);
    }

    @Test
    public void centerTest() {
        Assert.assertNull(UStringUtil.center(null, 10));
        Assert.assertEquals("    ", UStringUtil.center("", 4));
        Assert.assertEquals("ab", UStringUtil.center("ab", -1));
        Assert.assertEquals(" ab ", UStringUtil.center("ab", 4));
        Assert.assertEquals("abcd", UStringUtil.center("abcd", 2));
        Assert.assertEquals(" a  ", UStringUtil.center("a", 4));
    }

    @Test
    public void padPreTest() {
        Assert.assertNull(UStringUtil.padPre(null, 10, ' '));
        Assert.assertEquals("001", UStringUtil.padPre("1", 3, '0'));
        Assert.assertEquals("12", UStringUtil.padPre("123", 2, '0'));

        Assert.assertNull(UStringUtil.padPre(null, 10, "AA"));
        Assert.assertEquals("AB1", UStringUtil.padPre("1", 3, "ABC"));
        Assert.assertEquals("12", UStringUtil.padPre("123", 2, "ABC"));
    }

    @Test
    public void padAfterTest() {
        Assert.assertNull(UStringUtil.padAfter(null, 10, ' '));
        Assert.assertEquals("100", UStringUtil.padAfter("1", 3, '0'));
        Assert.assertEquals("23", UStringUtil.padAfter("123", 2, '0'));

        Assert.assertNull(UStringUtil.padAfter(null, 10, "ABC"));
        Assert.assertEquals("1AB", UStringUtil.padAfter("1", 3, "ABC"));
        Assert.assertEquals("23", UStringUtil.padAfter("123", 2, "ABC"));
    }

    @Test
    public void subBetweenAllTest() {
        Assert.assertArrayEquals(new String[]{"yz", "abc"}, UStringUtil.subBetweenAll("saho[yz]fdsadp[abc]a", "[", "]"));
        Assert.assertArrayEquals(new String[]{"abc"}, UStringUtil.subBetweenAll("saho[yzfdsadp[abc]a]", "[", "]"));
        Assert.assertArrayEquals(new String[]{"abc", "abc"}, UStringUtil.subBetweenAll("yabczyabcz", "y", "z"));
        Assert.assertArrayEquals(new String[0], UStringUtil.subBetweenAll(null, "y", "z"));
        Assert.assertArrayEquals(new String[0], UStringUtil.subBetweenAll("", "y", "z"));
        Assert.assertArrayEquals(new String[0], UStringUtil.subBetweenAll("abc", null, "z"));
        Assert.assertArrayEquals(new String[0], UStringUtil.subBetweenAll("abc", "y", null));
    }

    @Test
    public void subBetweenAllTest2() {
        //issue#861@Github，起始不匹配的时候，应该直接空
        String src1 = "/* \n* hutool  */  asdas  /* \n* hutool  */";
        String src2 = "/ * hutool  */  asdas  / * hutool  */";

        String[] results1 = UStringUtil.subBetweenAll(src1, "/**", "*/");
        Assert.assertEquals(0, results1.length);

        String[] results2 = UStringUtil.subBetweenAll(src2, "/*", "*/");
        Assert.assertEquals(0, results2.length);
    }
}