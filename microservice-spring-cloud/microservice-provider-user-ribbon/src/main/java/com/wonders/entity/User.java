package com.wonders.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
//JsonIgnoreProperties注解忽略Hibernate的延迟加载的一些属性["hibernateLazyInitializer", "handler", "fieldHandler"]，这些属性在实体类里没有所以要忽略掉，否则会报错：
//报错：com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User implements Serializable {
    private static final long serialVersionUID = 6553698929416839235L;
    private Long id;
    private String username;
    private String name;
    private Short age;
    private BigDecimal balance;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //在getter上加JsonIgnoreProperties是声明在序列化时忽略这个属性类的某个属性，避免对象互相引用造成死循环
    //@JsonIgnoreProperties(ignoreUnknown = true, value = {"bids"})
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Column
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
