package com.kh.statement.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberService {
	
	private MemberDao md = new MemberDao();
	
	public int save(Member member) {
		/*
		 * Conneciont conn = JDBCTemplate.getConnecion();
		 * 
		 * int result = new MemberDao().save(conn,member);
		 * 
		 * if(resul>0){
		 *  	JDVCTemplate.close(conn)
		 *  }
		 *  return result;
		 */
		
		SqlSession session= Template.getSqlSession();
		
		int result = 0;
		try {
			result = md.save(session,member);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(result>0){
			session.commit();
		}
		session.close();
		
		return result;
	}
	
	public List<Member> findAll(){
		
		SqlSession session = Template.getSqlSession();
		
		List<Member> members = md.findAll(session);
		
		session.close();
		
		return members;
	}
	public Member findById(String userId) {
		
		SqlSession session = Template.getSqlSession();
		//session은 connection역할을 함
		Member member = md.findById(session, userId);
		
		//자원반납 처리 사용이 끝난 sqlsession처리
		session.close();
		
		return member;
		
	}
	
	public List<Member> findByKeyword(String keyword){ 
		
		SqlSession session = Template.getSqlSession();
		
		List<Member>  members= md.findByKeyword(session, keyword);
		session .close();
		return members;
	}
	
	
	public int update(PasswordDTO pd) {
		SqlSession session = Template.getSqlSession();
		int result = md.update(session,pd);
		if(result>0) {
			session.commit();
		}
		session.close();
		return result;
	}
	public int delete(Member member) {
		SqlSession session = Template.getSqlSession();
		
		int result = md.delete(session, member);
		if(result>0) {
			session.commit();
		}
		session.close();
		return result;
	}
	
	
	

}
