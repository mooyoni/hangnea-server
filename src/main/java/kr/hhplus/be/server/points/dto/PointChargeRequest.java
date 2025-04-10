package kr.hhplus.be.server.points.dto;

import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.common.enums.ChargeDirection;

public record PointChargeRequest(
        Long userId,
        @Min(value = 0, message = "Amount must be a positive number or zero")
        int amount,
        ChargeDirection chargeDirection
) {
}
