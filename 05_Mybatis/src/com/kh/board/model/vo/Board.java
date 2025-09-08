package com.kh.board.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Board {
	//필드
	private int boardNo;//BOARD_NO NUMBER
	private String boardTitle;//BOARD_TITLE VARCHARE2
	private String boardContent;//BOARD_CONTEMT VARCHAR2
	private String  boardWriter;//BOARD_WRITER NUMBER FOREIGN KEY(USERNO) 
	private Date createDate;//CREATE_ DATE DATE
	private String deleteStatus;//DELETE_STATUS CHAR(1) 딜리트를 할때 애초에 삭제여부 컬럼을 만들어서 업데이트를 통해 구현한다 이게 조흥ㅁ
	// 모든 필드에 대한 매개변수 생성자
	//게터
	//이퀄스,해쉬코드
	public Board() {}
		//모든필드에대한 매개변수생성자
		
		public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, Date createDate,
			String deleteStatus) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.deleteStatus = deleteStatus;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(boardContent, boardNo, boardTitle, boardWriter, createDate, deleteStatus);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return Objects.equals(boardContent, other.boardContent) && boardNo == other.boardNo
				&& Objects.equals(boardTitle, other.boardTitle) && Objects.equals(boardWriter, other.boardWriter)
				&& Objects.equals(createDate, other.createDate) && Objects.equals(deleteStatus, other.deleteStatus);
	}
	
}
