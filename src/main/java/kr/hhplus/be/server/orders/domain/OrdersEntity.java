package kr.hhplus.be.server.orders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class OrdersEntity {
    @Id
    Long id;
    Long userId;

}
