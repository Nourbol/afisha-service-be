package kz.edu.astanait.afisha.domain;

import java.time.LocalDateTime;

public record GeneratedToken(String token,
                             LocalDateTime expiredAt) {
}