package kz.edu.astanait.afisha.mapper;

import kz.edu.astanait.afisha.domain.Category;
import kz.edu.astanait.afisha.domain.CategorySaveRequest;
import kz.edu.astanait.afisha.entity.CategoryEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CategoryMapper {

    public Category mapToCategory(final CategoryEntity category) {
        return new Category(
                category.getId(),
                category.getName()
        );
    }

    public List<Category> mapToCategoryList(final List<CategoryEntity> categories) {
        return categories.stream().map(this::mapToCategory).toList();
    }

    public CategoryEntity mapToCategoryEntity(final CategorySaveRequest request) {
        final CategoryEntity category = new CategoryEntity();
        category.setName(request.name());
        return category;
    }
}
