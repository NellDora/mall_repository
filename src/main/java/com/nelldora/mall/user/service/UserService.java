package com.nelldora.mall.user.service;

import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.exception.IdDuplicationException;
import com.nelldora.mall.user.exception.PasswordCheckFailException;
import com.nelldora.mall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    public void save(User user){
        try {
            duplicateCheckId(user.getId());
            //아이디 중복 여부에 따라 유저 회원가입디 될지 안될지
            userRepository.save(user);
        } catch (IdDuplicationException e) {
            throw new RuntimeException(e);
        }
    }

    //아이디 중복 검증
    public void duplicateCheckId(String id) throws IdDuplicationException {
        List<User> findUsers = userRepository.findById(id);
        if(!findUsers.isEmpty()) {
            throw new IdDuplicationException("이미 존재하는 아이디 입니다.");
        }else{
            log.info("UserService : 중복 검사 통과");
        }
    }

    //회원가입시 비밀번호 이중체크
    public void joinPasswordCheck(String passwordOne , String passwordTwo) throws PasswordCheckFailException {
        if(passwordOne.equals(passwordTwo)){
            log.info("UserService : 비밀번호 체크 성공");
        }else{
            throw new PasswordCheckFailException("비밀번호가 맞지 않습니다.");
        }
    }

}
