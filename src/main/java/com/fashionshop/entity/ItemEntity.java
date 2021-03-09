package com.fashionshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
public class ItemEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "item_detail")
    private String itemDetail;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplierEntity;

    @Column(name = "isdetele")
    private int isDeleted;

    @Column(name = "is_special_collection")
    private int isSpecialCollection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEnity saleEnity;

    public ItemEntity() {

    }


    public ItemEntity(int id, String name, Long price, String itemDetail, String image, SupplierEntity supplierId, int isDeleted, int isSpecialCollection, CategoryEntity categoryId, SaleEnity saleId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.itemDetail = itemDetail;
        this.image = image;
        this.supplierEntity = supplierId;
        this.isDeleted = isDeleted;
        this.isSpecialCollection = isSpecialCollection;
        this.categoryEntity = categoryId;
        this.saleEnity = saleId;
    }

    public ItemEntity(UserEntity createBy, UserEntity updateBy, LocalDate createDate, LocalDate updateDate) {
        super(createBy, updateBy, createDate, updateDate);
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SupplierEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsSpecialCollection() {
        return isSpecialCollection;
    }

    public void setIsSpecialCollection(int isSpecialCollection) {
        this.isSpecialCollection = isSpecialCollection;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public SaleEnity getSaleEnity() {
        return saleEnity;
    }

    public void setSaleEnity(SaleEnity saleEnity) {
        this.saleEnity = saleEnity;
    }

    public double getCurrentPrice() {
        return this.price * (100 - this.saleEnity.getPercent()) / 100;
    }

}
