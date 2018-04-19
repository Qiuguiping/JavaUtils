package com.nascent.utils;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils
 * @Description: TODO
 * @date 2018/4/19 17:11
 */
public class CommonUtils {

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    /**
     * 检查IP
     *
     * @param addr
     * @return
     */
    public boolean checkIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }


    /**
     * 获取指定位数的随机数(纯数字)
     *
     * @param length 随机数的位数
     * @return String
     */
    public static String getRandomNum(int length) {
        if (length <= 0) {
            length = 1;
        }
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        int i = 0;
        while (i < length) {
            res.append(random.nextInt(10));
            i++;
        }
        return res.toString();
    }

    /**
     * 获取指定位数的随机数(各种类型字符混合)
     *
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        //生成随机字符时，字符来源
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        if (length <= 0) {
            length = 1;
        }
        Random random = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < length; i++) {
            res.append(str.charAt(random.nextInt(str.length())));
        }
        return res.toString();
    }

    /**
     * 获取内网iP
     *
     * @return
     * @throws IOException
     */
    private static String getIntranetIP() throws IOException {
        InetAddress ia = InetAddress.getLocalHost();
        return ia.getHostAddress();
    }

    /**
     * 根据主机名获取iP
     *
     * @param strUrl
     * @return
     * @throws UnknownHostException
     */
    public static String getIPByHostName(String strUrl) throws UnknownHostException {
        //获取的是该网站的ip地址，比如我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地址
        InetAddress ia = InetAddress.getByName(strUrl);
        return ia.getHostAddress();
    }


    public static String getExtranetIP() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getExtranetIP());
    }

}
