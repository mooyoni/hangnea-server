package kr.hhplus.be.server.points.domain;

import kr.hhplus.be.server.common.enums.ChargeDirection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PointsHistoryEntity {
    Long id;
    Long userId;
    Long amount;
    ChargeDirection chargeDirection;
    LocalDateTime chargedAt;
}
