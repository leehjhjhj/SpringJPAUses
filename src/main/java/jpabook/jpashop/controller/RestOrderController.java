package jpabook.jpashop.controller;

import jpabook.jpashop.controller.OrderRequest;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restorders")
public class RestOrderController {
    public final OrderService orderService;
    public final MemberService memberService;
    public final ItemService itemService;

    @PostMapping("")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest request) {
        orderService.order(request.getMemberId(), request.getItemId(), request.getCount());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public List<Order> getOrderList(@ModelAttribute("orderSearch") OrderSearch orderSearch) {
        return orderService.findOrders(orderSearch);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
