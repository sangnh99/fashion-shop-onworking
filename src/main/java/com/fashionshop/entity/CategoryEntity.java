package com.fashionshop.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "category")
public class CategoryEntity{
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity(){

    }

    public CategoryEntity(int id, String name){
        this.id = id;
        this.name = name;
    }
}
