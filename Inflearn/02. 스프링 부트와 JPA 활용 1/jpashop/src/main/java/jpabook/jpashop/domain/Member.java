package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    private String name;

    // 내장타입
    @Embedded
    private Address address;

    // 나는 주인이 아니기 때문에 mappedBy로 주인을 알려줘야함.
    // order 테이블의 member 에 매핑된 거울이다. 라는 뜻으로 해석
    // 읽기 전용
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    // 생성자보다 그냥 바로 초기화 하는게 베스트
    // 생성자로 하면 null 문제에 안전하다.
    // 하이버네이트가 영속화 하는 과정에서 리스트가 바뀌면 매커니즘에 문제가 발생 할 수 있다.
    // 그래서 그냥 필드단위에서 초기화 하는게 안전하고 바꾸지 않는게 안전하고 중요하다.

}
