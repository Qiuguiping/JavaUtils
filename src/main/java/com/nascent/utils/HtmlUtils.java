package com.nascent.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils
 * @Description: TODO
 * @date 2018/4/20 11:03
 */
public class HtmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtils.class);

    private HtmlUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 删除所有的HTML标签
     *
     * @param source 需要进行除HTML的文本
     * @return
     */
    public static String deleteAllHTMLTag(String source) {

        if (source == null) {
            return "";
        }

        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }


    /**
     * 过滤HTML标签输出文本
     *
     * @param inputString 原字符串
     * @return 过滤后字符串
     */
    public static String Html2Text(String inputString) {
        if (StringUtils.isEmpty(inputString)) {
            return "";
        }

        // 含html标签的字符串
        String htmlStr = inputString.trim();
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_space;
        Matcher m_space;
        Pattern p_escape;
        Matcher m_escape;

        try {
            // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

            // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";

            // 定义空格回车换行符
            String regEx_space = "\\s*|\t|\r|\n";

            // 定义转义字符
            String regEx_escape = "&.{2,6}?;";

            // 过滤script标签
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");

            // 过滤style标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");

            // 过滤html标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");

            // 过滤空格回车标签
            p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
            m_space = p_space.matcher(htmlStr);
            htmlStr = m_space.replaceAll("");

            // 过滤转义字符
            p_escape = Pattern.compile(regEx_escape, Pattern.CASE_INSENSITIVE);
            m_escape = p_escape.matcher(htmlStr);
            htmlStr = m_escape.replaceAll("");

            textStr = htmlStr;

        } catch (Exception e) {
            logger.info("Html2Text:{}", e.getMessage());
        }

        // 返回文本字符串
        return textStr;
    }

    /**
     * HTML格式转换 * * @param str * @return
     */
    public static final String htmlFilter(String str) {
        if (str == null) {
            return " ";
        }
        char toCompare;
        StringBuffer replaceChar = new StringBuffer(str.length() + 256);
        int maxLength = str.length();
        try {
            for (int i = 0; i < maxLength; i++) {
                toCompare = str.charAt(i);
                // 所有的 " 替换成 : &;quot;
                if (toCompare == '"') {
                    replaceChar.append("&;quot;");
                }
                // 所有的 < 替换成: &;lt;
                else if (toCompare == '<') {
                    replaceChar.append("&;lt;");
                }
                // 所有的 > 替换成: &;gt;
                else if (toCompare == '>') {
                    replaceChar.append("&;gt;");
                }
                // 所有的 &; 替换成: &;amp;
                else if (toCompare == '&') {
                    if (i < maxLength - 1) {
                        if (str.charAt(i + 1) == '#') {
                            replaceChar.append("&;#");
                        } else {
                            replaceChar.append("&;amp;");
                        }
                        i++;
                    }
                } else if (toCompare == ' ') {
                    replaceChar.append(" ");
                }
                // 所有的 /r/n (using System.getProperty("line.separator") to get // it ) 替换成 lihjk
                else if (toCompare == '/') {
                    if (i < maxLength - 1) {
                        if (str.charAt(i + 1) == 'r') {
                            replaceChar.append(" ");
                        } else if (str.charAt(i + 1) == 'n') {
                            replaceChar.append(" ");
                        }
                        i++;
                    }
                    replaceChar.append(" ");
                } else {
                    replaceChar.append(toCompare);
                }
            }
            // end for
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return replaceChar.toString();
    }
}
