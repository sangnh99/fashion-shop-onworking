package com.fashionshop.service;

import com.fashionshop.entity.CategoryEntity;
import com.fashionshop.repository.CategoryRepository;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategory();
}
