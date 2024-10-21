package org.example.springweb1.service;

import lombok.RequiredArgsConstructor;
import org.example.springweb1.domain.Delivery;
import org.example.springweb1.domain.Member;
import org.example.springweb1.domain.Order;
import org.example.springweb1.domain.OrderItem;
import org.example.springweb1.domain.item.Item;
import org.example.springweb1.repository.ItemRepository;
import org.example.springweb1.repository.MemberRepository;
import org.example.springweb1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문하기

    @Transactional
    public Long order(Long memberId, Long itemId,int count) {

        //엔티티 조회
        Member member = memberRepository.findMember(memberId);
        Item item = itemRepository.findOne(itemId);
        
        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문 생성
        Order order= Order.createOrder(member,delivery,orderItem);

        orderRepository.save(order);

        return order.getId();
    }


    //취소

    /**
     * 주문 취소
     * @param orderId
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }



    //검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

}
