package com.fashionshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "isdelete")
    private int isDeleted;

    public RoleEntity() {

    }

    public RoleEntity(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
