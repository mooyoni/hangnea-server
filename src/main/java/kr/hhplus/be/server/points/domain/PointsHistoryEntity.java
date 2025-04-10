package kr.hhplus.be.server.points.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.hhplus.be.server.common.enums.ChargeDirection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PointsHistoryEntity {
    @Id
    Long id;
    Long userId;
    int amount;
    ChargeDirection chargeDirection;
    LocalDateTime chargedAt;

    public PointsHistoryEntity(
            Long userId,
            int amount,
            ChargeDirection chargeDirection,
            LocalDateTime chargedAt
    ) {
        this.userId = userId;
        this.amount = amount;
        this.chargeDirection = chargeDirection;
        this.chargedAt = chargedAt;
    }
}
