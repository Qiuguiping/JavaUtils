//package com.nascent.utils;
//
//import au.com.bytecode.opencsv.CSVWriter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.springframework.util.ReflectionUtils;
//
//import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;
//
//
///**
// * @author guiping.Qiu
// * @version V1.0
// * @Package com.nascent.utils
// * @Description: TODO
// * @date 2018/4/20 11:17
// */
//public class CsvUtils {
//
//    private static Logger logger = LoggerFactory.getLogger(CsvUtils.class);
//
//    private CsvUtils() {
//    }
//
//
//    /**
//     * 生成CSV文件
//     *
//     * @param filePath 文件保存路径，例如：D:/temp/test.csv
//     * @param beans    实体对象集合
//     */
//    public static <T> void createFile(String filePath, List<T> beans) {
//        CSVWriter writer = null;
//        try {
//            // 创建文件对象
//            File file = createFile(filePath);
//            // 生成文件
//            writer = new CSVWriter(new FileWriter(filePath), ',');
//            // 获取内容
//            List<String[]> contents = getStringArrayFromBean(beans);
//            // 写入内容
//            for (String[] each : contents) {
//                writer.writeNext(each);
//            }
//        } catch (Exception e) {
//            logger.error("生成CSV文件失败", e);
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
////    /**
////     * 读取CSV文件内容
////     *
////     * @param filePath 文件存放的路径，如：D:/csv/xxx.csv
////     * @param bean     类类型
////     * @return List<T>
////     */
////    public static <T> List<T> readFile(String filePath, Class<T> bean) {
////        List<String[]> dataList = new ArrayList<String[]>();
////        CsvReader reader = null;
////        try {
////            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
////            reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
////            if (reader != null) {
////                // 跳过表头，如果需要表头的话，这句可以忽略
////                //reader.readHeaders();
////                // 逐行读入除表头的数据
////                while (reader.readRecord()) {
////                    dataList.add(reader.getValues());
////                }
////                if (!dataList.isEmpty()) {
////                    // 数组转对象
////                    return getBeanFromStringArray(dataList, bean);
////                }
////            }
////        } catch (Exception e) {
////            logger.error("读取CSV文件失败", e);
////        } finally {
////            if (reader != null) {
////                reader.close();
////            }
////        }
////        return Collections.emptyList();
////    }
////
////    /**
////     * 删除该目录下所有文件
////     *
////     * @param filePath 文件目录路径，如：d:/test
////     */
////    public static boolean deleteFiles(String filePath) {
////        File file = new File(filePath);
////        if (file.exists()) {
////            File[] files = file.listFiles();
////            if (files != null && files.length > 0) {
////                for (File f : files) {
////                    if (f.isFile() && f.delete()) {
////                        logger.info("删除" + f.getName() + "文件成功");
////                    }
////                }
////                return true;
////            }
////        }
////        return false;
////    }
////
////    /**
////     * 删除单个文件
////     *
////     * @param filePath 文件目录路径，如：d:/test
////     * @param fileName 文件名称，如：110.csv
////     */
////    public static boolean deleteFile(String filePath, String fileName) {
////        File file = new File(filePath);
////        if (file.exists()) {
////            File[] files = file.listFiles();
////            if (files != null && files.length > 0) {
////                for (File f : files) {
////                    if (f.isFile() && f.getName().equals(fileName)) {
////                        return f.delete();
////                    }
////                }
////            }
////        }
////        return false;
////    }
////
////    /**
////     * 泛型实体转换为数组
////     *
////     * @param beans
////     * @return List<String   [   ]>
////     */
//    private static <T> List<String[]> getStringArrayFromBean(List<T> beans) {
//        List<String[]> result = new ArrayList<String[]>();
//        Class<? extends Object> cls = beans.get(0).getClass();
//        Field[] declaredFields = cls.getDeclaredFields();
//        List<Field> annoFields = new ArrayList<Field>();
//        // 筛选出标有注解的字段
//        for (Field field : declaredFields) {
//            CSVField anno = field.getAnnotation(CSVField.class);
//            if (anno != null) {
//                annoFields.add(field);
//            }
//        }
//        // 获取注解的值，即内容标题
//        String[] title = new String[annoFields.size()];
//        for (int i = 0; i < annoFields.size(); i++) {
//            title[i] = declaredFields[i].getAnnotation(CSVField.class).name();
//        }
//        result.add(title);
//        try {
//            // 获取内容
//            for (T t : beans) {
//                String[] item = new String[annoFields.size()];
//                int index = 0;
//                for (Field field : annoFields) {
//                    String fieldName = field.getName();
//                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//                    Method method = ReflectionUtils.findMethod(t.getClass(), methodName);
//                    if (method != null) {
//                        Object value = ReflectionUtils.invokeMethod(method, t);
//                        if (value == null) {
//                            item[index] = "";
//                        } else {
//                            item[index] = value.toString();
//                        }
//                    }
//                    index++;
//                }
//                result.add(item);
//            }
//        } catch (Exception e) {
//            logger.info("实体对象转数组失败", e);
//        }
//        return result;
//    }
////
////    /**
////     * 数组转为对象集合
////     *
////     * @param dataList
////     * @param bean
////     * @return List<T>
////     */
////    private static <T> List<T> getBeanFromStringArray(List<String[]> dataList, Class<T> bean) {
////        List<T> list = new ArrayList<>();
////        List<Map<String, String>> titles = getTitles(dataList);
////        Map<String, Field> fields = getFields(bean);
////        try {
////            for (Map<String, String> map : titles) {
////                T t = bean.newInstance();
////                for (Entry<String, String> entry : map.entrySet()) {
////                    if (fields.containsKey(entry.getKey())) {
////                        Field field = fields.get(entry.getKey());
////                        Class<?> valType = field.getType();
////                        String fieldName = field.getName();
////                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
////                        Method method = ReflectionUtils.findMethod(bean, methodName, valType);
////                        if (method != null) {
////                            ReflectionUtils.invokeMethod(method, t, entry.getValue());
////                        }
////                    }
////                }
////                list.add(t);
////            }
////        } catch (Exception e) {
////            logger.error("创建实体失败", e);
////        }
////        return list;
////    }
////
////    /**
////     * 数组标题与值的对应关系
////     *
////     * @param dataList
////     * @return
////     */
////    private static <T> List<Map<String, String>> getTitles(List<String[]> dataList) {
////        List<Map<String, String>> list = new ArrayList<>();
////        String[] titles = dataList.get(0);
////        dataList.remove(0);
////        for (String[] values : dataList) {
////            Map<String, String> titleMap = new HashMap<>();
////            for (int i = 0; i < values.length; i++) {
////                titleMap.put(titles[i], values[i]);
////            }
////            list.add(titleMap);
////        }
////        return list;
////    }
////
////    /**
////     * 注解名称与字段属性的对应关系
////     *
////     * @param clazz 实体对象类类型
////     * @param <T>   泛型类型
////     * @return Map<String   ,   Field>
////     */
////    private static <T> Map<String, Field> getFields(Class<T> clazz) {
////        Map<String, Field> annoMap = new HashMap<>();
////        Field[] fileds = clazz.getDeclaredFields();
////        for (Field filed : fileds) {
////            CSVField anno = filed.getAnnotation(CSVField.class);
////            if (anno != null) {
////                // 获取name属性值
////                if (StringUtils.isNotBlank(anno.name())) {
////                    annoMap.put(anno.name(), filed);
////                }
////            }
////        }
////        return annoMap;
////    }
////
////    /**
////     * 创建文件对象
////     *
////     * @param filePath 文件路径，例如：temp/test.csv
////     * @return File
////     */
////    private static File createFile(String filePath) {
////        File file = null;
////        try {
////            // 创建文件目录
////            file = new File(filePath.substring(0, filePath.lastIndexOf('/')));
////            if (!file.exists()) {
////                file.mkdirs();
////            }
////            // 创建文件对象
////            file = new File(filePath);
////            if (!file.exists() && file.createNewFile()) {
////                logger.info("创建文件对象成功");
////            }
////        } catch (IOException e) {
////            logger.error("创建文件对象失败", e);
////        }
////        return file;
////    }
//}
