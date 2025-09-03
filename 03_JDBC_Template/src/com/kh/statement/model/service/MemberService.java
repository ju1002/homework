package com.kh.statement.model.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberService {
	/*클라이언트의 요청처리 
	 * 제어흐름->컨트롤러
	 * 핵심로직 살행-> 서비스
	 */
	/*비즈니스 로직(의사결정 코드) =>무슨말이냐 데이터 가공이나, 중복체크, 연산처리, 암호화등 여기서 다 처리 하겠따.
	 * 트랜잭션 관리
	 * 여러 DAO를 조합
	 * 예외 처리 및 변환
	 * 보안 및 검가
	 */
	/*
	 * Service :비즈니스 로직  의사결정 코드를 작성하는 부분
	 * controller =>service의 메소드를 호출
	 * service에서 connention 을  생성해서 dao로 전달
	 * 만약 sql문을 수행해야하는데 필요한 값이 있다면 controller로부터 전달받아서
	 * connection과 같이 넘겨줄것
	 * dao에서 db작업이 끝나면 servic딴에서 결과에 따른 트랜잭션 처리도 진행
	 * service를 추가함으로 dao는  순수하게 sql문을 처리하는 부분만 남겨둘것
	 *controller와 dao사이의 템플릿이 생긴거임   
	 */
	private Connection conn = null;
	public MemberService() {
		
	}
	
	public int save(Member member) {
		
		JDBCTemplate.RegistDriver();
		//connection 객체생성
		Connection conn = JDBCTemplate.getConnection();
		//controller가 넘겨준 사용자가 입력한 값이 필드에 담겨있는 Member참조변수를 함꼐넘겨줌
		int result = new MemberDao().save(conn,member);
		//트랜잭션 처리
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public List<Member> findAll(){
		JDBCTemplate.getConnection();
		Connection conn = JDBCTemplate.getConnection();
		//임포트 하고 import아페 static  마지막에 .*; 이러면 클래스명 안작성하고 메소드만 작성해서 사용가능
		//dao호출해서 반환하기
		//service에서 받아온 connection 넘겨주기 + 만약에 Controller가 넘겨준 값이 있다면 같이 넘겨줌
		List<Member> members = new MemberDao().findAll(conn);
		close(conn);
		return members;
	}
	public Member findById(String userId) {
		Member member =new MemberDao().findById(conn,userId);
		close(conn);
		return member;
	}
	public List<Member> findByKeyword (String keyword){
		
		List<Member> members = new MemberDao().findByKeyword(conn,keyword);
		close(conn);
		return members;
	}
	
	
	
	//의사결정 코 드 작성
	public int update(PasswordDTO pd) {
		//회원의 비밀번호를 수정해야 한다 == Member테이블에서 한 행 update
		//비밀번호르 수정을 하는데 실패할 수도 있음
		//값이 유효한지 체크를 해줘야 함
		//UPDATE MEMBER SET USERPWD = ? WHERE USERID = ? AND USERPWD =?
		if(pd.getNewPassword().length() > 20) {
			throw new RuntimeException("긴 비밀번호 입니다.");
			//throw : 개발자가 직접 예외를 발생시키는것
			}
		
			Member member = new MemberDao().findById(conn, pd.getUserId());
			if(member ==null) {
				//throw new RuntimeException ("존재하지 않는 아이디 입니다.");
				return 0;
			}
			int result =new MemberDao().update(conn,pd);
			if(result >0) {
				commit(conn);
			}
			close(conn);
		
			
			return result;
		
		}
		
		
		
		
		
		
		
		
	
	public int delete(Member member) {
		
		//회원의 정보를 삭제해야 == Member테이블에서 한 행 DELETE
		
		//커넥션 만들어야지
		//매개변수로 받은거하고 커넥션하고 DAO로넘겨야지
		int result = new MemberDao().delete(conn,member);
		//dml이니까 다녀오면 트핸잭션처리
		if(result>0) {
			commit(conn);
		}
		//트랜젝션 끝나면 커넥션할 일 없으니까 반납해야지
		close(conn);
		//결과 반환해줘야지
		return result;
		
		
		
		
	}

}
