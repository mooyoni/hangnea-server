package kr.hhplus.be.server.orders.service;

import kr.hhplus.be.server.orders.domain.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
}
