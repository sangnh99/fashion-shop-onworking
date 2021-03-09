package com.fashionshop.entity;

import java.util.List;

public class ItemAddProduct {
    private String name;
    private String detail;
    private Integer price;
    private String category;
    private List<String> sizes;

    public ItemAddProduct(){

    }

    public ItemAddProduct(String name, String detail, Integer price, String category, List<String> sizes) {
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.category = category;
        this.sizes = sizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }
}
