package kz.edu.astanait.afisha.domain;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;

public record CategorySaveRequest(@NotNull
                                  @Length(min = 2, max = 100)
                                  String name) {
}
