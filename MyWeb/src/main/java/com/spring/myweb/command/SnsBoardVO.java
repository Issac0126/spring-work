package com.spring.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor 
public class SnsBoardVO {

	private int bno;
	private String writer;
	private String uploadPath; // C:/test/upload
	private String fileLoca;  // 20230527 등 날짜별로 폴더를 생성할 예정
	private String fileName, fileRealName, content;
	private LocalDateTime regDate;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
