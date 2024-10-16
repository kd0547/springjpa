package org.example.springweb1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springweb1.domain.item.Item;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격

    private int count;

}
