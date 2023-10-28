package com.nelldora.mall.admin.controller;

import com.nelldora.mall.item.service.ItemService;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ItemService itemService;

    @GetMapping("/addItem")
    public String addItemView(){

        return "/admin/mall-item-reg";
    }

}
