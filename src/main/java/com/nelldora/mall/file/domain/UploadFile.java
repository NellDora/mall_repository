package com.nelldora.mall.file.domain;

import com.nelldora.mall.board.domain.Board;

import java.time.LocalDateTime;

public class UploadFile {

    private Long id;

    private Board board;

    private String originalFileName;

    private String serverFileName;

    private LocalDateTime regDate;
}
