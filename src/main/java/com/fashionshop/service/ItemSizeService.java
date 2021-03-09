package com.fashionshop.service;

import com.fashionshop.entity.ItemSizeEntity;
import com.fashionshop.entity.SizeEntity;

import java.util.List;

public interface ItemSizeService {
    List<ItemSizeEntity> findAllItemSizeEntity();
    List<SizeEntity> findItemSizeEntitiesByItem_Id(int id);
    List<ItemSizeEntity> findAllItemSizeEntitiesByItem_Id(int itemId);
}
