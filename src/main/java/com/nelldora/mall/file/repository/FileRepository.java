package com.nelldora.mall.file.repository;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.item.domain.Item;

import java.util.List;

public interface FileRepository {

    void save(ItemImage itemImage);

    ItemImage findByItemIdFirst(Long itemId);

    List<ItemImage> findByItemIdAll(Long itemId);
}
