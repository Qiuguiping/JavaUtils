package com.nascent.utils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils
 * @Description: TODO
 * @date 2018/4/19 16:56
 */
public class XmlUtils {

    public static JSON xml2json(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xml);
        return json;
    }

    public static String json2xml(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        String xml = new XMLSerializer().write(jsonObject);
        return xml;
    }

}
