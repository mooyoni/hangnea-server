package kr.hhplus.be.server.products.service;

import kr.hhplus.be.server.products.domain.ProductsRepository;
import kr.hhplus.be.server.products.dto.ProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    public ProductsResponse findAllProducts(){
        return ProductsResponse.of(productsRepository.findAll());
    }
}
