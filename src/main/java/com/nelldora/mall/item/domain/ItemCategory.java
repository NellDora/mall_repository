package com.nelldora.mall.item.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item_category")
@Getter
public class ItemCategory {

    @Id
    private Long code;

    @Column(name = "name")
    private String categoryName;

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items;

    protected ItemCategory() {
    }

    public static ItemCategory createItemCategory(Long code , String categoryName){
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.code = code;
        itemCategory.categoryName = categoryName;
        return itemCategory;
    }
}
