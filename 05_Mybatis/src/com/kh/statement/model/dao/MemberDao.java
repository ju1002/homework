package com.kh.statement.model.dao;
//dao : sql을 실행하고 결과를 받아오는 역할을 함
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;
//SQL문을 실행하고 DB로부터 0결과 받는 클래스 인데 하는일이 너무 많음 그리고 중복이 너무 많음 
//값의 중복은 변수 코드의 중복은 메소드로 처리하는데
//반복적인 구문을 class에 담기

public class MemberDao {
	public static int save(SqlSession session, Member member) {
		/*
		 * int result = 0;
		 * ProparedStatement stmt = null;
		 * String sql = prop.getProperty("save")
		 * try{
		 * pstmt = conn.prepareStatment(sql);
		 * pstmt.setString(1, member.getUserId());
		 * pstmt.setString (2, member.getUserPwd());
		 * 
		 * result = pstmt.executeUpdate();
		 * }catch(IOException e){e.printStackTrace}
		 * finallu{
		 * JDBCTemplate.close(pstmt);
		 * }
		 * return result;
		 * Sqlsession이 제공하는 메소드를 통해 SQL문을 찾아서실행하고 결과도 받아볼 수 있음
		 */
		
		//SqlSession은 conneciont역할도 하고 statement역할도 함
		//int result = session.insert("memberMapper.save", member);
//insert
//(어떤 sql문을 실행할거냐 대신 구분해주기 위해 앞에 구분할 namespace를 작성 , 앞에서 넘어온 안에 들어갈 값)
		return   session.insert("memberMapper.save",member);
		//dao오기전에 넘어올 값이 여러개면 하나ㅡ이 담 아서 보내줌; 
		
		
		
	}
	public List<Member> findAll(SqlSession session){
		//List<Member> member = session.selectList("memberMapper.findAll");
		//return member;
		return session.selectList("memberMapper.findAll");
		//결과가 몇행이 돌아올까를 생각해야함
		//지금 같은 경우 모르니까 list
	}
	
	
	public Member findById(SqlSession session , String userId) {
		//Member member = session.selectOne("member-mapper.findById",userId);
		//조화결과가 존재하지 않는다면 null값 반환
		return session.selectOne("memberMapper.findById",userId);
		//내가 실행하고자 하는 sql문이 select니까select에 있는 것을 불러야 함
	}
	
	
	public List<Member> findByKeyword(SqlSession session , String keyword){
		//List<Member> member =  session.selectList("memberMapper.fjindByKeyword", keyword);
		return session.selectList("memberMapper.findByKeyword", keyword);
	}
	
	public int update(SqlSession session,PasswordDTO pd) {
		
		
		session.update("memberMapper.update", pd);
		return 0;
		
	}
	public int delete(SqlSession session , Member member) {
		return session.delete("memberMapper.delete", member);
	}
	
}