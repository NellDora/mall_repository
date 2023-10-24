package com.nelldora.mall.board.service;

import com.nelldora.mall.board.domain.Board;
import com.nelldora.mall.board.domain.BoardCategory;
import com.nelldora.mall.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(Board board){
        boardRepository.save(board);
        log.info(" BoardService : save 성공");
    }

    public Board findById(Long id){
        log.info("BoardService : findById 호출");
        return boardRepository.findById(id);
    }

    public List<Board> findByCategoryForAll(BoardCategory boardCategory){
        return boardRepository.findByCategoryForAll(boardCategory);
    }
}
