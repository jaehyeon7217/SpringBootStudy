package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name= "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name ="order_id")
    private Long id;

    // 양방향 연관관계 매핑에서는 데이터의 수정시 성능 및 정확도를 위해서 주종관계로 설정함
    // 실제 테이블에서 FK가 있는 테이블에 가까운 쪽을 주인으로 설정해서 데이터 수정에 용이하도록 함
    // 주인은 그대로 있고 종이 있는 엔티티로 가서 mappedBy 로 관계를 설정해줘야 함
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // Cascade 를 안하면 각각 persist 를 해야하는데 캐스케이드를 하면 연관된 모든게 알아서 persist 된다.


    // 원투원일 때 주인관계를 어디둬도 상관 없는데 주로 조회하는 쪽에 두는 것이 편함 FK도
    // 주인은 아무것도 안적고 종에다가 적어야 함
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;
    // Date 클래스를 쓰면 날짜관련 어노테이션 매핑을 해줘야함
    // LocalDateTime 은 Hibernate 에서 알아서 지원 해줌
    // 어노테이션 불필요

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL] ENUM


    // -- 연관관계 (편의) 메서드 -- //

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this); // 양방향 연관관계를 원자성으로 묶기 위함

        /*
        // 이 부분을 위 SetMember 에서 정의한 것처럼 한 번에 원자성으로 묶어 주는 내용
        Member member1 = new Member();
        Order  order = new Order();

        //member1.getOrders().add(order); // 원자성으로 묶이면 이 줄은 안적어도 원자성 확보가 됨
        order.setMember(member1);
         */
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드== // Order 만 생성되는게 아니라 Delivery 같은 여러개가 생성되기 때문
    // 이렇게 하면 생성 시점에 대한 변경사항을 한 번에 처리 가능
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//

    /*
     주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        // 알트 엔터 누르고 replace sum()으로 바꾸면 됨
//        int totalPrice = 0;
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }

//        int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
//        return totalPrice;

        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

}
