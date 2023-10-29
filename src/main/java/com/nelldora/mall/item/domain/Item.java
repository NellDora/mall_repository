package com.nelldora.mall.item.domain;

import com.nelldora.mall.order.domain.OrderItem;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "item")
@Getter
public class Item {
//반드시 DB에 추가 할 내용 추가 기재할 것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Column(name = "item_code")
    private String itemCode;

    private String name;

    private String description;

    private int price;

    private int stock;

    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "item_category")
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

    protected Item() {
    }

    public static Item createItem(String name, String description, int price, int stock, ItemCategory itemCategory){
        Item item = new Item();
        item.name = name;
        item.description = description;
        item.price = price;
        item.stock = stock;
        item.itemCategory = itemCategory;
        item.itemCode= item.createItemCode();
        item.regDate = LocalDateTime.now();
        return item;
    }

    protected String createItemCode(){
        String code = UUID.randomUUID().toString();
        return code;
    }

}
