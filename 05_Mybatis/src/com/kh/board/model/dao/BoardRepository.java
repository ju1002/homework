package com.kh.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;

public class BoardRepository {
	
//sql실행결과를 돌려줌	
	public List<Board> selectBoardList(SqlSession session)
	{
		return session.selectList("boardMapper.selectBoardList");
		
	}
	
	public Board selectBoard(SqlSession session, int boardNo) {
		return session.selectOne("boardMapper.selectBoard");
		
	}
	public int insertBoard(SqlSession session , BoardDTO bd) {
		return session.insert("boardMapper.insertBoard");
	}
}
