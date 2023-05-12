package com.spring.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.db.model.BoardVO;
import com.spring.db.model.ScoreVO;

@Repository
public class BoardDAO implements IBoardDAO {
	
	//전체 목록 불러오기용 inner
	class ArticleList implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int Num) throws SQLException {
			System.out.println("Num: "+ Num);
			
			BoardVO vo = new BoardVO(
						rs.getInt("board_no"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("reg_date").toLocalDateTime()
					);
			return vo;
		}
	} //전체 목록 불러오기 END
	
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void insertArticle(BoardVO vo) {
		String sql="INSERT INTO jdbc_board (writer, title, content) VALUES (?, ?, ?)";
		template.update(sql, vo.getWriter(), vo.getTitle(), vo.getContent());
	}

	
	@Override
	public List<BoardVO> getArticles() {
		String sql="SELECT * FROM jdbc_board ORDER BY board_no";
		List<BoardVO> list = template.query(sql, new ArticleList());
		System.out.println(list);
		return list;
	}

	
	@Override
	public BoardVO getArticle(int bno) {
		return null;
	}

	
	@Override
	public void deleteArticle(int bno) {

	}

	
	@Override
	public void updateArticle(BoardVO vo) {

	}

}
