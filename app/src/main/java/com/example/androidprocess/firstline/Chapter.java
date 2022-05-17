package com.example.androidprocess.firstline;

public class Chapter {
    private String name;
    private Class<?> cls;

    public Chapter(String name, Class<?> cls) {
        this.name = name;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
