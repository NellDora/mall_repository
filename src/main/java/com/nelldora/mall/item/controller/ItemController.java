package com.nelldora.mall.item.controller;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.file.dto.ItemImageDTO;
import com.nelldora.mall.file.service.FileService;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.dto.ItemDTO;
import com.nelldora.mall.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final FileService fileService;

    @GetMapping("/main")
    public String itemsView(Model model){
        List<Item> items = itemService.findByAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<ItemImageDTO> itemImageDTOS = new ArrayList<>();

        for(Item item : items){
            ItemDTO itemDTO = new ItemDTO(item);
            itemDTOS.add(itemDTO);
            if(fileService.findByItemIdFirst(item.getId())!=null){
                ItemImage itemImage = fileService.findByItemIdFirst(item.getId());
                ItemImageDTO itemImageDTO = new ItemImageDTO(itemImage);
                itemImageDTOS.add(itemImageDTO);
            }


        }

        model.addAttribute("itemDTOs", itemDTOS);
        model.addAttribute("itemImageDTOs", itemImageDTOS);


        return "/item/mall-items";
    }

    @GetMapping("/item/{itemId}")
    public String itemDetail(@PathVariable ("itemId") Long id, Model model){
        Item item = itemService.findById(id);
        model.addAttribute("item",item);
        //model.addAttribute("itemImage",);

        return null;
    }

}
