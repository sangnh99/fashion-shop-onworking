package com.fashionshop.service;

import com.fashionshop.entity.SizeEntity;
import com.fashionshop.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<SizeEntity> findAllSizeEntity() {
        return sizeRepository.findAll();
    }
}
