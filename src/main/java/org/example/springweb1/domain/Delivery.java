package org.example.springweb1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order orders;

    @Enumerated
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryState state;
}
