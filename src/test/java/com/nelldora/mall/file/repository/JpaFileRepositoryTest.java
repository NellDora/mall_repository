package com.nelldora.mall.file.repository;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.JpaItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaFileRepositoryTest {

    private final JpaFileRepository jpaFileRepository;
    private final JpaItemRepository jpaItemRepository;

    @Autowired
    public JpaFileRepositoryTest(JpaFileRepository jpaFileRepository, JpaItemRepository jpaItemRepository){
        this.jpaFileRepository = jpaFileRepository;
        this.jpaItemRepository = jpaItemRepository;
    }

    @Test
    void Test1(){
      //조건
      Item findItem =  jpaItemRepository.findById(9L);

        ItemImage itemImage = jpaFileRepository.findByItemIdFirst(findItem);
        
        System.out.println("itemImage의 ID : "+itemImage.getId());
        System.out.println("itemImage의 서버이름 : "+itemImage.getServerFileName());
        System.out.println("itemImage의 원본이름 : "+itemImage.getOriginalFileName());
        System.out.println("itemImage 아이템 ID 값 : "+itemImage.getItem().getId());

        Assertions.assertEquals(9,itemImage.getItem().getId());
    }

}