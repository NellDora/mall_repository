package com.nelldora.mall.board.domain;

import com.nelldora.mall.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "board_category")
    private BoardCategory boardCategory;

    private String title;

    private String content;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    //private List<UploadFile> uploadFiles;

    public static void writeBoard(User writer, BoardCategory boardCategory, String title, String content){
        Board board = new Board();
        board.writer = writer;
        board.boardCategory = boardCategory;
        board.title = title;
        board.content = content;
    }

}
