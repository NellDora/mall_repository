package com.nelldora.mall.item.repository;

import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.vo.ItemCategoryVO;

public interface ItemCategoryRepository {

    void save(ItemCategory itemCategory);
    ItemCategory findByCategory(ItemCategoryVO itemCategoryVO);
}
