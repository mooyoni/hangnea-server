package kr.hhplus.be.server.points.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsHistoryRepository extends JpaRepository<PointsHistoryEntity, Long> {
}
