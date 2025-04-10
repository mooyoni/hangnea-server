package kr.hhplus.be.server.points.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointsRepository extends JpaRepository<PointsEntity, Long> {
    Optional<PointsEntity> findByUserId(Long userId);
}
