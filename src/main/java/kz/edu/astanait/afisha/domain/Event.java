package kz.edu.astanait.afisha.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Event(UUID id,
                    String title,
                    String description,
                    LocalDateTime startTime,
                    String location,
                    BigDecimal price,
                    Category category) {
}
