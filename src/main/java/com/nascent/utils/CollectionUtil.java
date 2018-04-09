package com.nascent.utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/4/9
 */
public class CollectionUtil {



    /**
     * 泛型list转换为数组
     * @param src List<T> 原List
     * @return T[] 转换后的数组
     */
    public static <T> T[] listToArray(List<T> src, Class<T> type) {
        if (listIsNullOrEmpty(src)) {
            return null;
        }
        // 初始化泛型数组
        // JAVA中不允许这样初始化泛型数组： T[] dest = new T[src.size()];
        T[] dest = (T[]) Array.newInstance(type, src.size());
        for (int i = 0; i < src.size(); i++) {
            dest[i] = src.get(i);
        }
        return dest;
    }

    /**
     * 泛型嵌套list转换为二维数组
     * @param src List<List<T>> 原嵌套list （子list的长度必须相等）
     * @return T[][] 转换后的二维数组
     */
    public static <T> T[][] listsToArrays(List<List<T>> src, Class<T> type) {

        if (listIsNullOrEmpty(src)) {
            return null;
        }

        // 初始化泛型二维数组
        // JAVA中不允许这样初始化泛型二维数组： T[][] dest = new T[src.size()][];
        T[][] dest = (T[][]) Array.newInstance(type, src.size(), src.get(0).size());

        for (int i = 0; i < src.size(); i++) {
            for (int j = 0; j < src.get(i).size(); j++) {
                dest[i][j] = src.get(i).get(j);
            }
        }

        return dest;
    }

    /**
     * 判断list是否为null 或 size == 0
     * @param list
     * @return
     */
    public static boolean listIsNullOrEmpty(List<?> list){
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * 泛型数组转换为List
     * @param a
     * @param <T>
     * @return
     */
    public static  <T> List<T> array2List(T...a){
        List<T> list = new ArrayList<>();
        for(T t : a){
            list.add(t);
        }
        return list;
    }

    /**
     * 泛型Set转换为List
     * @param set
     * @param <T>
     * @return
     */
    public static <T> List<T> set2List(Set<T> set){
        return new ArrayList<>(set);
    }

    /**
     * 泛型List转换为Set
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Set<T> list2Set(List<T> list){
        return new HashSet<>(list);
    }




}
