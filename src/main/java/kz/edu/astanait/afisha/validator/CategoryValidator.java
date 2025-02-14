package kz.edu.astanait.afisha.validator;

import kz.edu.astanait.afisha.exception.RecordNotFoundException;
import kz.edu.astanait.afisha.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public void assertExistence(final UUID categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw RecordNotFoundException.categoryNotFound(categoryId);
        }
    }
}
