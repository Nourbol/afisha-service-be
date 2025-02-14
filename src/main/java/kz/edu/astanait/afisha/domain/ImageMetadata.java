package kz.edu.astanait.afisha.domain;

import java.util.UUID;

public record ImageMetadata(UUID id,
                            String name,
                            String type) {
}
