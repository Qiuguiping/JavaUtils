package com.nascent.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.SpringProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils
 * @Description: 取得属性和信息的公共类。如果属性不存在返回null，如果信息不存在返回 "" ，缓存属性文件相关信息<BR>
 * @date 2018/4/19 14:51
 */
public class PropertiesUtils {
    /**
     * 默认访问的文件名称
     */
    private static final String PROPERTIES_BASE_NAME = "base";
    /**
     * 布尔型的字符串标示，“否”/false统一使用 0
     */
    public static final String BOOLEAN_FALSE = "0";
    public static final String BOOLEAN_FALSE_STR = "false";
    /**
     * 布尔型的字符串标示，“是”/true统一使用 1
     */
    public static final String BOOLEAN_TRUE = "1";
    public static final String BOOLEAN_TRUE_STR = "true";

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    /**
     * 缓存信息，其中的value的类型是java.util.Map
     */
    private static Map bundleLocaleCache = new HashMap();

    /**
     * 缓存属性，其中的value的类型是java.util.Properties
     */
    private static Map propertyCache = new HashMap();

    private PropertiesUtils(){}

    /**
     * 取得指定属性文件中的属性
     *
     * @param resource 属性文件
     * @param name     属性key
     * @return 属性值
     */
    public static String getProperty(String resource, String name) {
        if (StringUtils.isBlank(resource) || StringUtils.isBlank(name)) {
            return null;
        }
        Properties props = (Properties) propertyCache.get(resource);
        if (props == null) {
            synchronized (propertyCache) {
                props = (Properties) propertyCache.get(resource);
                if (props == null) {
                    props = getBaseProperties(resource);
                }
            }
            if (props == null) {
                return null;
            }
        }
        //modify by teison at 20110826 从属性文件读出来后，去空格
        String rs = props.getProperty(name);
        if (!StringUtils.isEmpty(rs)) {
            rs = rs.trim();
        }
        //end modify
        return rs;
    }

    /**
     * 取得 默认配置文件中的属性
     *
     * @param name key
     * @return 对应的字符串
     */
    public static String getProperty(String name) {

        String value = getProperty(PROPERTIES_BASE_NAME, name);
        if (value == null) {
            return PropertiesSource.getProperty(name);
        }
        return value;
    }

    /**
     * 取得base.properties中的属性值
     *
     * @param name         key
     * @param defaultValue 配置项的缺省值
     * @return name对应的value
     */
    public static String getPropertyValue(String name, String defaultValue) {
        String value = getProperty(name);
        return (value == null ? defaultValue : value);
    }

