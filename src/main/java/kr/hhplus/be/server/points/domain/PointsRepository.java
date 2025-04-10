package kr.hhplus.be.server.points.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointsRepository extends JpaRepository<PointsEntity, Long> {
    Optional<PointsEntity> findByUserId(Long userId);
}
