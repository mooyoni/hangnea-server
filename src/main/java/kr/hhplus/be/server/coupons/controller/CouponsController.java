package kr.hhplus.be.server.coupons.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/coupons")
@RestController
public class CouponsController {

    @Operation(summary = "선착순 쿠폰 발급 api")
    @PostMapping()
    public void issue(){

    }
}
