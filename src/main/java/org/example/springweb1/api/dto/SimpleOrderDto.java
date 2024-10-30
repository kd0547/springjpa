package org.example.springweb1.api.dto;

import lombok.Data;
import org.example.springweb1.domain.Address;
import org.example.springweb1.domain.Order;
import org.example.springweb1.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {

    private Long orderId;

    private String name;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private Address address;


    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getUsername();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }

}
