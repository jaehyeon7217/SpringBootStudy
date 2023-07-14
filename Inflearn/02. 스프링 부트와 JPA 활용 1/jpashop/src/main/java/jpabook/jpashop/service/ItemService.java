package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    /*
     이러한 Repository 에 그냥 데이터만 넘겨주는 Service 는 Controller 단계에서 바로 Repository 를 호출하여 사용해도
     큰 문제 없다고 하심
     */

    private final ItemRepository itemRepository;

    @Transactional // 전체에 readOnly가 걸려있으니 여기는 저장하기 위해서 transactional 을 붙여 줘야함 (기본의 false)
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // @Transactional 이 전역으로 readOnly 되어있으니 따로 붙일 필요가 없음
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    // @Transactional 이 전역으로 readOnly 되어있으니 따로 붙일 필요가 없음
    public Item findOne(Long itemId){
        return itemRepository.fineOne(itemId);
    }

}
