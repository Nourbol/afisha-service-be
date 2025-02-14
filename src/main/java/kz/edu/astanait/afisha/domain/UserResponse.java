package kz.edu.astanait.afisha.domain;

import java.util.UUID;

public record UserResponse(UUID id,
                           String fullName,
                           String email) {
}