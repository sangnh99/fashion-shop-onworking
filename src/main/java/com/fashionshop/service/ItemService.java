package com.fashionshop.service;

import com.fashionshop.domain.ItemDomain;
import com.fashionshop.entity.ItemEntity;

import java.util.List;

public interface ItemService {
    List<ItemDomain> findAllItemDomain();

    ItemEntity findItemEntityById(int id);
}
