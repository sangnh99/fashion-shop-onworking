package com.fashionshop.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "size")
public class SizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "size_id")
    private int id;

    @Column(name = "size_name")
    private String name;

    @Column(name = "isdelete")
    private int isDeleted;

    public SizeEntity(int id, String name, int isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public SizeEntity() {

    }


}
