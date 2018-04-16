package com.nascent.utils;

import org.junit.Test;

import java.util.List;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/3/22
 */
public class DateUtilTest {

    @Test
    public void currentDateTime() throws Exception {
        String str = "2365365.236";


           System.out.println(NumberUtil.toChineseLower(str));
    }


    @Test
    public void currentTime() throws Exception {
        String[] a = {"a","d"};
        String b = "a";
        List<String> ab = StringUtil.splitString2List("a a b"," ",1);
        System.out.println(ab);
    }

    @Test
    public void currentDate() throws Exception {
        String str = "9793265456";
        String hexStr = CharsetUtil.str2HexStr(str);
        System.out.println(hexStr);
        System.out.println(CharsetUtil.changeCharset(str,CharsetUtil.US_ASCII,CharsetUtil.US_ASCII));
        System.out.println(CharsetUtil.hexStr2Str(hexStr));
//        String  encodeStr = CharsetUtil.Base64Encode2Str(str,CharsetUtil.UTF_16);
//        System.out.println(encodeStr);
//        String  decodeStr = CharsetUtil.Base64Decode2Str(encodeStr,CharsetUtil.UTF_16);
//        System.out.println(decodeStr);
    }

}