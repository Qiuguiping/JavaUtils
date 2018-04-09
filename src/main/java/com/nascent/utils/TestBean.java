package com.nascent.utils;

/**
 * com.nascent.utils
 *
 * @Author guiping.Qiu
 * @Date 2018/4/9
 */
public class TestBean {

    private String name;
    private Integer age;


    public TestBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
