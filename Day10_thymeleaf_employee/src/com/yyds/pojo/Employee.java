package com.yyds.pojo;

import com.yyds.servlet.ViewBaseServlet;

public class Employee {
    private Integer eid;

    private String name;

    private Integer age;

    private String email;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(Integer eid, String name, Integer age, String email) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Employee() {
    }
}
