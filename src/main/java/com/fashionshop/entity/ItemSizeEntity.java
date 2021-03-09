package com.fashionshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_size")
public class ItemSizeEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @Column(name = "amount")
    private int amount;

    public ItemSizeEntity() {

    }

    public ItemSizeEntity(int id, ItemEntity item, SizeEntity size, int amount) {
        this.id = id;
        this.item = item;
        this.size = size;
        this.amount = amount;
    }

    public SizeEntity getSize() {
        return size;
    }

    public void setSize(SizeEntity size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
