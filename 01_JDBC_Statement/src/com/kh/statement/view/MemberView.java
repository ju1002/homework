package com.kh.statement.view;

import java.util.List;
import java.util.Scanner;

import com.kh.statement.controller.MemberController;
import com.kh.statement.model.vo.Member;

// alt shift  j  : 이쁘게 주석 만드는 법
/**MemberView클래스는 JDBC 실습을 위헤 생성하였으며,
 * 사용자에게 입력 및 출력을 수행하는 메소드를 제공합니다.
 * @author 배주영
 * @version 0.1
 * @date 2025-09-01
 */
public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	//상속구조가 아닌 클래스를 필드로 불러서 사용하는 것을 합성이라한다.
	// 컨트롤러에게 보내줄거임
	/**프로그램 구동 시 사용자에게 메인화면을 출력해주는 메소드입니다.
	 * 
	 */
	public void mainMenu() {
		/*2020/09/01 오늘의 실습 겸 숙제
		 * 나만의 테이블 만들고
		 * 나만의 테이블에 INSERT/전체조회/ 유니크제약조건 걸려있는 컬럼으로 조회
		 * LIKE키워드 써서 조회
		 * 
		 * 
		 */
		
		
		
		//메인화면이 떴을 때 띄어줄 내용 작성
		while(true) {
			System.out.println("----------회원 관리 프로그램---------------");
			System.out.println("1.회원추가");
			System.out.println("2.회원전체 조회");
			System.out.println("3.회원아이디 조회");
			System.out.println("4. 회원이름 키워드로 조회");
			System.out.println("5. 회원정보 변경");
			System.out.println("6. 회원탈퇴");
			System.out.println("9. 프로그램 종료");
			System.out.println("메뉴를 선택해주세요");
			int menuNo =sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : save();break;
			case 2 : findAll(); break;
			case 3 : findById(); break;
			case 4 : findByKeyword();break;
			case 5 : break;
			case 6 : break;
			case 9 : System.out.println("프로그램을 종료합니다.");return;
			default : System.out.println("없는 메뉴입니다.");
			
			}
			
			
			
		}
		
	}
	private void save() {
		/** Member테이블에 INSERT할 값을 입력받는 화면을 출력해주는메소드
		 * 컬럼에INSERT할 값들을 모두 입력받은 후 입력받은 값 컨트롤러로 전달
		 * 
		 */
		System.out.println("회원추가");
		
	//자동 완성하는 법 Sysout 하고 컨트롤 스페이스 누르면 자동완성
		System.out.println("이름을 입력해주세요");
		String userName = sc.nextLine();
		System.out.println("아이디를 입력해주세요");
		String userId = sc.nextLine();
		System.out.println("비밀번호를 입력해주세요");
		String userPwd = sc.nextLine();
		System.out.println("이메일을 입력해주세요");
		String email = sc.nextLine();
		
		
		//입력받았으니까 전달한다.
		//전달하는 방법은 메소드를 호출하면서  인자값으로 보내줌
		int result = mc.save(userId, userPwd,email,userName);
		if(result>0) {
			System.out.println("회원가입의 실패했습니다.");
		}else {
			System.out.println("회원가입의 실패했습니다.");
		}
	}
	/**회원 전체 조회 요청 시 Member테이블에 존재하는 모든 회원의 정보를 출력해주는 메소드
	 * 
	 */
	public void findAll() {
		System.out.println("/n회원 전체 조회");

		
	}
	/**
	 *  사용자로부터 회원의 아이디를 입력받아서
	 *  Member테이블로부처 아이디 값을 비교해서 조회한 뒤
	 *  동인한 아이디 값을 가진 행의 데이터를 출력해주는 메소드
	 */
	public void findById() {
		
		System.out.println("\n 아이디로 겁색 서비스 입니다.");
		System.out.println("아이디를 입력해주세여");
		String userId = sc.nextLine();
		Member member = mc.findById(userId);
	if(member !=null){
		System.out.println(userId + "님의 검색 결과입니다.");
		System.out.println("========================");
		System.out.println("아이디 : " + member.getUserId()+",");
		System.out.println("비밀번호 :"+ member.getUserPwd()+",");
		System.out.println("이름 :"+member.getUserName()+",");
		System.out.println("이메일 :"+member.getEmamil()+",");
		System.out.println("가입일 : "+ member.getEnrollDate());
		System.out.println();
		}else {System.out.println("존재하지 않는 아이디 입니다.");
			
		}
		
	}
	public void findByKeyword()
	{
		System.out.println("\n 회원 이름 키워드로 검 색");
		System.out.println("검색하고자 하는 키워드를 입력해주세요");
		String keyword = sc.nextLine();
		List<Member> members = mc.findByKeyword(keyword);
		//조회결과가 있을 수도 있고 없을 수도 있고
		if(members.isEmpty()) {
			System.out.println("조회결과가 존재하지 않습니다.");
		}else {
			for(Member member : members) {
				System.out.println(member);
			}
		}
	}























	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
