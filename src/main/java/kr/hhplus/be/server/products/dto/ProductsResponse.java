package kr.hhplus.be.server.products.dto;

import kr.hhplus.be.server.products.domain.ProductsEntity;

import java.util.List;

public record ProductsResponse(
        List<Product> products
) {
    public static ProductsResponse of(List<ProductsEntity> entities) {
            List<Product> products = entities.stream()
                    .map(Product::of)
                    .toList();
            return new ProductsResponse(products);
    }
}
