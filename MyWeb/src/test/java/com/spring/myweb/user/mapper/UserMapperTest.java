package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/db-config.xml")
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper;
	
	
	// 회원가입
	@Test
	@DisplayName("회원가입을 진행했을 때 회원가입에 성공해야 한다.")
	void registTest() {
		UserVO vo = new UserVO();
		vo.setUserId("abc1234");
		vo.setUserPw("aaa1111!");
		vo.setUserName("홍길동");
		
		mapper.join(vo);
	}
	
	// 아이디 중복 확인
	@Test
	@DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴이 되어야 한다.")
	void checkIdTest() {
		
		//begin
//		String id = "hamgam8282";
		String id = "abc1234";
		
		//when
		int check = mapper.idCheck(id);
		
		//then
		System.out.println("체크된 숫자"+check);
		assertEquals(1, check);

	}
	
	
	// 회원 로그인
	@Test
	@DisplayName("존재하는 회원 아이디와 올바른 비밀번호를 입력했을 시 회원의 정보가 리턴되어야 한다.")
	void loginTest() {
		//begin
		String id = "abc1234";
		String pw = "aaa1111!";
		
		//when
		UserVO vo = mapper.login(id, pw);
		
		//then
		assertNotNull(vo);
	}
	
	// 회원 정보 얻어오기
	@Test
	@DisplayName("존재하지 않는 회원의 아이디를 입력하면 null이 올 것이다.")
	void getInfoTest() {
		//begin
		String id = "ham8282";
		
		//when
		UserVO vo = mapper.getInfo(id);
		assertNull(vo);
		 
	}
	
	//회원 정보 수정
	@Test
	@DisplayName("아이디를 제외한 회원의 정보를 수정할 수 있다.")
	void updateTest() {
		UserVO vo = mapper.getInfo("abc1234");
		vo.setUserName("뉴김삐뽀");
		vo.setUserPhone1("010");
		vo.setUserPhone2("12345678");
		
		mapper.updateUser(vo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}










