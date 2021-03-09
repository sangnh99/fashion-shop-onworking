package com.fashionshop.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rating")
public class RatingEntity extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "id_rate",nullable = false)
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @Column(name = "rate_star")
    private int rateStar;

    @Column(name = "isdelete")
    private int isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id",nullable = false)
    private ItemEntity item;

    public RatingEntity(UserEntity createBy, UserEntity updateBy, LocalDate createDate, LocalDate updateDate) {
        super(createBy, updateBy, createDate, updateDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getRateStar() {
        return rateStar;
    }

    public void setRateStar(int rateStar) {
        this.rateStar = rateStar;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
    public RatingEntity(){

    }

    public RatingEntity(int id, String comment, UserEntity user, int rateStar, int isDeleted, ItemEntity item) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.rateStar = rateStar;
        this.isDeleted = isDeleted;
        this.item = item;
    }
}
