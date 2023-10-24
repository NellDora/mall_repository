package com.nelldora.mall.file.domain;

import com.nelldora.mall.board.domain.Board;

import java.time.LocalDateTime;

public class UploadFile {

    private Long id;

    private Board board;

    private String originalFile;

    private String storeFile;

    private LocalDateTime regDate;
}
