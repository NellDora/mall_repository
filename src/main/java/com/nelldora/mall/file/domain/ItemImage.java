package com.nelldora.mall.file.domain;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.file.vo.TransFile;
import com.nelldora.mall.item.domain.Item;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_image")
@Data
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "original_name")
    private String originalFileName;

    @Column(name = "server_name")
    private String serverFileName;

    public static ItemImage createItemImage(Item item , TransFile transFile){
        ItemImage itemImage = new ItemImage();
        itemImage.item = item;
        itemImage.originalFileName = transFile.getOriginalName();
        itemImage.serverFileName = transFile.getServerName();
        return itemImage;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getServerFileName() {
        return serverFileName;
    }
}
