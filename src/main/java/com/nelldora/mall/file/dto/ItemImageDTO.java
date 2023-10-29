package com.nelldora.mall.file.dto;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.item.domain.Item;

import javax.persistence.*;

public class ItemImageDTO {

    private Long id;

    private Long itemId;
    private String itemName;
    private String originalFileName;

    private String serverFileName;

    public ItemImageDTO(ItemImage itemImage) {
        this.id = itemImage.getId();
        this.itemId = itemImage.getItem().getId();
        this.itemName = itemImage.getItem().getName();
        this.originalFileName = itemImage.getOriginalFileName();
        this.serverFileName = itemImage.getServerFileName();
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getServerFileName() {
        return serverFileName;
    }
}
