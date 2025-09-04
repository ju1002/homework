package view;

import java.util.List;
import java.util.Scanner;

import controller.EmployeeController;
import model.vo.Employee;

//화면 출력 용
public class EmployeeView {
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();
	public void mainMenu() {
		while(true) {
		System.out.println("회사 메뉴");
		System.out.println("===============================");
		System.out.println("1.회원전체조회");
		System.out.println("2.부서별사원조회");//부서명을 입력받아서 부서가 동일한 사원을 조회
		System.out.println("3.직급별 사원조회");
		System.out.println("4.사원상세조회"); //사번을 입력받아서 전체조회
		System.out.println("5.급여가 높은 상위 다섯명 조회");
		System.out.println("6.급여가 낮은 하위 다섯명 조회");
		System.out.println("7.사원 추가기능");
		System.out.println("8.사원 정보 수정");//사번을 입력 받아 급여, 직급,부서 수정
		System.out.println("9.사원퇴사 기능");
		System.out.println("메뉴를 선택해주세요");//사번을 입력받아 퇴사 여부 ,퇴사일 수정
		int menuNo = sc.nextInt();
		switch(menuNo) {
		case 1 : findAll();break;
		case 2 : findUser();break;
		case 3 : findLevel();break;
		case 4 : selectUser();break;
		case 5 : break;
		case 6 : break;
		case 7 : break;
		case 8 : break;
		case 9 : break;
		default : System.out.println("없는 메뉴입니다.");
		}
		return;
		}
	}
	public void findAll()
	{
		System.out.println("회원전체 조회 서비스입니다");
	 List<Employee> employees =  ec.findAll();
	 if(employees.isEmpty()) {
		 System.out.println("조회할게 없어요");
	 }else {//성공이니까 안에있는것들을 넣어서 보여줘야지
		 //그럴려면 배열 안에 있는것들을 순차적으로 보여줘야지
		 //그방법으로는 향상된 for문을 사용해 이거는 배열을 순차적으로 보여주는 문법이야
		 //for(자료형 변수명 : 배열){순차적으로 보여줄 것들 작성}
		 for(Employee employee : employees) {
		System.out.println(employee.getUserNo()+",");
		System.out.println(employee.getSalary()+",");
		System.out.println(employee.getUserName()+",");
		System.out.println(employee.getUserName()+",");
		System.out.println(employee.getUserPart()+",");
		System.out.println(employee.getUserLevel());
		 
		 
		 
		 System.out.println("조회 성공");
		 }
	 }
		
		
	}
	public void findUser() {
		System.out.println("부서별 사원조회 서비스입니다.");
		System.out.println("부서명을 입력해주세요");
		String userName = sc.nextLine();
		
		Employee employee =ec.findUser(userName);
		if(employee !=null) {
			System.out.println(userName+"의 검색 결과입니다");
			System.out.println("사원이름: "+employee.getUserName());
			
			System.out.println(employee);
		}
		else {
			System.out.println("없는 부서명입니다.");
		}}
	
	public void findLevel() {
		System.out.println("직급별 사원 조회 서비스입니다");
		System.out.println("직급을 입력해주세요");
		String level = sc.nextLine();
	
		Employee employee =ec.findLevel(level);
		while(employee != null) {
			if(employee != null) {
				System.out.println(level +"의 검색 결과 입니다.");
				System.out.println(employee);
				
			}else {
				System.out.println("없는 직급입니다.");
			}
		}
	}
	public void selectUser() {
		System.out.println("상세조회서비스입니다");
		System.out.println("사번을 입력해주세요");
		int userNo = sc.nextInt();
		Employee employee =ec.selectUser(userNo);
		while(employee != null) {
			if(employee != null) {
				System.out.println(userNo +"의 검색 결과 입니다.");
				System.out.println(employee.getUserNo()+",");
				System.out.println(employee.getSalary()+",");
				System.out.println(employee.getUserName()+",");
				System.out.println(employee.getUserName()+",");
				System.out.println(employee.getUserPart()+",");
				System.out.println(employee.getUserLevel());
				 
				
			}else {
				System.out.println("없는 직급입니다.");
		}}
	



























}}
