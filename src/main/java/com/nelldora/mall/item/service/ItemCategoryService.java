package com.nelldora.mall.item.service;

import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.repository.ItemCategoryRepository;
import com.nelldora.mall.item.vo.ItemCategoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    public void save(ItemCategory itemCategory){

    }

}
