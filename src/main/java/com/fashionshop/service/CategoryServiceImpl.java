package com.fashionshop.service;

import com.fashionshop.entity.CategoryEntity;
import com.fashionshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

   @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }
}
