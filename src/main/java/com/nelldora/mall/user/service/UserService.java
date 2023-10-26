package com.nelldora.mall.user.service;

import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.dto.UserDTO;
import com.nelldora.mall.user.exception.IdDuplicationException;
import com.nelldora.mall.user.exception.PasswordCheckFailException;
import com.nelldora.mall.user.repository.UserRepository;
import com.nelldora.mall.user.vo.JoinResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public JoinResult save(UserDTO userDTO){
        JoinResult joinResult = JoinResult.STANDBY;
        try {
            duplicateCheckId(userDTO.getId());
            //아이디 중복 여부에 따라 유저 회원가입이 될지 안될지

            joinPasswordCheck(userDTO.getPasswordOne(), userDTO.getPasswordTwo());

            log.info("UserService Save : 입력받은 값 = {}", userDTO.toString());

            userRepository.save(User.createUser(userDTO.getId(),userDTO.getName(),userDTO.getPasswordOne(), userDTO.getNickName(), userDTO.getTelephone()));
            joinResult = JoinResult.SUCCESS;
        } catch (IdDuplicationException e) {
            log.info("{}",e);
            joinResult = JoinResult.ID_EEROR;
        }catch (PasswordCheckFailException e){
            log.info("{}",e);
            joinResult=JoinResult.PASSWORD_ERROR;
        }
        return joinResult;
    }

    //아이디 중복 검증
    public void duplicateCheckId(String id) throws IdDuplicationException {
        List<User> findUsers = new ArrayList<>();
        try{
            findUsers = userRepository.findById(id);
        }catch (NullPointerException e){
            log.info("중복 발생 에러가 발생한 것인가");
        }finally {
            if(!findUsers.isEmpty()) {
                throw new IdDuplicationException("이미 존재하는 아이디 입니다.");
            }else{
                log.info("UserService : 중복 검사 통과");
            }
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
