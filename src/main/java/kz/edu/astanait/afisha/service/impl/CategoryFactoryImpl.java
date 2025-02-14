package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.Category;
import kz.edu.astanait.afisha.domain.CategorySaveRequest;
import kz.edu.astanait.afisha.entity.CategoryEntity;
import kz.edu.astanait.afisha.mapper.CategoryMapper;
import kz.edu.astanait.afisha.repository.CategoryRepository;
import kz.edu.astanait.afisha.service.CategoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFactoryImpl implements CategoryFactory {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category createCategory(final CategorySaveRequest request) {
        final CategoryEntity category = categoryMapper.mapToCategoryEntity(request);
        final CategoryEntity savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToCategory(savedCategory);
    }
}
