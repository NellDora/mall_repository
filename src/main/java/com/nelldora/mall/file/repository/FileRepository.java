package com.nelldora.mall.file.repository;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.item.domain.Item;

public interface FileRepository {

    void save(ItemImage itemImage);

    ItemImage findByItemIdFirst(Item item);
}
