package kz.edu.astanait.afisha.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventPreview(UUID id,
                           String title,
                           LocalDateTime startTime,
                           String location,
                           BigDecimal price) {
}
