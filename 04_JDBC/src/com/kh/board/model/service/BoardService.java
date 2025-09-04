package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

public class BoardService {
	 private Connection conn = null;
	 public BoardService() {
		 conn = JDBCTemplate.getConnection(); // 커넥션 초기화
	 }
	 public int insertBoard(BoardDTO bd) {
		 //내가 입력한 값을 가지고
		 //BOARD테이블에 한 행 INSERT해줘
		 //1. 값의 유효성 검증
		 int result = 0;
		 if("".equals(bd.getBoardTitle().trim())) {
			return result; //유효하지 않는 값이니까 다시 돌아가라
		 }
		 //2.인증 /인가  먼저 아이디가 존재하는 지 테이블에 간다. INSERT하기 전에 SELECT먼저 한다.
		 Member member = new MemberDao().findById(conn, bd.getBoardWriter());		 
		 if(member != null) { //사용자가 입력한데 있다는거다.
			int userNo = member.getUserNo();
			Board board = new Board(0,
									 bd.getBoardTitle()
									 , bd.getBoardContent()
									 ,String.valueOf(userNo)//userNo는 int 형인데 우리는 스트링을 해놔서 자료형을 바꿔주는데 그 방법이 저거다
									 ,null
									 ,null);
			result = new BoardDAO().insertBoard(conn,board);
		 }
		 if(result >0) {
			 JDBCTemplate.commit(conn);
			 
		 }
		 JDBCTemplate.close(conn);
		 return result;
	 }
	 public List<Board> selectBoardList(){
		 List<Board> boards =new BoardDAO().selectBoardList(conn);
		 new BoardDAO().outputHTML(conn);
		 JDBCTemplate.close(conn);
		 return boards;
		 
	 }
	 public Board selectBoard(int boardNo) {
		 //보드 넘버 시퀀스 가지고 만든건데
		 //1부터 시작인데 이거 막 이상한 숫자 0이한인 값 들어오면 갈 필요없는데 시퀀스로 해놔서
		 Board board = null;
		 if(boardNo>0) {
		board = new BoardDAO().selectBoard (conn ,boardNo);
		 }
		 JDBCTemplate.close(conn);
		 return board;
	 }
	 public int deleteBoard(int boardNo) {
		 int result = new BoardDAO().deleteBoard( conn, boardNo);
		 if(result>0) {
			 JDBCTemplate.commit(conn);
			 }
		 JDBCTemplate.close(conn);
		 return result;
		 
	 }
}
	// new BoardDAO().outputHTML(conn);
	 //JDBCTemplate.close(conn);
	 


