package com.nelldora.mall.item.repository;

import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.vo.ItemCategoryVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class JpaItemCategoryRepositoryTest {

    private final ItemCategoryRepository itemCategoryRepository;

    @Autowired
    JpaItemCategoryRepositoryTest(ItemCategoryRepository itemCategoryRepository){
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Test
    void Test1(){

        ItemCategory findItemCategory = itemCategoryRepository.findByCategory(ItemCategoryVO.CAT);
        System.out.println("조회한 카테고리의 Code = "+ findItemCategory.getCode()+ " name =" +findItemCategory.getCategoryName());
        Assertions.assertEquals(2000,findItemCategory.getCode());
    }

    @Test
    void Test2(){

        itemCategoryRepository.save(ItemCategory.createItemCategory(3000L,"fox"));
    }

}