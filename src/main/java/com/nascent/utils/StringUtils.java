package com.nascent.utils;



import java.util.*;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/3/22
 */
public class StringUtils {


    private static final List<String> All_TRUE_STRING = new ArrayList<String>(Arrays.asList("1", "yes", "y", "true"));

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }


    /**
     * 判断字符串是否不为空
     * null  返回false
     * ""  返回 false
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if ((values == null) || (values.length == 0)) {
            result = false;
        } else {
            for (String value : values) {
                result &= isNotEmpty(value);
            }
        }
        return result;
    }


    // ==========================================================================
    // 默认值函数。
    //
    // 当字符串为empty或blank时，将字符串转换成指定的默认字符串。
    // 注：判断字符串为null时，可用更通用的ObjectUtil.defaultIfNull。
    // ==========================================================================

    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.defaultIfEmpty(null, "default")  = "default"
     * StringUtil.defaultIfEmpty("", "default")    = "default"
     * StringUtil.defaultIfEmpty("  ", "default")  = "  "
     * StringUtil.defaultIfEmpty("bat", "default") = "bat"
     * </pre>
     *
     * @param str        要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return str == null || str.length() == 0 ? defaultStr : str;
    }

    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.defaultIfBlank(null, "default")  = "default"
     * StringUtil.defaultIfBlank("", "default")    = "default"
     * StringUtil.defaultIfBlank("  ", "default")  = "default"
     * StringUtil.defaultIfBlank("bat", "default") = "bat"
     * </pre>
     *
     * @param str        要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }


    /**
     * 比较两个字符串（大小写不敏感）。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean isEqualsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 比较两个字符串 （大小写敏感）
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equals(str2);
    }


    /**
     * 取得字符串的长度。
     *
     * @param str 要取长度的字符串
     * @return 如果字符串为<code>null</code>，则返回<code>0</code>。否则返回字符串的长度。
     */
    public static int getLength(String str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 获取字符串长度，中文算2位
     *
     * @param s
     * @return
     */
    public static int getWordCount(String s) {
        int length = 0;

        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }

        return length;
    }


    // ==========================================================================
    // 字符串连接函数。
    //
    // 将多个对象按指定分隔符连接成字符串。
    // ==========================================================================


    /**
     * 将数组中的元素连接成一个字符串。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.join(*,null)                = null
     * StringUtil.join(*,[])                  = ""
     * StringUtil.join(*,[null])              = ""
     * StringUtil.join("--",["a", "b", "c"])  = "a--b--c"
     * StringUtil.join(null,["a", "b", "c"])  = "abc"
     * StringUtil.join("",["a", "b", "c"])    = "abc"
     * StringUtil.join(',',[null, "", "a"])   = ",,a"
     * </pre>
     *
     * @param objs      要连接的数组
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(String separator, Object... objs) {
        if (objs == null) {
            return null;
        }

        if (separator == null) {
            separator = "";
        }

        int arraySize = objs.length;
        int bufSize;

        if (arraySize == 0) {
            bufSize = 0;
        } else {
            int firstLength = objs[0] == null ? 16 : objs[0].toString().length();
            bufSize = arraySize * (firstLength + separator.length());
        }

        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (separator != null && i > 0) {
                buf.append(separator);
            }

            if (objs[i] != null) {
                buf.append(objs[i]);
            }
        }

        return buf.toString();
    }


    /**
     * 将<code>Iterator</code>中的元素连接成一个字符串。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.join(*,null)                = null
     * StringUtil.join(*,[])                  = ""
     * StringUtil.join(*,[null])              = ""
     * StringUtil.join("--",["a", "b", "c"])  = "a--b--c"
     * StringUtil.join(null,["a", "b", "c"])  = "abc"
     * StringUtil.join("",["a", "b", "c"])    = "abc"
     * StringUtil.join(',',[null, "", "a"])   = ",,a"
     * </pre>
     *
     * @param list      要连接的<code>Iterator</code>
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(String separator, Iterable<?> list) {
        if (list == null) {
            return null;
        }
        // Java默认值是16, 可能偏小
        StringBuilder buf = new StringBuilder(256);

        for (Iterator<?> i = list.iterator(); i.hasNext(); ) {
            Object obj = i.next();

            if (obj != null) {
                buf.append(obj);
            }

            if (separator != null && i.hasNext()) {
                buf.append(separator);
            }

        }

        return buf.toString();
    }


    // ==========================================================================
    // 字符串分割函数。
    //
    // 将字符串按指定分隔符分割。
    // ==========================================================================

    /**
     * 将字符串按指定字符分割。
     * <p>
     * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     * <p/>
     * <p>
     * <pre>
     * StringUtil.split(null, *)         = null
     * StringUtil.split("", *)           = []
     * StringUtil.split("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtil.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringUtil.split("a:b:c", '.')    = ["a:b:c"]
     * StringUtil.split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     * <p/>
     * </p>
     *
     * @param str           要分割的字符串
     * @param separatorChar 分隔符
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str, char separatorChar) {
        if (str == null) {
            return null;
        }

        int length = str.length();

        if (length == 0) {
            return new String[0];
        }

        List<String> list = new LinkedList<String>();
        int i = 0;
        int start = 0;
        boolean match = false;

        while (i < length) {
            if (str.charAt(i) == separatorChar) {
                if (match) {
                    list.add(str.substring(start, i));
                    match = false;
                }

                start = ++i;
                continue;
            }

            match = true;
            i++;
        }

        if (match) {
            list.add(str.substring(start, i));
        }

        return list.toArray(new String[list.size()]);
    }

    /**
     * 根据指定的字符把源字符串分割成一个list
     *
     * @param str     处理的字符串
     * @param pattern 分割字符串
     * @param max 分割List的大小
     * @return 处理后的list
     */
    public static List<String> splitString2List(String str, String pattern, int max) {
        List<String> list = new ArrayList<String>();
        if (isNotEmpty(str)) {
            String[] tt = max > 0 ? str.split(pattern, max) : str.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }

    /**
     * 根据指定的字符把源字符串分割成一个list
     *
     * @param str     处理的字符串
     * @param pattern 分割字符串
     * @return 处理后的list
     */
    public static List<String> splitString2List(String str, String pattern) {
        return splitString2List(str, pattern, -1);
    }


    /**
     * 将字符集转换成整型
     *
     * @param str 要转化的字符串
     * @return int 转化后的整数
     */
    public static int toInt(String str) {
        int result = 0;
        if(!StringUtils.isBlank(str)){
            result = Integer.parseInt(str);
        }
        return result;
    }


    /**
     * 将字符集转换成长整型
     *
     * @param str 要转化的字符串
     * @return long 转化后的整数
     */
    public static long toLong(String str) {
        long result = 0l;
        if(!StringUtils.isBlank(str)) {
            result = Long.parseLong(str);
        }
        return result;
    }


    /**
     * 将字符串转换成布尔型
     *
     * @param theString
     * @return True or False
     */
    public static boolean toBoolean(String theString) {
        if (theString == null) {
            return false;
        }

        theString = theString.trim();
        for (String str : All_TRUE_STRING) {
            if (theString.equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 将字符串首字母转大写
     * @param str 需要处理的字符串
     */
    public  static String upperFirstChar(String str){
        if(isEmpty(str)){
            return "";
        }
        char[] cs=str.toCharArray();
        if((cs[0] >= 'a') && (cs[0] <= 'z')){
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * 将字符串首字母转小写
     * @param str
     * @return
     */
    public  static String lowerFirstChar(String str){
        if(isEmpty(str)){
            return "";
        }
        char[] cs=str.toCharArray();
        if((cs[0] >= 'A') && (cs[0] <= 'Z')){
            cs[0] += (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * 转换String操作，空串和null都转为""
     *
     * @param string
     * @return
     */
    public static String getNotNullString(Object string) {
        if (string == null || "".equals(string)) {
            return "";
        } else {
            return string.toString();
        }
    }


    // ==========================================================================
    // 替换子串。
    // ==========================================================================

    /**
     * 替换指定的子串，替换所有出现的子串。
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     * <p/>
     *
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     * <p/>
     * </p>
     *
     * @param text
     *            要扫描的字符串
     * @param repl
     *            要搜索的子串
     * @param with
     *            替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * 替换指定的子串，替换指定的次数。
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     * <p/>
     *
     * <pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace("", *, *, *)           = ""
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", "a", null, 1)  = "abaa"
     * StringUtil.replace("abaa", "a", "", 1)    = "baa"
     * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     * <p/>
     * </p>
     *
     * @param text
     *            要扫描的字符串
     * @param repl
     *            要搜索的子串
     * @param with
     *            替换字符串
     * @param max
     *            maximum number of values to replace, or <code>-1</code> if no
     *            maximum
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if (text == null || repl == null || with == null || repl.length() == 0 || max == 0) {
            return text;
        }

        StringBuilder buf = new StringBuilder(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        buf.append(text.substring(start));

        return buf.toString();
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>。
     * <p/>
     *
     * <pre>
     * StringUtil.replaceChars(null, *, *)        = null
     * StringUtil.replaceChars("", *, *)          = ""
     * StringUtil.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtil.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str
     *            要扫描的字符串
     * @param searchChar
     *            要搜索的字符
     * @param replaceChar
     *            替换字符
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChar(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }

        return str.replace(searchChar, replaceChar);
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>。如果搜索字符串为<code>null</code>
     * 或空，则返回原字符串。
     * </p>
     * <p>
     * 例如：
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>
     * 。
     * </p>
     * <p>
     * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
     * <p/>
     *
     * <pre>
     * StringUtil.replaceChars(null, *, *)           = null
     * StringUtil.replaceChars("", *, *)             = ""
     * StringUtil.replaceChars("abc", null, *)       = "abc"
     * StringUtil.replaceChars("abc", "", *)         = "abc"
     * StringUtil.replaceChars("abc", "b", null)     = "ac"
     * StringUtil.replaceChars("abc", "b", "")       = "ac"
     * StringUtil.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtil.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtil.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str
     *            要扫描的字符串
     * @param searchChars
     *            要搜索的字符串
     * @param replaceChars
     *            替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length() == 0) {
            return str;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;

        for (int i = 0, isize = searchChars.length(); i < isize; i++) {
            char searchChar = searchChars.charAt(i);

            if (replaceChars == null || i >= replaceChars.length()) {
                // 删除
                int pos = 0;

                for (int j = 0; j < len; j++) {
                    if (chars[j] != searchChar) {
                        chars[pos++] = chars[j];
                    } else {
                        modified = true;
                    }
                }

                len = pos;
            } else {
                // 替换
                for (int j = 0; j < len; j++) {
                    if (chars[j] == searchChar) {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }

        if (!modified) {
            return str;
        }

        return new String(chars, 0, len);
    }

    /**
     * 将指定的子串用另一指定子串覆盖。
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 负的索引值将被看作<code>0</code>
     * ，越界的索引值将被设置成字符串的长度相同的值。
     * <p/>
     *
     * <pre>
     * StringUtil.overlay(null, *, *, *)            = null
     * StringUtil.overlay("", "abc", 0, 0)          = "abc"
     * StringUtil.overlay("abcdef", null, 2, 4)     = "abef"
     * StringUtil.overlay("abcdef", "", 2, 4)       = "abef"
     * StringUtil.overlay("abcdef", "", 4, 2)       = "abef"
     * StringUtil.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
     * StringUtil.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
     * StringUtil.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
     * StringUtil.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
     * StringUtil.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
     * StringUtil.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str
     *            要扫描的字符串
     * @param overlay
     *            用来覆盖的字符串
     * @param start
     *            起始索引
     * @param end
     *            结束索引
     * @return 被覆盖后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String overlay(String str, String overlay, int start, int end) {
        if (str == null) {
            return null;
        }

        if (overlay == null) {
            overlay = "";
        }

        int len = str.length();

        if (start < 0) {
            start = 0;
        }

        if (start > len) {
            start = len;
        }

        if (end < 0) {
            end = 0;
        }

        if (end > len) {
            end = len;
        }

        if (start > end) {
            int temp = start;

            start = end;
            end = temp;
        }

        return new StringBuilder(len + start - end + overlay.length() + 1).append(str.substring(0, start))
                .append(overlay).append(str.substring(end)).toString();
    }

    /**
     * 获取字符串str在String中出现的次数
     *
     * @param string 处理的字符串
     * @param str 子字符串
     */
    public  static int countSubStr(String string, String str) {
        if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }




    /**
     * 替换一个出现的子串
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public  static String replaceFirst(String s, String sub, String with) {
        int i = s.indexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * 替换最后一次出现的字串
     * Replaces the very last occurrence of a substring with supplied string.
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public  static String replaceLast(String s, String sub, String with) {
        int i = s.lastIndexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * 删除所有的子串
     * Removes all substring occurrences from the string.
     *
     * @param s   source string
     * @param sub substring to remove
     */
    public  static String remove(String s, String sub) {
        int c      = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do {
            sb.append(s.substring(c, i));
            c = i + sublen;
        } while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            sb.append(s.substring(c, s.length()));
        }
        return sb.toString();
    }

}
