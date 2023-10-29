package com.nelldora.mall.item.service;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.exception.PriceException;
import com.nelldora.mall.item.exception.StockException;
import com.nelldora.mall.item.repository.ItemCategoryRepository;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.item.vo.ItemCategoryVO;
import com.nelldora.mall.item.vo.ItemSaveResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    public ItemSaveResult save(Item item){
        ItemSaveResult itemSaveResult = ItemSaveResult.STANDBY;
        try {
            stockCheck(item); //수량 등록 체크
            priceCheck(item); //가격 등록 체크

            itemRepository.save(item); //문제 없을 시 등록
            log.info("ItemService : 상품 등록 완료");
            itemSaveResult = ItemSaveResult.SUCCESS; //등록 성공시 result에 SUCCESS(성공)
        } catch (StockException e) {
            itemSaveResult = ItemSaveResult.STOCK_ERROR;
        } catch (PriceException e) {
            itemSaveResult = ItemSaveResult.PRICE_ERROR;
        }
        return itemSaveResult;

    }

    public Item findById(Long id){
        Item findItem = itemRepository.findById(id);
        log.info("ItemService : id로 상품 조회 성공");
        return findItem;
    }

    public Optional<Item> findByCode(String code){
        return itemRepository.findByCode(code);
    }

    public List<Item> findByAll(){
        return itemRepository.findByAll();
    }

    public List<Item> findByCategory(ItemCategoryVO categoryVO){
        log.info("ItemService : 카테고리별 찾기 호출");
        ItemCategory findItemCategory = itemCategoryRepository.findByCategory(categoryVO);
        return itemRepository.findByCategory(findItemCategory);

    }

    private void stockCheck(Item item) throws StockException {
        if(item.getStock()!=0){
            log.info("ItemService : 수량체크 문제없음");
        }else{
            throw new StockException("등록 수량인 1개 이상이어야 합니다.");
        }
    }

    private void priceCheck(Item item) throws PriceException {
        if(item.getPrice()!=0){
            log.info("ItemService : 금액 체크 문제 없음");
        }else{
            throw new PriceException("판매 금액은 0이 될 수 없습니다.");
        }
    }

}
