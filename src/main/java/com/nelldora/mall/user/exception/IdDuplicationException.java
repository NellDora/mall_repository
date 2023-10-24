package com.nelldora.mall.user.exception;

//아이디 중복 발생시 사용하는 예외
public class IdDuplicationException extends Exception{
    public IdDuplicationException(String message){
        super(message);
    }
}
