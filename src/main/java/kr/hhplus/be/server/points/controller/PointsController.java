package kr.hhplus.be.server.points.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/points")
@RestController
public class PointsController {

    //충전 api
    @Operation(summary = "유저 포인트 충전 api")
    @PostMapping
    public ResponseEntity<Void> charge(){
        return ResponseEntity.ok().build();
    }

    //조회 api
    @Operation(summary = "유저 포인트 잔액 조회 api")
    @GetMapping
    public ResponseEntity<Void> getPoints(){
        return ResponseEntity.ok().build();
    }
}
