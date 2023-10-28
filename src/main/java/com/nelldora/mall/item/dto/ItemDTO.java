package com.nelldora.mall.item.dto;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.order.domain.OrderItem;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ItemDTO {

    private long id;

    private String name;

    private String description;

    private int price;

    private int stock;


    private ItemCategory itemCategory;

    private List<OrderItem> orderItems;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.itemCategory = item.getItemCategory();
        this.orderItems = item.getOrderItems();
    }
}
