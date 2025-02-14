package kz.edu.astanait.afisha.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.afisha.domain.Category;
import kz.edu.astanait.afisha.domain.CategorySaveRequest;
import kz.edu.astanait.afisha.service.CategoryFactory;
import kz.edu.astanait.afisha.service.CategoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@Tag(name = "Categories")
@Validated
@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFactory categoryFactory;
    private final CategoryReader categoryReader;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category createCategory(final @RequestBody @Valid CategorySaveRequest request) {
        return categoryFactory.createCategory(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategories() {
        return categoryReader.getCategories();
    }
}
