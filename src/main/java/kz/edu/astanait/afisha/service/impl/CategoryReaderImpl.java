package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.Category;
import kz.edu.astanait.afisha.entity.CategoryEntity;
import kz.edu.astanait.afisha.mapper.CategoryMapper;
import kz.edu.astanait.afisha.repository.CategoryRepository;
import kz.edu.astanait.afisha.service.CategoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategories() {
        final List<CategoryEntity> categories = categoryRepository.findAll();
        return categoryMapper.mapToCategoryList(categories);
    }
}
