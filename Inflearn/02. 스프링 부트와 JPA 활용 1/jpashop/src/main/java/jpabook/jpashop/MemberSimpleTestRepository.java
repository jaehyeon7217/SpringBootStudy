package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberSimpleTestRepository {

    // Springboot data jpa에서 생성해서 주입 해줌
    @PersistenceContext
    private EntityManager em;

    public Long save(MemberSimpleTest memberTest){
        em.persist(memberTest);
        return memberTest.getId();
    }

    public MemberSimpleTest find(Long id){
        return em.find(MemberSimpleTest.class, id);
    }

}
