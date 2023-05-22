package com.spring.myweb.command;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 CREATE TABLE users (
    user_id VARCHAR(50) PRIMARY KEY,
    user_pw VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_phone1 VARCHAR(50),
    user_phone2 VARCHAR(50),
    user_email1 VARCHAR(50),
    user_email2 VARCHAR(50),
    addr_basic VARCHAR(300),
    addr_detail VARCHAR(300),
    addr_zip_num VARCHAR(50),
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
 */

@Getter @Setter @ToString
public class UserVO {

	private String userId, userPw, userName, userPhone1, userPhone2, 
				userEmail1, userEmail2, addrBasic, addrDetail, addrZipNum;
	private LocalDateTime regDate;
	
	
	/*
	 한 명의 회원은 글을 여러 개 작성할 수 있다. => 1:n 관계 
	 마이페이지에서는 특정 회원이 작성한 글 목록을 나타내야 한다.
	 테이블이 다른 정보(users, freeBoard)를 마이페이지에서 한 번의 DB 연동으로 가져오기 위해
	 JOIN 문법으로 테이블을 합친 뒤 원하는 컬럼을 선택해서 데이터를 가져온다.
	 */
	
	//1이 UserVO 이기 때문에 UserVO 안에 N의 값을뜻하는 FreeBoardVO 객체의 모음을
	//저장할 수 있는 리스트를 선언.
	//1:N 관계의 테이블을 list형태로 선언한다.
	private List<FreeBoardVO> userBoardList;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
