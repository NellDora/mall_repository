package com.nelldora.mall.item.service;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.repository.ItemCategoryRepository;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.item.vo.ItemCategoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    public void save(Item item){
        itemRepository.save(item);
        log.info("ItemService : 상품 등록 완료");
    }

    public Item findById(Long id){
        Item findItem = itemRepository.findById(id);
        log.info("ItemService : id로 상품 조회 성공");
        return findItem;
    }

    public List<Item> findByCategory(ItemCategoryVO categoryVO){
        log.info("ItemService : 카테고리별 찾기 호출");
        ItemCategory findItemCategory = itemCategoryRepository.findByCategory(categoryVO);
        return itemRepository.findByCategory(findItemCategory);

    }

}
