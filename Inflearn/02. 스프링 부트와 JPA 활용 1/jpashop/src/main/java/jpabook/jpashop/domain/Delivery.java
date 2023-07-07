package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL 썻다가 중간에 추가되면 다 바뀌게 됨 그래서 STRING 으로 써야 수정해도 멀쩡함
    private DeliveryStatus status; // READY,COMP (ENUM 타입)
    // ex) READY, COMP -> READY, SET, COMP 로 바꼈을 때 ORDINAL 하면 모든 COMP 가 SET 으로 장애남 그래서 STRING 써야함
}
