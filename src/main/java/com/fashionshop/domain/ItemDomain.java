package com.fashionshop.domain;

public class ItemDomain {
    private int idItem;
    private String name;
    private String imgSrc;

    public ItemDomain() {

    }

    public ItemDomain(int idItem, String name, String imgSrc) {
        this.idItem = idItem;
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
