package com.nelldora.mall.user.controller;

import com.nelldora.mall.user.exception.IdDuplicationException;
import com.nelldora.mall.user.service.UserService;
import com.nelldora.mall.user.vo.IdDuplicateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserAjaxController {

    private final UserService userService;

    @ResponseBody
    @GetMapping(value = "idCheck", produces = "application/json; charset=utf8")
    public ResponseEntity<IdDuplicateResult> AjaxIdDuplicateCheck(String checkId){

        IdDuplicateResult idDuplicateResult;

        try {
            userService.duplicateCheckId(checkId);
            idDuplicateResult= IdDuplicateResult.PASS;
        } catch (IdDuplicationException e) {
            idDuplicateResult=IdDuplicateResult.NOPASS;
        }
        return new ResponseEntity<>(idDuplicateResult, HttpStatus.OK);

    }
}
