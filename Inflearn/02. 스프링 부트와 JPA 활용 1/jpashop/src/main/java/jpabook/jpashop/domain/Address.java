package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// 내장타입
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;


    // jpa 에서 기본 생성자가 항상 있어야함.
    // jpa 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.
    protected Address() {
    }

    // 생성 후 값 변경 안되도록 Setter 지우고 생성자로 값 초기화 후 입력
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
