package com.fashionshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "src")
    private String src;

    @ManyToOne
    @JoinColumn (name="item_id",nullable = true)
    private ItemEntity item_id;

    public ImageEntity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public ItemEntity getItem_id() {
        return item_id;
    }

    public void setItem_id(ItemEntity item_id) {
        this.item_id = item_id;
    }

    public ImageEntity(int id, String src, ItemEntity item_id) {
        this.id = id;
        this.src = src;
        this.item_id = item_id;
    }
}
