package org.example.springweb1.service;

import jakarta.persistence.EntityManager;
import org.example.springweb1.domain.Address;
import org.example.springweb1.domain.Member;
import org.example.springweb1.domain.Order;
import org.example.springweb1.domain.OrderStatus;
import org.example.springweb1.domain.item.Book;
import org.example.springweb1.domain.item.Item;
import org.example.springweb1.domain.item.NotEnuoughStockException;
import org.example.springweb1.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품_주문() throws Exception {
        //테스트용 유저 생성
        Member member = createMember();

        //테스트용 아이템 생성
        Book book = createBook("서울 JPA", 30000, 10);


        int orderCount = 2;

        Long  orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        //검증
        assertEquals(OrderStatus.ORDER, getOrder.getStatue(), "상품 주문시 상태는 ORDER");

    }


    @Test
    @Rollback
    public void 상품주문_재고수량초과() throws Exception {
        Member member = createMember();
        Item item = createBook("시골_JPA",30000,10);

        int orderCount = 11;

        assertThrows(NotEnuoughStockException.class,() -> {
            //상품 주문
            orderService.order(member.getId(), item.getId(), orderCount);
        },"재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    @Rollback
    public void 상품구매취소() throws Exception {
        Member member = createMember();
        Book book = createBook("시골 JPA",30000,10);

        //
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(),orderCount);

        //주문 취소
        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL,getOrder.getStatue(),"주문 취소시 상태는 CANCEL 이다.");
        assertEquals(10,book.getStockQuantity(),"주문이 취소가된 상품은 그만큼 재고가 증가해야한다.");


    }



    private Book createBook(String bookName, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(bookName);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);

        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setUsername("kim");
        member.setAddress(new Address("서울","경기","123-123"));
        em.persist(member);

        return member;
    }



}