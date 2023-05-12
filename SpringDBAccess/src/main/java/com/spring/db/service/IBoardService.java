package com.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.db.model.BoardVO;

@Service
public interface IBoardService {

	//글 등록 처리하는 메서드
	void insertBoard(BoardVO vo);
	
	//글 전체 조회 
	List<BoardVO> seleteAllBoard();
	
	//글 삭제
	void deleteBoard(int bNo);
	
	//글 수정
	void updateBoard(BoardVO vo);
	
	
	
	
	
}
