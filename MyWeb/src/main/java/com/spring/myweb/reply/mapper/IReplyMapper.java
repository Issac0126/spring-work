package com.spring.myweb.reply.mapper;

import java.util.List;
import java.util.Map;

import com.spring.myweb.command.ReplyVO;

public interface IReplyMapper {

	
	//댓글 등록
	void replyRegist(ReplyVO vo);
	
	//목록 요청
	List<ReplyVO> getList(Map<String, Object> data);
	
	//댓글 개수(페이징, PageCreator은 사용하지 않는다.) 
	int getTotal(int bno);
	
	
	//비밀번호 확인 (암호화 진행하면 결과가 true, false로 나오기 때문)
	boolean pwCheck(ReplyVO vo); 
	
	//댓글 수정
	void update(ReplyVO vo);
	
	//댓글 삭제
	void delete(int rno);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
