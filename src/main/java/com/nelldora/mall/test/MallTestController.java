package com.nelldora.mall.test;

import com.nelldora.mall.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class MallTestController {

    private final BoardService boardService;

    public MallTestController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/main")
    public String main(){

        return "/mall-main";
    }

    @GetMapping("/pay")
    public String pay(){
        return "/test/mall-pay";
    }


    @GetMapping("/pay2")
    public String pay2(){
        return "/test/realPayTest";
    }
}
