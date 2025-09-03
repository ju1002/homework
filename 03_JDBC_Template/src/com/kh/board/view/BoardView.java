package com.kh.board.view;

import java.util.List;
import java.util.Scanner;

import com.kh.board.controller.BoardController;
import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;

public class BoardView {
	private Scanner sc =new Scanner(System.in);
	private BoardController bc = new BoardController();
	/*SELECT/INSERT/UPDATE/DELETE
	 * 메소드 명을 짓는다 하면
	 *보통 공공기관 같이 좀 공적인 스타일 
	 * 
	 * BOATD를 예시로 들어보면
	 *  INSERT : insertBoard()
	 *  UPDATE : updateBoard()
	 *  DELETE : deleteBoard()
	 *  SELECT(다수행) :selectBoardList()
	 *  SELECT(단일행) :selectBoard()
	 *  -----------------------------------------
	 *  회사같은 곳 에서 사용 
	 *  
	 *  INSERT : save()
	 *  UPDATE : update()
	 *  DELETE :deleteByXXX()
	 *  SELECT(다수행) : findALl()
	 *  SELECT(단일행) : findByXXX()
	 */
	
	
	
	public void mainMenu() {
		while(true) {
			System.out.println("게시판 서비스 입니다.");
			selectBoardList();
			//전체 게시글 목록 조회
			System.out.println("\n====================================");
			System.out.println("1. 게시글 상세 조회");
			System.out.println("2. 게시글 작성하기");
			System.out.println("3. 게시글 삭제하기");
			System.out.println("9. 회원메뉴로 돌아가기");
			System.out.println("메뉴를 선택해주세요");
			int menuNo =sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : selectBoard();break;
			case 2 : insertBoard();break;
			case 3 : deleteBoard();break;
			case 9 : System.out.println("다시 돌아가요");return;
			default : System.out.println("없는 메뉴입니다.");
			}
		}
		
	}
	
	public void insertBoard() {
		System.out.println("게시글 작성 서비스입니다.");
		
		System.out.println("아이디를 입력해주세여");
		String  userId = sc.nextLine();
		
		System.out.println("제목을 입력해주세요");
		String boardTitle= sc.nextLine();
		
		System.out.println("본문을 입력해주세요");
		String boardContent = sc.nextLine();
		
		//뷰 1절 끝
		//결론은 db로 전달해야 하는데 한번에 못가니까 먼저 컨트롤러에 넘겨줌 메소드를 통애 넘겨줌
		int result =bc.insertBoard(new BoardDTO(boardTitle, boardContent,userId)); //DTO에 한번에 담아서 보내주자
		if(result>0) {
			System.out.println("게시글 작성 성공");
		}else {
			System.out.println("게시글작성 실패");
			
		}
		
		
		
		
		
	}
	private void selectBoardList() {
		System.out.println();
		//컨트롤러에게 게시글 전체를줘라
		List<Board> boards = bc.selectBoardList();
		//조회된걸 출력해야허는데 있을 수 있고 없을 수 있으니까
		if(!boards.isEmpty()) {
			//있긴한데 얼마나 있을지 몰라
			boards.stream().map(b->"\n게시글번호"+b.getBoardNo()
								    +"\t제목 :"+b.getBoardTitle()
								    +"\t\t 작성자 :"+b.getBoardWriter()
								    +"\t작성일 :"+ b.getCreateDate())
								    .forEach(System.out::println);
		}else {
			System.out.println("게시글이 존재하지 않습니다");
			System.out.println("첫게시글에 주인공이 돼보세요");
		}
	}
	public void selectBoard() {
		System.out.println("게시글 상세조회 서비스입니다.");
		System.out.println("조회할 게시글 번호를 입력해주세요");
		int boardNo =sc.nextInt();
		sc.nextLine();
		Board board = bc.selectBoard(boardNo);
		
		if(board !=null) {
			System.out.println("\n\n제목 :"+board.getBoardTitle());
			System.out.println("\n 작성자 :"+ board.getBoardWriter());
			System.out.println("\n 작성일 :"+ board.getCreateDate());
			System.out.println("\n본문");
			System.out.println("================================");
			System.out.println(board.getBoardContent());
			System.out.println("=================================");
		}else {
					System.out.println("존재하지 않는 게시글 번호 입니다.");
		}
		while(true) {
			System.out.println("목록으로 돌아가시려면 돌아가기를 입력하세요");
			String exit = sc.nextLine();
			if("돌아가기".equals(exit)) {
				return;
			}
		}
		
	}
	public void deleteBoard() {
		System.out.println("주세요 보드번호");
		int boardNo = sc.nextInt();
		sc.nextLine();
		if(bc.deleteBoard())
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
