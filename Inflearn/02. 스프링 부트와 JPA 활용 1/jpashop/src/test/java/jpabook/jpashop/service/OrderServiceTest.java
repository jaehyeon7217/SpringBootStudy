package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // Given
        Member member = getMember("회원1");

        Book book = getBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        // When
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // Then
        Order getOrder = orderRepository.findOne(orderId);

//        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        System.out.println("상품 주문시 상태는 ORDER");
        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        System.out.println("주문한 상품 종류 수가 정확해야 한다.");
        Assertions.assertEquals(1,getOrder.getOrderItems().size());
        System.out.println("주문 가격은 가격 * 수량이다.");
        Assertions.assertEquals(10000*orderCount, getOrder.getTotalPrice());
        System.out.println("주문 수량만큼 재고가 줄어야 한다.");
        Assertions.assertEquals(8,book.getStockQuantity());
    }

    @Test
//    @Test(expected = NotEnoughStockException.class) // JUnit 5에선 안됨 Assertions.assertThrows 써야함
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = getMember("회원1");
        Book book = getBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        NotEnoughStockException e = Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), orderCount);
        });

        //then
//        Assertions.fail("재고 수량 부족 예외가 발생해야 한다.");
        Assertions.assertEquals("need more stock", e.getMessage());
    }

    @Test
    public void 주문취소() throws Exception {
        // Given
        Member member = getMember("회원1");
        Book item = getBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // When
        orderService.cancelOrder(orderId);

        // Then
        Order getOrder = orderRepository.findOne(orderId);

        System.out.println("상품 주문 취소시 상태는 CANCEL");
        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        System.out.println("주문이 취소된 상품은 그만큼 재고가 증가해야한다.");
        Assertions.assertEquals(10,item.getStockQuantity());

    }


    private Book getBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member getMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}
