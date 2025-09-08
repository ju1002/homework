package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.dao.BoardRepository;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.common.Template;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

public class BoardService {
	 private Connection conn = null;
	 public BoardService() {
		 conn = JDBCTemplate.getConnection(); // 커넥션 초기화
	 }
	 public int insertBoard(BoardDTO bd) {
		 SqlSession session = Template.getSqlSession();
		 int result = new BoardRepository().insertBoard(bd,session);
		 
		
	 }
	 public List<Board> selectBoardList(){
		 
		 SqlSession session = Template.getSqlSession();
		 
		  List<Board> boards =new BoardRepository().selectBoardList(session);
		 return boards;
		 
		 
		 
		 
		/* List<Board> boards =new BoardDAO().selectBoardList(conn);
		 new BoardDAO().outputHTML(conn);
		 JDBCTemplate.close(conn);
		 return boards;
		 */
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 public Board selectBoard(int boardNo) {
		 //보드 넘버 시퀀스 가지고 만든건데
		 //1부터 시작인데 이거 막 이상한 숫자 0이한인 값 들어오면 갈 필요없는데 시퀀스로 해놔서
		 SqlSession session = Template.getSqlSession();
		 
		Board board =  new BoardRepository().selectBoard(session, boardNo);
		session.close();
		return board;

		 
	
	// new BoardDAO().outputHTML(conn);
	 //JDBCTemplate.close(conn);
	 
}
}
