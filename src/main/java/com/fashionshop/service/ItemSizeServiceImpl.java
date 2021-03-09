package com.fashionshop.service;

import com.fashionshop.entity.ItemSizeEntity;
import com.fashionshop.entity.SizeEntity;
import com.fashionshop.repository.ItemSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSizeServiceImpl implements ItemSizeService {
    @Autowired
    private ItemSizeRepository itemSizeRepository;

    @Override
    public List<ItemSizeEntity> findAllItemSizeEntity() {
        return itemSizeRepository.findAll();
    }

    @Override
    public List<SizeEntity> findItemSizeEntitiesByItem_Id(int id) {
        return itemSizeRepository.findItemSizeEntitiesByItem_Id(id);
    }

    @Override
    public List<ItemSizeEntity> findAllItemSizeEntitiesByItem_Id(int itemId) {
        return itemSizeRepository.findAllItemSizeEntitiesByItem_Id(itemId);
    }

}
