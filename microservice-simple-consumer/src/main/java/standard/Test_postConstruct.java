package com.wondersgroup.standard;

import javax.annotation.PostConstruct;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class Test_postConstruct {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        this.name = "张三";
        System.out.println("set name:张三");
    }

    @Override
    public String toString() {
        return "Test_postConstruct{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Test_postConstruct test_postConstruct = new Test_postConstruct();
        System.out.println(test_postConstruct);
    }
}
