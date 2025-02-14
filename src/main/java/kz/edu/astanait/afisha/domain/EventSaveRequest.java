package kz.edu.astanait.afisha.domain;

import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventSaveRequest(@NotNull
                               @Length(min = 2, max = 100)
                               String title,
                               @NotBlank
                               String description,
                               LocalDateTime startTime,
                               @NotNull
                               @Length(min = 2, max = 256)
                               String location,
                               @NotNull
                               @Length(min = 2, max = 100)
                               String shortLocation,
                               @Min(10L)
                               BigDecimal price,
                               @NotNull
                               UUID categoryId) {
}
