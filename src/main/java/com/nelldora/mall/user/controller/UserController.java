package com.nelldora.mall.user.controller;

import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.dto.UserDTO;
import com.nelldora.mall.user.exception.IdDuplicationException;
import com.nelldora.mall.user.exception.PasswordCheckFailException;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String joinView(Model model)
    {
        model.addAttribute("userDTO", new UserDTO());
        return "/mall-join";

    }

    @PostMapping("/login")
    public String joinAction(@ModelAttribute("userDTO")UserDTO userDTO, HttpServletResponse response) throws IOException {

        //우선 아이디 중복 확인
        try {
            userService.duplicateCheckId(userDTO.getId());
        } catch (IdDuplicationException e) {
            log.info("User Controller : 아이디 중복 문제 발생 : ={} ", e);
            response.setContentType("text/html; charset=utf-8");
            String message = "이미 존재하는 아이디입니다..";

            PrintWriter advise = response.getWriter();
            advise.write("<script>alert('"+message+"');</script>");
            advise.write("<script>history.back();</script>");
            advise.flush();
        }

        //비밀번호 검증 확인
        try {
            userService.joinPasswordCheck(userDTO.getPasswordOne(), userDTO.getPasswordTwo());
        } catch (PasswordCheckFailException e) {
            log.info("User Controller : 비밀번호 검증 문제 발생 : ={} ", e);
            response.setContentType("text/html; charset=utf-8");
            String message = "비밀번호가 다릅니다.";

            PrintWriter advise = response.getWriter();
            advise.write("<script>alert('"+message+"');</script>");
            advise.write("<script>history.back();</script>");
            advise.flush();
        }

        //userDTO -> user 전환

       userService.save(User.createUser(userDTO.getId(),userDTO.getName(),userDTO.getPasswordOne(), userDTO.getNickName(), userDTO.getAge(), userDTO.getTelephone()));


        return "redirect:/main";
    }

}
