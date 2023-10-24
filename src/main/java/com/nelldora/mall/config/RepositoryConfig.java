package com.nelldora.mall.config;

import com.nelldora.mall.board.repository.BoardRepository;
import com.nelldora.mall.board.repository.JpaBoardRepository;
import com.nelldora.mall.user.repository.JpaUserRepository;
import com.nelldora.mall.user.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class RepositoryConfig {

    private final EntityManager em;

    @Bean
    public BoardRepository boardRepository(){
        return new JpaBoardRepository(em);
    }

    @Bean
    public UserRepository userRepository(){return new JpaUserRepository(em);
    }
}
