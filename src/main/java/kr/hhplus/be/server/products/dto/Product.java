package kr.hhplus.be.server.products.dto;

import kr.hhplus.be.server.products.domain.ProductsEntity;

public record Product(
    Long id,
    String name,
    int price,
    int stock
) {
    public static Product of(ProductsEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock()
        );
    }
}
