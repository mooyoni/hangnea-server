package kr.hhplus.be.server.products.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class ProductsEntity {
    @Id
    Long id;
    String name;
    int price;
    int stock;
}
