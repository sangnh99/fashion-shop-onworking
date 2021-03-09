package com.fashionshop.service;

import com.fashionshop.domain.ItemDomain;
import com.fashionshop.entity.ItemEntity;
import com.fashionshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<ItemDomain> findAllItemDomain() {
        return itemRepository.findAll().stream().
                map(i -> new ItemDomain(i.getId(), i.getName(), i.getImage()))
                .collect(Collectors.toList());
    }

    @Override
    public ItemEntity findItemEntityById(int id) {
        return itemRepository.findById(id).orElse(null);
    }
}
