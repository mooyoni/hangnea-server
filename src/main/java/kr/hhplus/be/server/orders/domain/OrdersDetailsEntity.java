package kr.hhplus.be.server.orders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrdersDetailsEntity {
    @Id
    Long id;
    Long orderId;
    Long productId;
    int orderCount;
}
