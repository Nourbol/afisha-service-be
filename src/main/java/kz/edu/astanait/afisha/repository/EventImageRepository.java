package kz.edu.astanait.afisha.repository;

import kz.edu.astanait.afisha.entity.EventImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventImageRepository extends JpaRepository<EventImageEntity, UUID> {

    boolean existsByEventId(UUID eventId);

    Optional<EventImageEntity> findByEventIdAndIsCoverTrue(UUID eventId);

    void deleteByEventIdAndImageId(UUID eventId, UUID imageId);
}
