package kz.edu.astanait.afisha.repository;

import kz.edu.astanait.afisha.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

    Optional<TokenEntity> findByHashAndExpiredAtAfter(byte[] hash, LocalDateTime expiredAtBefore);

    void deleteAllByExpiredAtBefore(LocalDateTime createdAtBefore);
}