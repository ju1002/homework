package com.kh.statement.run;

import com.kh.board.view.BoardView;
import com.kh.common.JDBCTemplate;
import com.kh.statement.view.MemberView;

public class MemberRun {

	public static void main(String[] args) {
		JDBCTemplate.RegistDriver();
		MemberView mv = new MemberView();
		mv.mainMenu();
		//BoardView bv = new BoardView();
		//bv.mainMenu();
		
		
		
		
		
	}

}
