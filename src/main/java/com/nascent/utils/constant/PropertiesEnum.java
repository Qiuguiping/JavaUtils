package com.nascent.utils.constant;

/**
 * @author guiping.Qiu
 * @version V1.0
 * @Package com.nascent.utils.constant
 * @Description: TODO
 * @date 2018/4/19 15:22
 */
public enum PropertiesEnum {

    PUBLIC_SALT("public_salt", "加密公盐");

    private String value;
    private String name;

    PropertiesEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
