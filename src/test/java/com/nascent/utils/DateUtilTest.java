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
        new ThreadPoolUtils(ThreadPoolUtils.CachedThread ,4);
    }


    @Test
    public void currentTime() throws Exception {
        String[] a = {"a","d"};
        String b = "a";
        List<String> ab = StringUtils.splitString2List("a a b"," ",1);
        System.out.println(ab);
    }

    @Test
    public void currentDate() throws Exception {
        String str = "9793265456";
        String hexStr = CharsetUtils.str2HexStr(str);
        System.out.println(hexStr);
        System.out.println(CharsetUtils.changeCharset(str,CharsetUtils.US_ASCII,CharsetUtils.US_ASCII));
        System.out.println(CharsetUtils.hexStr2Str(hexStr));
//        String  encodeStr = CharsetUtil.Base64Encode2Str(str,CharsetUtil.UTF_16);
//        System.out.println(encodeStr);
//        String  decodeStr = CharsetUtil.Base64Decode2Str(encodeStr,CharsetUtil.UTF_16);
//        System.out.println(decodeStr);
    }

}