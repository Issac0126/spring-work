package com.spring.myweb.freeboard.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.FreeBoardVO;

@ExtendWith(SpringExtension.class) //테스트 환경을 만들어 주는 junit5 객체 로딩
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/db-config.xml"	
})
public class FreeBoardMapperTest {

	@Autowired
	private IFreeBoardMapper mapper;
	
	//단위 테스트 (unit test) - 가장 작은 단위의 테스트
	//테스트 시나리오
	//- 단언 (Assertion) 기법
	
	@Test
	@DisplayName("Mapper 계층의 regist를 호출하면서"
			+ " FreeBoardVO를 전달하면 데이터가 INSERT 된다.")
	void registTest() {
		//given - when - then 패턴을 따른다. (생략 가능)
		
		//given: 테스트를 위해 주어질 데이터 (ex/ parameter)
		FreeBoardVO vo = new FreeBoardVO();
		vo.setTitle("귄");
		vo.setWriter("lun1234");
		vo.setContent("만두좋아행복한펭귄");
		
		//when: 테스트 실제 상황
		mapper.regist(vo);
		
		//then: 테스트 결과를 확인.
	}
	
	
	@Test
	@DisplayName("전체 글 목록을 조회하고,"
			+ " 조회한 글 갯수를 파악했을 때 하나가 조회될 것이다.")
	void getListTest() {
		
		//given (줄 게 없으므로 건너뜀)
		
		//when
		List<FreeBoardVO> list = mapper.getList();
		
		//then 
		list.forEach(vo -> System.out.println(vo));
		/*	forEach 풀이: 
		 * 	for(FreeBoardVO vo : list) {System.out.println(vo);} */
		
		assertEquals(1, list.size()); // -> 나는 list.size가 1이리라 단언한다.
		//위의 값이 true라면 정상작동, false라면 예외처리된다.
		
	}
	
	@Test
	@DisplayName("글 번호가 2번인 글을 조회하면 글쓴이는 펭귄일 것이고 글 내용은 '만두좋아펭귄'일 것이다.")
	void getContentTest() {
		//given
		int bno = 3; //글 번호
		
		//when
		FreeBoardVO vo = mapper.getContent(bno);
		
		//then
		assertEquals("lun1234", vo.getWriter());
		assertEquals("만두좋아행복한펭귄", vo.getContent());
//		assertTrue(vo.getContent().equals("만두좋아행복한펭귄")); //트루여부 판단
//		assertNull(vo); //vo에 들어온 값이 null이면 통과
		
		
		System.out.println("3번 글 조회: "+vo);
	}
	
	
	@Test
	@DisplayName("글 번호가 1번인 글의 제목과 내용을 수정 후 다시 조회했을 때"
			+ " 제목이 수정한 제목으로 바뀌었음을 단언한다.")
	void updateTest() {	
		//given
		FreeBoardVO vo = new FreeBoardVO();
		vo.setBno(1);
		vo.setTitle("1번 글 수정");
		vo.setContent("박박");
		
		//when
		mapper.update(vo); //업데이트 파이띵
		FreeBoardVO upVo = mapper.getContent(vo.getBno());
		
		//then
		assertEquals(vo.getTitle(), upVo.getTitle());
		assertEquals("박박", upVo.getContent());
		System.out.println("업데이트 완료!");
	
	}
	
	@Test
	@DisplayName("글 번호가 2번인 글을 삭제한 후에 리스트를 전체 조회했을 때"
			+ " 글의 개수는 1개일 것이고, 2번 글을 조회했을 때 null이 반환되어야 한다.")
	void deleteTest() {
		//given
			int bno = 2;
		
		//when
			mapper.delete(bno);
			List<FreeBoardVO> list = mapper.getList();
			FreeBoardVO vo = mapper.getContent(bno);
			
		//then
			assertEquals(1, list.size());
			assertNull(vo);
		System.out.println("삭제 완료!");
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
