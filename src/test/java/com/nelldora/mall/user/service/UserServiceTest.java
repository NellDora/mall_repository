package com.nelldora.mall.user.service;

import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.dto.UserDTO;
import com.nelldora.mall.user.vo.JoinResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userServie){
        this.userService = userServie;
    }

    @Test
    void saveTest(){

        //입력 값
        UserDTO userDTO = new UserDTO();
        userDTO.setId("ttat");
        userDTO.setPasswordOne("AAA");
        userDTO.setPasswordTwo("AAAB");

        //과정
        JoinResult result =userService.save(userDTO);

        //결과
        Assertions.assertEquals(result,JoinResult.ID_EEROR);
    }

    @Test
    void saveTest2(){

        //입력 값
        UserDTO userDTO = new UserDTO();
        userDTO.setId("test");
        userDTO.setPasswordOne("AAA");
        userDTO.setPasswordTwo("AAA");

        //과정
        JoinResult result =userService.save(userDTO);

        //결과
        Assertions.assertEquals(result,JoinResult.ID_EEROR);
    }

    @Test
    void saveTest3(){

        //입력 값
        UserDTO userDTO = new UserDTO();
        userDTO.setId("testtest");
        userDTO.setPasswordOne("AAA");
        userDTO.setPasswordTwo("AAA");

        //과정
        JoinResult result =userService.save(userDTO);

        //결과
        Assertions.assertEquals(result,JoinResult.SUCCESS);
    }
}