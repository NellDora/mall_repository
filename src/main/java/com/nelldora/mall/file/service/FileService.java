package com.nelldora.mall.file.service;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.file.repository.FileRepository;
import com.nelldora.mall.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public void save(ItemImage itemImage){
        fileRepository.save(itemImage);
    }

    public ItemImage findByItemIdFirst(Item item){
        return fileRepository.findByItemIdFirst(item);
    }
}
