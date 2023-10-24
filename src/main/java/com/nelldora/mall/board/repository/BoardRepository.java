package com.nelldora.mall.board.repository;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.board.domain.BoardCategory;

import javax.persistence.TypedQuery;
import java.util.List;

public interface BoardRepository {

    public Board save(Board board);

    public Board findById(Long id);

    public List<Board> findByCategoryForAll(BoardCategory category);
}
