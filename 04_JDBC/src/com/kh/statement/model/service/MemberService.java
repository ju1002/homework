package com.kh.statement.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.function.Function;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberService {
//여기도 중복이 있어 
/*전달하려는 값과 , 반환해주는것만 다르고 다 중복이야
 * int result = new MemberDao().save(conn, member);

		if (result > 0) {
			commit(conn);
		}
		close(conn);
 */
	
	
	
	
	
	private Connection conn = null;

	public MemberService() {
		this.conn = getConnection();

	}
	

	public int save(Member member) {

		int result = new MemberDao().save(conn, member);

		if (result > 0) {
			commit(conn);
		}
		close(conn);

		return result;
	}
	
	private	<T> T executeQuery(Function<Connection, T>daoFunction) {
		Connection conn = null;
		T result = null;
		conn = getConnection();
		result =daoFunction.apply(conn);
		close(conn);
		
		return result;
	
	}
	//이들의 공통점은 executeQuery를 돌려주는 애들이다
	
	
	
	
	

	public List<Member> findAll() {
		return executeQuery(new MemberDao()::findAll);
	}

	public Member findById(String userId) {
		return executeQuery(conn ->new MemberDao().findById(conn,userId));
	}

	public List<Member> findByKeyword(String keyword) {
		return executeQuery(conn -> new MemberDao().findByKeyword(conn,keyword));


	}

	public int update(PasswordDTO pd) {

		if (pd.getNewPassword().length() > 20) {
			throw new RuntimeException("긴 비밀번호 입니다.");

		}

		Member member = new MemberDao().findById(conn, pd.getUserId());
		if (member == null) {

			return 0;
		}
		int result = new MemberDao().update(conn, pd);
		if (result > 0) {
			commit(conn);
		}
		close(conn);

		return result;

	}

	public int delete(Member member) {

		int result = new MemberDao().delete(conn, member);

		if (result > 0) {
			commit(conn);
		}

		close(conn);

		return result;

	}

}
