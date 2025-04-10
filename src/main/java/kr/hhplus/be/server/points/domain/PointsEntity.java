package kr.hhplus.be.server.points.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointsEntity {
    private Long id;
    private Long userId;
    private Integer points;

    public PointsEntity(
            Long id,
            Long userId,
            Integer points
    ) {
        this.id = id;
        this.userId = userId;
        this.points = points;
    }

    public void addPoints(int amount) {
        this.points += amount;
    }

    public void usePoints(int amount) {
        this.points -= amount;
    }
}