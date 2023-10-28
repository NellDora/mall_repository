package com.nelldora.mall.item.repository;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.vo.ItemCategoryVO;

import java.util.List;

public interface ItemRepository {

    void save(Item item);

    Item findById(Long Id);

    List<Item> findByCategory (ItemCategory itemCategory);

    List<Item> findByNameAndCategory(String name , ItemCategory itemCategory);
}