    /**
     *   取得指定属性文件中的属性值
     * @param resource
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getPropertyValue(String resource,String name, String defaultValue) {
        String value = getProperty(resource,name);
        return (value == null ? defaultValue : value);
    }




    /**
     * 取得base.properties中的属性值
     *
     * @param name key
     * @return name对应的属性值，布尔型。为1或true时，返回true，其它（包括出错）返回false
     */
    public static boolean getPropertyBool(String name) {
        String value = getProperty(name);
        try {
            if (value != null) {
                value = value.trim();
            }
            return (BOOLEAN_TRUE.equals(value) || BOOLEAN_TRUE_STR.compareToIgnoreCase(value) == 0);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 取得base.properties中的属性值
     *
     * @param name         key
     * @param defaultValue 配置项的缺省值
     * @return name对应的属性值，布尔型。为null时，返回缺省值;
     * 为1或true时，返回true，其它（包括出错）返回false
     */
    public static boolean getPropertyBool(String name, boolean defaultValue) {
        String value = getPropertyValue(name, String.valueOf(defaultValue));
        try {
            return (value.equals(BOOLEAN_TRUE) || value.compareToIgnoreCase(BOOLEAN_TRUE_STR) == 0);
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean getPropertyBoolByResource(String resource, String name) {
        String value = getProperty(resource, name);
        try {
            return (value.equals(BOOLEAN_TRUE) || value.compareToIgnoreCase(BOOLEAN_TRUE_STR) == 0);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 取得tfm-systeminfo.properties中的属性值
     *
     * @param name key
     * @return name对应的属性值，整型。出错返回0
     */
    public static int getPropertyInt(String name) {
        String value = getProperty(name);
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 取得tfm-systeminfo.properties中的属性值
     *
     * @param name         key
     * @param defaultValue 配置项的缺省值
     * @return name对应的属性值，整型。如果返回的属性值为null，取defaultValue，出错返回0
     */
    public static int getPropertyInt(String name, int defaultValue) {
        String value = getPropertyValue(name, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 取得tfm-systeminfo.properties中的属性值
     *
     * @param name key
     * @return name对应的属性值，浮点型。出错返回0.0
     */
    public static float getPropertyFloat(String name) {
        String value = getProperty(name);
        try {
            return Float.parseFloat(value);
        } catch (Exception ex) {
            return 0.0F;
        }
    }

    /**
     * 取得tfm-systeminfo.properties中的属性值
     *
     * @param name         key
     * @param defaultValue 配置项的缺省值
     * @return name对应的属性值，浮点型。如果返回的属性值为null，取defaultValue，出错返回0.0
     */
    public static float getPropertyFloat(String name, float defaultValue) {
        String value = getPropertyValue(name, String.valueOf(defaultValue));
        try {
            return Float.parseFloat(value);
        } catch (Exception ex) {
            return 0.0F;
        }
    }

    /**
     * 取得tfm-systeminfo.properties中的属性值
     *
     * @param name key
     * @return name对应的属性值，浮点型。出错返回0.0
     */
    public static double getPropertyDouble(String name) {
        String value = getProperty(name);
        try {
            return Double.parseDouble(value);
        } catch (Exception ex) {
            return 0.0F;
        }
    }

    /**
     * 取得tfm-systeminfo.properties中的属性值
     *
     * @param name         key
     * @param defaultValue 配置项的缺省值
     * @return name对应的属性值，浮点型。如果返回的属性值为null，取defaultValue，出错返回0.0
     */
    public static double getPropertyDouble(String name, double defaultValue) {
        String value = getPropertyValue(name, String.valueOf(defaultValue));
        try {
            return Double.parseDouble(value);
        } catch (Exception ex) {
            return 0.0F;
        }
    }

    /**
     * 取得一组属性的值，如果处在则缓存propertyCache
     *
     * @param resource 属性文件的名称
     * @return java.util.Properties形式的属性
     */
    protected static Properties getBaseProperties(String resource) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try {
            logger.info("Found properties file: {}", loader.getResource(resource + ".properties"));
            BufferedReader bf = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(resource + ".properties"), "UTF-8"));
            props.load(bf);
        } catch (Throwable t) {
            props = null;
        }
        if (props != null) {
            propertyCache.put(resource, props);
        }
        return props;
    }

    /**
     * 取得<tt>ResourceBundle</tt>
     */
    public static ResourceBundle getResourceBundle(String resource, Locale locale) {
        Map bundleMap = getResourceBundleMap(resource, locale);
        if (bundleMap == null) {
            return null;
        }
        return (ResourceBundle) bundleMap.get("_RESOURCE_BUNDLE_");
    }

    /**
     * 返回以Map形式保存的resources
     */
    public static Map getResourceBundleMap(String resource, Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null");
        }
        String resourceCacheKey = resource + "_" + locale.toString();
        Map bundleMap = (Map) bundleLocaleCache.get(resourceCacheKey);
        if (bundleMap == null) {
            ResourceBundle bundle = getBaseResourceBundle(resource, locale);
            bundleMap = resourceBundleToMap(bundle);
            if (bundleMap != null) {
                bundleLocaleCache.put(resourceCacheKey, bundleMap);
            }
        }
        return bundleMap;
    }

    protected static ResourceBundle getBaseResourceBundle(String resource, Locale locale) {
        if (resource == null || resource.length() <= 0) {
            return null;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(resource, locale, loader);
        } catch (MissingResourceException e) {
            logger.error("Could not find resource: " + resource +
                    " for locale " + locale.toString() + ": " +
                    e.toString(), e);
            return null;
        }
        if (bundle == null) {
            logger.error("Could not find resource: " + resource +
                    " for locale " + locale.toString());
            return null;
        }
        return bundle;
    }

    protected static Map resourceBundleToMap(ResourceBundle bundle) {
        if (bundle == null) {
            return new HashMap();
        }
        Enumeration keyNum = bundle.getKeys();
        Map resourceBundleMap = new HashMap();
        while (keyNum.hasMoreElements()) {
            String key = (String) keyNum.nextElement();
            Object value = bundle.getObject(key);
            resourceBundleMap.put(key, value);
        }
        resourceBundleMap.put("_RESOURCE_BUNDLE_", bundle);
        return resourceBundleMap;
    }


    /**
     * 配置信息源
     */
    public static abstract class PropertiesSource {
        private final static String[] resource_paths
                = new String[]{"spring.properties", "redis.properties", "elasticsearch.properties", "mysql.properties", "mq.properties"};

        private static final Properties localProperties = new Properties();

        private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

        static {
            for (int i = 0; i < resource_paths.length; i++) {
                readResource(resource_paths[i]);
            }
        }

        private static void readResource(final String resource) {
            try {
                ClassLoader cl = SpringProperties.class.getClassLoader();
                URL url = (cl != null ? cl.getResource(resource) :
                        ClassLoader.getSystemResource(resource));
                if (url != null) {
                    InputStream is = url.openStream();
                    try {
                        localProperties.load(is);
                    } finally {
                        is.close();
                        logger.info("Found properties file: " + url.getPath());
                    }
                }
            } catch (IOException ex) {
                if (logger.isInfoEnabled()) {
                    logger.info("Could not load '" + resource + "' file from local classpath: " + ex);
                }
            }
        }

        public static String getProperty(String key) {
            String value = localProperties.getProperty(key);
            if (value == null) {
                try {
                    value = System.getProperty(key);
                } catch (Throwable ex) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Could not retrieve system property '" + key + "': " + ex);
                    }
                }
            }
            return value;
        }
    }
}
