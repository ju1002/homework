package com.kh.statement.view;

import java.util.Scanner;

import com.kh.statement.controller.SoccerController;

/**
 * SoccerView클래스는 JDBC실습을 위해 생성하였으며
 * 사용자에게 입력을 받고 화면상의 출력을 수행하는 메소드를 제공
 * @author 배주영
 * @version 0.2
 * @date 2025-09/02
 * 
 */
public class SoccerView {
	private Scanner sc = new Scanner(System.in);
	private SoccerController st = new SoccerController();
	
	public void mainmenu() {
		System.out.println("선수 관리 프로그램");
		System.out.println("1.선수 추가");
		System.out.println("2.선수 전체 조회");
		System.out.println("3.선수 급여 조회");
		System.out.println("4.선수번호 키워드로 조회");
		System.out.println("5.선수 정보 변경");
		System.out.println("6.선수 방출");
		System.out.println("9.프로그램 종료");
		int menuNo = sc.nextInt();
		sc.nextLine();
		
	switch(menuNo) {
	case 1 : plus(); break;
	case 2 : break;
	case 3 : break;
	case 4 : break;
	case 5 : update();break;
	case 6 : break;
	case 9 : System.out.println("프로그램을 종료합니다."); return;
	default : System.out.println("없는 메뉴입니다.");
	
	
	}
	}
	public void plus () {
		System.out.println("선수 추가 메뉴입니다.");
		System.out.println("선수 이름을 입력해 주세요");
		String userName = sc.nextLine();
		System.out.println("선수 포지션을 입력해 주세요");
		String userPosition = sc.nextLine();
		System.out.println("선수 팀을 입력해 주세요");
		String userTeam = sc.nextLine();
		
		st.plus(userName, userPosition , userTeam);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
