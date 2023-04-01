package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문 상품 생성 -> 스태틱 생성 메서드를 사용해서 만듦
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성 -> 이것도 스태틱 생성 메서드 사용, 케스케이드 때문에 자동으로 persist
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order); // Casecade 덕에 orderItem과 delivery도 자동으로 persist
        return order.getId();
    }

    /**
     *
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
