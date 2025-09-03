package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.service.MemberService;
import com.kh.statement.model.vo.Member;

/**
 * VIEW에서 넘어온 요청을 처리해주는 클래스입니다.
 * 1. 메소드로 전달된 데이터를 가공처리 한 뒤 DAO로 전달합니다.
 * 2. DAO로부터 반환받은 값을 
 * 
 */
public class MemberController {
	public int save(String userId, String userPwd, String userName , String email) {
		/**
		 * 
		 *  VIEW로 부터 전달받은 문자열값 다섯 개를 DAO로 전달하는 메소드
		 * @param userId :사용자에게 입력받은 ID값
		 * @param userPwd : 사용자에게 입력받은 비밀번호 값
		 * @param userName : 사용자에게 입력받은 이름
		 * @param email : 사용자에게 입력받은 이메일
		 * @return  : 반환 어떻게 할건지
		 * @throuws : IllaArgumentException : 잘못된 인자가 전달되면 발생할  수 있음 
		 */
		//Dao로 넘어갈때는 1개의 값으로 전달해야한다.
		//1. 여기서는 데이터 가공할거다 => 넘어온 인자값이 두 개 이상일 시 
//								    어딘가에 담을 것(Vo, Map, DTO)
		// 전달된 인자값들을 Member객체의 필드에 담기
		//1.매개변수 생성자를 호출하여서 객체 생성과 동시에 필드값을 대입하는 방 법
		//2. 기본생성자로 객체를 생성한 뒤 setter메소드를 호출하는방법
		Member member = new Member(userId, userPwd, userName , email); //1번 방법 사용
		//컨트롤 1절 끝
		int result = new MemberService().save(member);
		return result;
		//요청 처리 후 결과값 반환
	}
	
	public List<Member> findAll() {
		//1. 데이터 가공 == 할게 없음
		//2. DAO 호출 회원들의 정보를 보내달라고 요청
		List<Member> members =new MemberService().findAll();
		return members;
	}
	
	
	
	/**사용자에게 입력받은 아이디 값을 이용해서 검색요청을 처리해주는 멤소드
	 * @param userId : 사용자가 입력한 검색하고자 하는 문자열
	 * 
	 */
	
	public Member findById(String userId) {
		//1.데이터 가공 -> 자료값이 하나밖에 없어서 넘어가
		//DAO객체 생성 후 메소드 호출
		
		
		Member member = new MemberService().findById(userId);
		return member;
	}
	
	
	
	
	
	public List<Member> findByKeyword(String keyword) {
		//결과값이 나중에 어떻게돌아올지 생각을 해야함
		//SELECT -> ResultSet-> Member -> List<Member>
		List<Member> members = new MemberService().findByKeyword(keyword);
		return members;
	}
	
	
	
	
	public int update (String userId , String userPwd , String newPassword) {
		//1. 데이터 가공 인자값이 많아서 담을 공간이 필요함
		PasswordDTO pd = new PasswordDTO(userId,userPwd,newPassword);
		//2. 요청처리 
		//아이디랑 비밀번호랑 바꿀비밀번호 줓테니까
		//아이디랑 비밀번호 맞는지 확인 하고 바꿀비밀번호로 비밀번호 컬럼값 바꿔저
		int result =new MemberService().update(pd);
	return result;}

	public int delete(String userId, String userPwd) {
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		int result = new MemberService().delete(member);
		return result;	
	
	}
}
