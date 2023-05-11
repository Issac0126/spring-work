package com.spring.basic.bodel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private List<String> hobby; 
	//변수명은 파라미터 변수명과 똑같아야한다.
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
