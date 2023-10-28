package com.nelldora.mall.user.controller;

import com.nelldora.mall.user.exception.IdDuplicationException;
import com.nelldora.mall.user.service.UserService;
import com.nelldora.mall.user.vo.IdDuplicateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserAjaxController {

    private final UserService userService;

    @ResponseBody
    @PostMapping(value = "/idCheck")
    public IdDuplicateResult AjaxIdDuplicateCheck(String checkId){
        log.info("UserServiceAjax : 중복확인 동작");
        log.info("UserServiceAjax : 전달 받은 ID 값 = {}", checkId);
        IdDuplicateResult idDuplicateResult;

        try {
            userService.duplicateCheckId(checkId);
            idDuplicateResult= IdDuplicateResult.PASS;
        } catch (IdDuplicationException e) {
            idDuplicateResult=IdDuplicateResult.NOPASS;
        }
        return idDuplicateResult;

    }
}
