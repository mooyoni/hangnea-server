package kr.hhplus.be.server.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.products.dto.ProductsResponse;
import kr.hhplus.be.server.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductsController {
    private final ProductsService productsService;

    @Operation(summary = "상품조회 api")
    @GetMapping
    public ResponseEntity<ProductsResponse> findAllProducts() {
        return ResponseEntity.ok(productsService.findAllProducts());
    }

}
