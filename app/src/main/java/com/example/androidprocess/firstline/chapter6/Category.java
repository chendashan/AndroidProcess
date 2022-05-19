package com.example.androidprocess.firstline.chapter6;

import org.litepal.crud.DataSupport;

/**
 * @author chendashan
 * @date 2022/5/19
 */
public class Category extends DataSupport {

    private int id;

    private String categoryName;

    private int categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
