package com.nascent.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Locale;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/3/23
 */
public class CharsetUtil {

    /**
     * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
     */
    public static final String US_ASCII = "US-ASCII";

    /**
     * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * 8 位 UCS 转换格式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
     */
    public static final String UTF_16BE = "UTF-16BE";

    /**
     * 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
     */
    public static final String UTF_16LE = "UTF-16LE";

    /**
     * 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
     */
    public static final String UTF_16 = "UTF-16";

    /**
     * 中文超大字符集
     */
    public static final String GBK = "GBK";

    public static final String GB2312 = "GB2312";

    private static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();
    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();


    private final static char[] mChars = "0123456789ABCDEF".toCharArray();
    private final static String mHexStr = "0123456789ABCDEF";

    /**
     * 将字符编码转换成US-ASCII码
     */
    public final static String toASCII(String str) {
        return changeCharset(str, US_ASCII);
    }

    /**
     * 将字符编码转换成ISO-8859-1码
     */
    public final static String toISO88591(String str) {
        return changeCharset(str, ISO_8859_1);
    }

    /**
     * 将字符编码转换成UTF-8码
     */
    public static String toUTF8(String str) {
        return changeCharset(str, UTF_8);
    }

    /**
     * 将字符编码转换成UTF-16BE码
     */
    public final static String toUTF16BE(String str) {
        return changeCharset(str, UTF_16BE);
    }

    /**
     * 将字符编码转换成UTF-16LE码
     */
    public final static String toUTF16LE(String str) {
        return changeCharset(str, UTF_16LE);
    }

    /**
     * 将字符编码转换成UTF-16码
     */
    public final static String toUTF16(String str) {
        return changeCharset(str, UTF_16);
    }

    /**
     * 将字符编码转换成GBK码
     */
    public final static String toGBK(String str) {
        return changeCharset(str, GBK);
    }

    /**
     * 将字符编码转换成GB2312码
     */
    public final static String toGB2312(String str) {
        return changeCharset(str, GB2312);
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String changeCharset(String str, String newCharset) {
        if (str != null) {
            try {
                // 用默认字符编码解码字符串。
                byte[] bs = str.getBytes(UTF_8);
                // 用新的字符编码生成字符串
                return new String(bs, newCharset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取默认字符编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String getDefaultCharset() throws UnsupportedEncodingException {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream(), UTF_8);
        String enc = writer.getEncoding();
        return enc;
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String changeCharset(String str, String oldCharset
            , String newCharset) {
        if (str != null) {

            byte[] bs = new byte[0];
            try {
                // 用旧的字符编码解码字符串。解码可能会出现异常。
                bs = str.getBytes(oldCharset);
                // 用新的字符编码生成字符串
                return new String(bs, newCharset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return null;
    }



    /**
     * 检查16进制字符串是否有效
     *
     * @param sHex String 16进制字符串
     * @return boolean
     */
    public static boolean checkHexStr(String sHex) {
        String sTmp = sHex.toString().trim().replace(" ", "").toUpperCase(Locale.US);
        int iLen = sTmp.length();

        if (iLen > 1 && iLen % 2 == 0) {
            for (int i = 0; i < iLen; i++)
                if (!mHexStr.contains(sTmp.substring(i, i + 1)))
                    return false;
            return true;
        } else
            return false;
    }

    /**
     * 字符串转换成十六进制字符串
     *
     * @param str String 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; i++) {
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
            sb.append(mChars[bs[i] & 0x0F]);
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制字符串转换成 ASCII字符串
     *
     * @param hexStr
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {
        hexStr = hexStr.toString().trim().replace(" ", "").toUpperCase(Locale.US);
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int iTmp = 0x00;
        ;

        for (int i = 0; i < bytes.length; i++) {
            iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;
            iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (iTmp & 0xFF);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     *
     * @param b    byte[] byte数组
     * @param iLen int 取前N位处理 N=iLen
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b, int iLen) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < iLen; n++) {
            sb.append(mChars[(b[n] & 0xFF) >> 4]);
            sb.append(mChars[b[n] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    /**
     * bytes字符串转换为Byte值
     *
     * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src) {
        /*对输入值进行规范化整理*/
        src = src.trim().replace(" ", "").toUpperCase(Locale.US);
        //处理值初始化
        int m = 0, n = 0;
        int iLen = src.length() / 2; //计算长度
        byte[] ret = new byte[iLen]; //分配存储空间

        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     *
     * @param strText String 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String str2Unicode(String strText)
            throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                str.append("\\u");
            else // 低位在前面补00
                str.append("\\u00");
            str.append(strHex);
        }
        return str.toString();
    }

    /**
     * unicode的String转换成String的字符串
     *
     * @param hex String 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicode2Str(String hex) {
        int t = hex.length() / 6;
        int iTmp = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 将16进制的string转为int
            iTmp = (Integer.valueOf(s.substring(2, 4), 16) << 8) | Integer.valueOf(s.substring(4), 16);
            // 将int转换为字符
            str.append(new String(Character.toChars(iTmp)));
        }
        return str.toString();
    }

    /**
     * Base64解码字符串
     *
     * @param str
     * @param charset
     * @return
     */
    public static String Base64Decode2Str(String str, String charset) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        String decode = "";
        try {
            decode = new String(BASE64_DECODER.decode(str), charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }

    /**
     * Base64编码字符串
     *
     * @param str
     * @param charset
     * @return
     */
    public static String Base64Encode2Str(String str, String charset) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        byte[] strByte = new byte[0];
        try {
            strByte = str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //编码
        return BASE64_ENCODER.encodeToString(strByte);
    }

    /**
     * Base64编码成utf8字符串
     *
     * @param str
     * @return
     */
    public static String Base64Encode2Utf8(String str) {
        return Base64Encode2Str(str, UTF_8);
    }

    /**
     * Base64解码成utf8字符串
     *
     * @param str
     * @return
     */
    public static String Base64Dncode2Utf8(String str) {
        return Base64Decode2Str(str, UTF_8);
    }


}
