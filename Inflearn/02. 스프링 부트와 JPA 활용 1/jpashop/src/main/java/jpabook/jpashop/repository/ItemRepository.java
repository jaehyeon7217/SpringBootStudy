package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ // id가 없으면 새로 생성한 객체
            em.persist(item);     // 그럼 신규로 등록
        } else{                   // id가 있다면 DB에 등록되어 가져온 객체
            em.merge(item);       // 쉽게 말해서 이부분은 update 진짜 update는 아님
        }
    }

    public Item fineOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
