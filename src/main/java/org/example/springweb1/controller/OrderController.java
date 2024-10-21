package org.example.springweb1.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springweb1.domain.Member;
import org.example.springweb1.domain.Order;
import org.example.springweb1.domain.item.Item;
import org.example.springweb1.service.ItemService;
import org.example.springweb1.service.MemberService;
import org.example.springweb1.service.OrderSearch;
import org.example.springweb1.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createOrder(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }
    @PostMapping("/order")
    public String createOrder(
            @RequestParam("memberId") Long memberId,
            @RequestParam("itemId") Long itemId,
            @RequestParam("count") int count
    ) {

        orderService.order(memberId,itemId,count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders",orders);

        return "order/orderList";
    }

    @PostMapping("order/{orderid}/cancel")
    public String cancel(@PathVariable("orderid")Long orderId) {
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }



}
