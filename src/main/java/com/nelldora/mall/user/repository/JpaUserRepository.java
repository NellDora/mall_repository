package com.nelldora.mall.user.repository;

import com.nelldora.mall.user.domain.User;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;
    private final QueryFactory query;

    public JpaUserRepository (EntityManager em){
        this.em= em;
        this.query= new JPAQueryFactory(em);

    }


    @Override
    public void save(User user) {
        em.persist(em);
        log.info("UserRepository : 저장 완료");
    }

    @Override
    public User findById(String id) {
        User findUser = em.find(User.class,id);
        log.info("UserRepository : 저장 완료");
        return findUser;
    }

}
