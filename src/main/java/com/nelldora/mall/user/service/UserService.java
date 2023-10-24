package com.nelldora.mall.user.service;

import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.exception.IdDuplicationException;
import com.nelldora.mall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    public void save(User user){
        try {
            duplicateCheckId(user.getId());
            //아이디 중복 여부에 따라 유저 회원가입디 될지 안될지

        } catch (IdDuplicationException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(user);
    }

    //아이디 중복 검증
    public void duplicateCheckId(String id) throws IdDuplicationException {
        User findUser = userRepository.findById(id);
        if(findUser!=null) {
            throw new IdDuplicationException("이미 존재하는 아이디 입니다.");
        }else{
            log.info("UserService : 중복 검사 통과");
        }
    }

}
