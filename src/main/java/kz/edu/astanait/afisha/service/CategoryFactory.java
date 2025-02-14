package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.Category;
import kz.edu.astanait.afisha.domain.CategorySaveRequest;

public interface CategoryFactory {

    Category createCategory(CategorySaveRequest request);
}
