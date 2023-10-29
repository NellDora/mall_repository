package com.nelldora.mall.admin.controller;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.file.save.FileSave;
import com.nelldora.mall.file.service.FileService;
import com.nelldora.mall.file.vo.TransFile;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.dto.ItemDTO;
import com.nelldora.mall.item.service.ItemService;
import com.nelldora.mall.item.vo.ItemSaveResult;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;
    private final ItemService itemService;
    private final FileService fileService;

    private final FileSave fileSave;

    @GetMapping("/addItem")
    public String addItemView(Model model){
        model.addAttribute("itemDTO", new ItemDTO());
        return "/admin/mall-item-reg";
    }

    @PostMapping("/addItem")
    public String addItemAction(@ModelAttribute("itemDTO")ItemDTO itemDTO, @RequestParam("files") List<MultipartFile> files , HttpServletResponse response){
        //아이템 등록
        Item item = Item.createItem(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getStock(),itemDTO.getItemCategory());
        ItemSaveResult itemSaveResult=itemService.save(item);


        if(itemSaveResult.equals(ItemSaveResult.PRICE_ERROR)){
            try{
                response.setContentType("text/html; charset=utf-8");
                String message = "금액 문제가 있습니다.";
                PrintWriter advise = response.getWriter();
                advise.write("<script>alert('"+message+"');</script>");
                advise.write("<script>history.back();</script>");
                advise.flush();
            }catch(IOException e){

            }
        }else if(itemSaveResult.equals(ItemSaveResult.STOCK_ERROR)){
            try {
                response.setContentType("text/html; charset=utf-8");
                String message = "수량에 문제가 있습니다...";
                PrintWriter advise = response.getWriter();
                advise.write("<script>alert('"+message+"');</script>");
                advise.write("<script>history.back();</script>");
                advise.flush();
                advise = response.getWriter();
            } catch (IOException e) {

            }

        }

        //이미지 저장에 저장된 Item값의 ID값이 필요하기 때문에 조회
        Optional<Item> findItem = itemService.findByCode(item.getItemCode());
        log.info("불러온 ID 값 = {}", findItem.get().getId());

        try {
            //입력받은 이미지 리스트 files를 변환
            List<TransFile> transFiles =fileSave.saveFiles(files);

            for(TransFile transFile : transFiles){
                ItemImage itemImage = ItemImage.createItemImage(findItem.get(),transFile);
                fileService.save(itemImage);
            }

        } catch (IOException e) {
            log.info("파일업로드 IO 예외 발생");
        }


        return null;
    }

}
