package com.nelldora.mall.board.repository;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.board.domain.BoardCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public JpaBoardRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Board save(Board board){
        em.persist(board);
        return board;
    }

    @Override
    public Board findById(Long id){
        Board findBoard = em.find(Board.class,id);
        return findBoard;
    }

    @Override
    public List<Board> findByCategoryForAll(BoardCategory category){
        String jpql = "select t from Board t where t.boardCategory = boardCategory";
        TypedQuery<Board> query = em.createQuery(jpql, Board.class);
        query.setParameter("boardCategory", category);
        return query.getResultList();
    }

}
