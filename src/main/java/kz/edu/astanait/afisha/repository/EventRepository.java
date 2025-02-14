package kz.edu.astanait.afisha.repository;

import kz.edu.astanait.afisha.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    Page<EventEntity> findAllByTitleContaining(String title, Pageable pageable);

    Page<EventEntity> findAllByCategory_Id(UUID categoryId, Pageable pageable);
}
