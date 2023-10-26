package com.nelldora.mall.user.repository;

import com.nelldora.mall.user.domain.User;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@Transactional
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;
    private final QueryFactory query;

    public JpaUserRepository (EntityManager em){
        this.em= em;
        this.query= new JPAQueryFactory(em);

    }


    @Override
    public void save(User user) {
        em.persist(user);
        log.info("UserRepository : 저장 완료");
    }

    @Override
    public User findByNum(int id) {
        User findUser = em.find(User.class,id);
        log.info("UserRepository : 조회 완료");
        return findUser;
    }

    @Override
    public List<User> findById(String id) {
        String jpql = "select t from User t where t.id = :id";
        return em.createQuery("select t from User t where t.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
