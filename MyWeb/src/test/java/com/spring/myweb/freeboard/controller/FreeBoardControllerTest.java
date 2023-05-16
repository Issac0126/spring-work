package com.spring.myweb.freeboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.FreeBoardController;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/db-config.xml",
		"file:src/main/webapp/WEB-INF/config/servlet-config.xml"
})
@WebAppConfiguration
public class FreeBoardControllerTest {

	/*
	 - 테스트 환경에서 가상의 DispatcherServlet을 사용하기 위한 객체 자동 주입
	 - WebApplicationContext는 Spring에서 제공되는 servlet들을 사용할 수 있도록
	 정보를 저장하는 객체이다.
	 */
	@Autowired
	private WebApplicationContext ctx;
//	@Autowired
//	private FreeBoardController controller;
	
	
	// MockMvc는 웹 어플리케이션을 서버에 베포하지 않아도 스프링 MVC 동작을
	//구현할 수 있는 가장의 구조를 만들어 준다. 
	private MockMvc mockMvc;
	
	
	@BeforeEach //테스트 시작 시 다른 메서드 실행 전에 항상 먼저 구동되는 기능들을 선언
	public void setup() {
		//(시작 전) 가상 구조를 세팅
		
		//스프링 컨테이너에 등록된 모든 빈과 의존성 주입까지 로드해서 사용이 가능한 코드.
		// 컨테이너를 전부 가져옴
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		//테스트할 컨트롤러를 수동으로 주입. 하나의 컨트롤러 자체만 테스트를 진행할 때 쓰인다.
		// 컨트롤러만 살짝 가져옴.
//		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	@DisplayName("/freeboard/freeList 요청 처리 과정 테스트")
	void testList() throws Exception {
		ModelAndView mv =
		mockMvc.perform(MockMvcRequestBuilders //perform = 동작을 하라는 명령
				.get("/freeboard/freeList")) //freeboard의 freeList로 get요청이 들어왔다는 가상의 환경 생성
				.andReturn() //결과를 받아서
				.getModelAndView(); //ModelAndView로 꺼냄.
//				.getModelMap() //모델에 넣은 데이터들. 값을 바로 꺼낼때 사용됨.
		
		System.out.println("Model 내에 저장한 데이터: "+mv.getModelMap());
		System.out.println("응답 처리를 위해 사용할 페이지: "+mv.getViewName()); 
		//void에 맞추어 왔던 요청 이름의 jsp로 돌아감.
		
	}
	
	
	@Test
	@DisplayName("게시글 등록 요청 처리 과정 테스트")
	void testInsert() throws Exception {
		String viewPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/freeboard/freeRegist")
				.param("title", "테스트 새 글 제목")
				.param("content", "테스트 새 글 내용")
				.param("writer", "user77"))
				.andReturn().getModelAndView().getViewName();
		System.out.println("viewName: "+ viewPage);
	}
	
	
	@Test
	@DisplayName("3번 글 상세보기 요청을 넣으면 컨트롤러는"
			+ " DB에서 가지고 온 글 객체를 model에 담아서 jsp로 이동시킬 것이다.")
	// /freeboard/content -> Get
	void testContent() throws Exception {
		
		ModelAndView mv =
		mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/detail?bno=3"))
		.andReturn().getModelAndView();
		
		System.out.println("상세보기 데이터: "+mv.getModelMap());
		//완성! 은 했으나 int bno 값 지정을 어디서 하는지 알아보기.
	}
	
	
	@Test
	@DisplayName("3번 글의 제목과 내용을 수정하는 요청을 post 방식으로 전송하면 수정이 진행되고,"
			+ " 수정된 글의 상세보기 페이지로 이동할 것이다. freeboard/modify.jsp?")
	public void testModify() throws Exception {
		ModelAndView mv = 
		mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/modify?bno=4")
				.param("title", "시간 포함 수정한 제목")
				.param("content", "시간 포함 수정한 내용"))
			.andReturn().getModelAndView();
//		assertEquals("redirect:/freeboard/content?bno="+bno, null)
	}
	
	
	@Test
	@DisplayName("3번 글을 삭제하면 목록 재요청이 발생할 것이다.")
	//  /freeboard/delete  -> Post
	void testDelete() throws Exception {
//		ModelAndView mv =
//		mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/delete?bno=3"))
//		.andReturn().getModelAndView();
		assertEquals("redirect:/freeboard/freeList", 
				mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/delete").param("bno", "4"))
				.andReturn().getModelAndView().getViewName());
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
