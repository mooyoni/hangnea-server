package kr.hhplus.be.server.points.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.hhplus.be.server.points.dto.PointChargeRequest;
import kr.hhplus.be.server.points.dto.PointUseRequest;
import kr.hhplus.be.server.points.service.PointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/points")
@RestController
public class PointsController {
    private final PointsService pointsService;

    @Operation(summary = "유저 포인트 충전 api")
    @PostMapping
    public ResponseEntity<Void> charge(
        @Valid @RequestBody PointChargeRequest request
    ){
        pointsService.chargePoints(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저 포인트 사용 api")
    @PostMapping("/payments")
    public ResponseEntity<Void> use(
            @Valid @RequestBody PointUseRequest request
    ) {
        pointsService.processPayment(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저 포인트 잔액 조회 api")
    @GetMapping
    public ResponseEntity<Void> getPoints(

    ){
        return ResponseEntity.ok().build();
    }
}
