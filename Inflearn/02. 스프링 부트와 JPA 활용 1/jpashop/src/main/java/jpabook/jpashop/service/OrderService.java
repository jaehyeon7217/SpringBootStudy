package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    // 주문

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.fineOne(itemId);

        // 배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
//        OrderItem orderItem2 = new OrderItem();
        // orderItem2.setItem() 등 이런 식으로 쓰면 생성자 생성 방식이 통일이 안됨
        // 그래서 기본 생성자를 protected로 막아야함
        // protected는 동일한 패키지에서만 사용 가능 혹은 상속받은 클래스에서 사용 가능

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        /**
         * 도메인에 핵심 비즈니스 로직을 넣고 서비스에서는 단순히 넘겨주는 형식을
         * ** 도메인 모델 패턴 ** 이라고 합니다.
         * 엔티티가 비즈니스 로직을 가지고 객체지향의 특성을 적극 활용하는 것
         *
         * 반대로 엔티티에는 비즈니스 로직이 거의 없고 서비스 계층에서 대부분 처리하는 것을
         * 트랜잭션 스크립트 패턴이라고 한다.
         * 일반적으로 SQL 작성하며 만든 형식
         */

        // 주문 저장
        // cascade를 걸어놨기 때문에 order만 save해도 oderItem과 delivery가 자동으로 저장됨
        // cascade를 쓰는 곳은 life cycle에 의해서 오로지 한 명만 참조를 할 때 사용
        // ex) orderItem과 delivery는 order말고는 아무도 안씀
        orderRepository.save(order);

        return order.getId();
    }

    // 취소

    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();

        /**
         * JPA를 사용할 때 가장 큰 장점은 더티 체킹  (변경 점 검사)
         * 하나하나 쿼리를 날려주지 않아도 바뀐 점은 점부 update 쿼리를 날려줌
         */

    }


    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }
}
