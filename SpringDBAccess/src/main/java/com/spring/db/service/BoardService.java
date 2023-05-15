package com.spring.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.db.model.BoardVO;
import com.spring.db.repository.IBoardDAO;
import com.spring.db.repository.IBoardMapper;
import com.spring.db.repository.IScoreMapper;

@Service
public class BoardService implements IBoardService {

//	@Autowired
//	@Qualifier("boardDAO")
//	private IBoardDAO dao;
	
	@Autowired
	private IBoardMapper mapper;
	
	@Override
	public void insertBoard(BoardVO vo) {
		mapper.insertArticle(vo);
	}

	@Override
	public List<BoardVO> seleteAllBoard() {
		return mapper.getArticles();
	}
	
	@Override
	public void deleteBoard(int bNo) {
		mapper.deleteArticle(bNo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		mapper.updateArticle(vo);
	}

	@Override
	public BoardVO getArticle(int bNo) {
		return mapper.getArticle(bNo);
	}

}
