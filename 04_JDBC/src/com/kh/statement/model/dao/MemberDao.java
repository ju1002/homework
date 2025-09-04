package com.kh.statement.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;
//SQL문을 실행하고 DB로부터 0결과 받는 클래스 인데 하는일이 너무 많음 그리고 중복이 너무 많음 
//값의 중복은 변수 코드의 중복은 메소드로 처리하는데
//반복적인 구문을 class에 담기

public class MemberDao {
	private Properties prop = new Properties(); 
	public MemberDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/member-mapper.xml"));
		} catch (InvalidPropertiesFormatException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	//메소드 호출할 때망다
	//xml파일로부터 Peoperties객체로 입력받는 코드를 써야함 중복이다
	//new MEmber

	/*
	 * DAO(DATA ACCESS OBJECT) 지금 시점 DAO에서는 DATABASE 관련된 직럽를 전문적으로 다막아응 객케 DAO안에 있는
	 * 모든ㄴ 메소드들을 데이터베이스 관현된 작업으로 구성 SQL SELECT, INERTLT.UPDATE, DELETE
	 * 
	 * 
	 */
	/*
	 * JDBC용 객체 Conninon :db와의 연결정보를 담는 객체 statemnt : connentiom resultSet :
	 * preparedSta
	 * 
	 * 
	 * 
	 */
	/*
	 * 필요한 변수들 세팅: jdbc driver emfe connectuon t 생성 preparedStaetement 샛테 생 현재 미왼성된
	 * sql문을 와 select :executeQuery()
	 * 
	 * 
	 * 
	 *
	 */
	public int save(Connection conn , Member member) {
		//connectiond 은 매개변수로 받았기 떄문에 생성 안함
		//pstmt 가 sql실행 결과 잖아 근데 일단 내가 null로 초기화 해놔서 null이잖아 그러면 값이 있게 만들어줘야 하는데 결과에 sql을 넣어서 pstmt에 담아야 null이 안되지
		PreparedStatement pstmt = null;
		int result = 0;
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("resources/member-mapper.xml"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String sql = prop.getProperty("save");
				
		
		try {
			//PreparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getEmamil());
			
			//실행 ,결과 (int) 받기
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
				//connection은 서비스에서 할꺼기 때문에 pstmt만 반납
				
			
		}
	//결과반환 이 메솓드를 호출한 서비스소 반환함
		return result; 
		
		
		
	}
	
	public List<Member> findAll(Connection conn){
		//필요한 변수
		//결괴 담아줄 list있어야 함
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList();
		String sql = prop.getProperty("findAll");

		//개행 불가능 해서 properties에 담기에는 좀 그럼
		try {
		pstmt = conn.prepareStatement(sql);
		//실행하고 결과(resultset) 받기   resultset은 null일 수가 없다.
		rset = pstmt.executeQuery();
		//조회결과 판단 => rset.next() 결과를 모르니까 while
		// 컬럼값을 객체 필드에 매핑
		while(rset.next()) {
			Member member = new Member(rset.getInt("USERNO")
										,rset.getString("USERID")
										,rset.getString("USERPWD")
										, rset.getString("USERNAME")
										, rset.getString("EMAIL")
										, rset.getDate("ENROLLDATE"));
			members.add(member); //저 값을 담으려면 공간이 있어야 하는데 
			
		}
		
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return members;}
		//서비스로 돌아감
		
		public Member findById(Connection conn,String userId) {
			Member member =null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
	
			String sql = prop.getProperty("findById");
					       
	
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					member = new Member(rset.getInt("USERNO")
										,rset.getString("USERID")
										,rset.getString("USERPWD")
										,rset.getString("USERNAME")
										,rset.getString("EMAIL")
										,rset.getDate("ENROLLDATE"));
				}
				
				
				
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally{
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			return member;
		
		
		
	}
		public List<Member> findByKeyword(Connection conn, String keyword){
			List<Member> members = new ArrayList();
			PreparedStatement pstmt =null;
			ResultSet rset  = null;
			
			String sql = prop.getProperty("findByKeyword");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					members.add(new Member(rset.getInt("USERNO")
										   , rset.getString("USERID")
										   , rset.getString("USERPWD")
										   , rset.getString("USERNAME")
										   , rset.getString("EAMIL")
										   , rset.getDate("ENROLLDATE")));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
				
			}
			return members;
		}
	public int update(Connection conn, PasswordDTO pd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("update");
				      
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pd.getNewPassword());
			pstmt.setString(2,pd.getUserId());
			pstmt.setString(3,pd.getUserPwd());
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		
		
		
		return result;
		
		
	}
	public int delete(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql =  prop.getProperty("delete");
							        
				;
		//1~2번 앞에서 함
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2,member.getUserPwd());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		
		
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}