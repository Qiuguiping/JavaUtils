package com.nascent.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/4/9
 */
public class NumberUtils {
    //num 表示数字，lower表示小写，upper表示大写
    private static final String[] num_lower = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] num_upper = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    //unit 表示单位权值，lower表示小写，upper表示大写
    private static final String[] unit_lower = {"", "十", "百", "千"};
    private static final String[] unit_upper = {"", "拾", "佰", "仟"};
    private static final String[] unit_common = {"", "万", "亿", "兆", "京", "垓", "秭", "穰", "沟", "涧", "正", "载"};

    //允许的格式
    private static final List<String> promissTypes = Arrays.asList("INTEGER", "INT", "LONG", "DECIMAL", "FLOAT", "DOUBLE", "STRING", "BYTE", "TYPE", "SHORT");


    /**
     * 在进制表示中的字符集合
     */
    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'};

    /**
     * 数字转化为小写的汉字
     *
     * @param num 将要转化的数字
     * @return
     */
    public static String toChineseLower(Object num) {
        return format(num, num_lower, unit_lower);
    }

    /**
     * 数字转化为大写的汉字
     *
     * @param num 将要转化的数字
     * @return
     */
    public static String toChineseUpper(Object num) {
        return format(num, num_upper, unit_upper);
    }

    /**
     * 格式化数字
     *
     * @param num      原数字
     * @param numArray 数字大小写数组
     * @param unit     单位权值
     * @return
     */
    private static String format(Object num, String[] numArray, String[] unit) {
        if (!promissTypes.contains(num.getClass().getSimpleName().toUpperCase())) {
            throw new RuntimeException("不支持的格式类型");
        }
        String numStr = String.valueOf(num);
        checkNum(numStr);
        //获取整数部分
        String intNum = getInt(numStr);
        //获取小数部分
        String decimal = getFraction(numStr);
        //格式化整数部分
        String result = formatIntPart(intNum, numArray, unit);
        //小数部分不为空
        if (!"".equals(decimal)) {
            //格式化小数
            result += "点" + formatFractionalPart(decimal, numArray);
        }
        return result;
    }

    /**
     * 格式化整数部分
     *
     * @param num      整数部分
     * @param numArray 数字大小写数组
     * @return
     */
    private static String formatIntPart(String num, String[] numArray, String[] unit) {

        //按4位分割成不同的组（不足四位的前面补0）
        String[] nums = split2ArrayByLen(num, 4);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nums.length; i++) {
            StringBuffer partNum = new StringBuffer();
            for (int j = 0; j < nums[i].length(); j++) {
                int length = nums[i].length();
                partNum.append(unit[j]).append(numArray[Integer.valueOf(nums[i].substring(length - j - 1, length - j))]);
            }
            sb.insert(0, partNum.reverse().append(unit_common[i]));
        }
        return sb.toString();
    }


    /**
     * 根据指定长度分割字符串为数组
     *
     * @param str
     * @param length
     * @return
     */
    public static String[] split2ArrayByLen(String str, int length) {
        int strLength = str.length();
        int size = strLength / length;
        if (strLength % length > 0) {
            size += 1;
        }
        int i = strLength;
        String[] strArr = new String[size];
        for (int k = 0; k < size; k++) {
            strArr[k] = str.substring(i - length < 0 ? 0 : i - length, i);
            i -= length;
        }
        return strArr;
    }

    /**
     * 格式化小数部分
     *
     * @param decimal  小数部分
     * @param numArray 数字大小写数组
     * @return
     */
    private static String formatFractionalPart(String decimal, String[] numArray) {
        char[] val = decimal.toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int n = Integer.valueOf(val[i] + "");
            sb.append(numArray[n]);
        }
        return sb.toString();
    }


    /**
     * 获取整数部分
     *
     * @param num
     * @return
     */
    private static String getInt(String num) {
        //检查格式
        checkNum(num);

        char[] val = String.valueOf(num).toCharArray();
        StringBuffer sb = new StringBuffer();
        int t, s = 0;
        for (int i = 0; i < val.length; i++) {
            if (val[i] == '.') {
                break;
            }
            t = Integer.parseInt(val[i] + "", 16);
            if (s + t == 0) {
                continue;
            }
            sb.append(t);
            s += t;
        }
        return (sb.length() == 0 ? "0" : sb.toString());
    }

    /**
     * 获取小数部分
     *
     * @param num
     * @return
     */
    private static String getFraction(String num) {
        int i = num.lastIndexOf(".");
        if (num.indexOf(".") != i) {
            throw new RuntimeException("数字格式不正确，最多只能有一位小数点！");
        }
        String fraction = "";
        if (i >= 0) {
            fraction = getInt(new StringBuffer(num).reverse().toString());
            if (fraction.equals("0")) {
                return "";
            }
        }
        return new StringBuffer(fraction).reverse().toString();
    }

    /**
     * 检查数字格式
     *
     * @param num
     */
    private static void checkNum(String num) {
        if (num.indexOf(".") != num.lastIndexOf(".")) {
            throw new RuntimeException("数字[" + num + "]格式不正确!");
        }
        if (num.indexOf("-") != num.lastIndexOf("-") || num.lastIndexOf("-") > 0) {
            throw new RuntimeException("数字[" + num + "]格式不正确！");
        }
        if (num.indexOf("+") != num.lastIndexOf("+")) {
            throw new RuntimeException("数字[" + num + "]格式不正确！");
        }
        if (num.replaceAll("[\\d|\\.|\\-|\\+]", "").length() > 0) {
            throw new RuntimeException("数字[" + num + "]格式不正确！");
        }
    }

    /**
     * 判断字符串是否传数字
     *
     * @param num
     * @return
     */
    public static boolean isPureNumber(String num) {
        return num.matches("[0-9]+");
    }

    /**
     * 判断字符串是否可以转换为数字
     *
     * @param num
     * @return
     */
    public static boolean isNumber(String num) {
        if (num.indexOf(".") != num.lastIndexOf(".")) {
            return false;
        }
        if (num.indexOf("-") != num.lastIndexOf("-") || num.lastIndexOf("-") > 0) {
            return false;
        }
        if (num.indexOf("+") != num.lastIndexOf("+")) {
            return false;
        }
        if (num.replaceAll("[\\d|\\.|\\-|\\+]", "").length() > 0) {
            return false;
        }
        return true;
    }


    /**
     * 将十进制的数字转换为指定进制的字符串
     *
     * @param n    十进制的数字
     * @param base 指定的进制
     * @return
     */
    public static String decimal2Other(long n, int base) {
        long num = 0;
        if (n < 0) {
            num = ((long) 2 * 0x7fffffff) + n + 2;
        } else {
            num = n;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / base) > 0) {
            buf[--charPos] = digits[(int) (num % base)];
            num /= base;
        }
        buf[--charPos] = digits[(int) (num % base)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 将其它进制的数字（字符串形式）转换为十进制的数字
     *
     * @param str  其它进制的数字（字符串形式）
     * @param base 指定的进制
     * @return
     */
    public static long toDecimal(String str, int base) {
        checkNum(str);
        char[] buf = new char[str.length()];
        str.getChars(0, str.length(), buf, 0);
        long num = 0;
        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == buf[i]) {
                    num += j * Math.pow(base, buf.length - i - 1);
                    break;
                }
            }
        }
        return num;
    }




}
