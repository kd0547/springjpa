package org.example.springweb1.api;

import lombok.RequiredArgsConstructor;
import org.example.springweb1.api.dto.SimpleOrderDto;
import org.example.springweb1.domain.Order;
import org.example.springweb1.repository.OrderRepository;
import org.example.springweb1.service.OrderSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne과 OneToOne 에서 성능 최적화
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    /**
     * 엔티티 직접 노출 시 생기는 문제점은?
     * 1. 양뱡향 연관관계의 순환 참조
     * 2. LAZY로 값이 NULL이 되는 문제
     * @return
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order>  ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto>  ordersV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());

        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

}
