package cn.gy.serial.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 序列化测试对象
 * Created by gaoyang on 2017/12/8.
 */
public class Person implements Serializable{

    private int age;
    private String name;
    private transient String state;

    public Person() {
    }

    public Person(int age, String name, String state) {
        this.age = age;
        this.name = name;
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
