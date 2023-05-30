package com.spring.myweb.command;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 CREATE TABLE freeboard (
    bno INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(300) NOT NULL,
    writer VARCHAR(50) UNIQUE NULL,
    content VARCHAR(3000) NOT NULL,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT NULL
);
 */

@Getter @Setter @ToString
public class FreeBoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	//하나의 게시물에 달린 댓글의 개수
	private int replyCnt;
	
	
	
	
	
	
}
