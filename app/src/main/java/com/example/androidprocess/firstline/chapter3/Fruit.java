package com.example.androidprocess.firstline.chapter3;

/**
 * @author chendashan
 * @date 2022/5/17
 */
public class Fruit {
    private String name;
    private int recId;

    public Fruit(String name, int recId) {
        this.name = name;
        this.recId = recId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }
}
