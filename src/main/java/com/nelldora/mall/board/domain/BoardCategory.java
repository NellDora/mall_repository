package com.nelldora.mall.board.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "board_category")
public class BoardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="board_category_id")
    private Long id;

    @Column(name = "board_type")
    private String boardType;

    @OneToMany(mappedBy = "boardCategory")
    private List<Board> boardList;

    public Long getId() {
        return id;
    }

    public String getBoardType() {
        return boardType;
    }
}
