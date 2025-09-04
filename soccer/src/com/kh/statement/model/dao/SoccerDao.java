package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.vo.Soccer;

public class SoccerDao {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERID = 	"BJY11";
	private final String USERPWD =	"BJY111234";
	public int plus(Soccer soccer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = """
				 INSERT
						       INTO
						             SOCCER
						       VALUES
						         (
						          ?
						         ,?
						         ,?
						         ,?
						         ,?
						         ,SYSDATE 
								 )
				""";
		//완벽하지 않은 sql문임
		//드라이버 먼저 등록
		//오류 검출
		try {
			Class.forName(DRIVER);
			
		//conn연결
			conn =DriverManager.getConnection(URL,USERID,USERPWD);
			conn.setAutoCommit(false);
		//PreparedStatement 객체를 생성해야함 생성과 동시에 sql문을 미리 전달한다
			pstmt = conn.prepareStatement(sql);
		//이제 비어있는 곳에 값을 넣어줌
		//넣어주는 방법은 일단 값들이 있는 주소로 가고 내가 값을 세팅 할꺼니까 set사용
			pstmt.setInt(1,soccer.getUserNo());
			pstmt.setString(2,soccer.getUserName());
			pstmt.setString(3, soccer.getUserPosition());
			pstmt.setInt(4, soccer.getSalary());
			pstmt.setString(5, soccer.getUserTeam());
			result = pstmt.executeUpdate();// 결과를 받아줌
			if(result>0) { //결과가 나올 수도 있고 안나올 수도 있기 때문에 조건문
				conn.commit();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();				}
			}catch(SQLException e) {
				
			}
			
		}
		return result;
		
	}
	public List<Soccer> found()
	{
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement pstm =null;
		List<Soccer> soccers =new ArrayList();
		
		try {
			Class.forName(DRIVER);
			
			conn = DriverManager.getConnection(URL,USERID,USERPWD);
			pstm = conn.prepareStatement(
					"""
					SELECT
						    USERNO
						  , USERNAME
						  , USERPOSITION
						  , SALARY
						  , USERTEAM
						  , ENROLLDATE
					 FROM
					      SOCCER
					""");
					
			//이미 완성된 SQL문임
			rset =pstm.executeQuery();
			while(rset.next()) {
				Soccer soccer = new Soccer();
				soccer.setUserNo(rset.getInt("USERNO"));
				soccer.setUserName(rset.getString("USERNAME"));
				soccer.setUserPosition(rset.getString("USERPOSITION"));
				soccer.setSalary(rset.getInt("SALARY"));
				soccer.setUserTeam(rset.getString("USERTEAM"));
				soccer.setEnrollDate(rset.getDate("ENROLLDATE"));
				soccers.add(soccer);
			}
			
					
					
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			if(rset != null) {
				rset.close();
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstm !=null) {
					pstm.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
			
		}
		return soccers;
		
		
		
		
		
		
	}
	public Soccer mouney(int salary) {
		ResultSet rset =null;
		Soccer soccer = null;
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql = """
							SELECT
							       USERNO
							     , USERNAME
							     , USERPOSITION
							     , SALARY
							     , USERTEAM
							     , ENROLLDATE
							  FROM 
							       SOCCER
							  WHERE
							        SALARY = ?
				""";
		
		//nullpointException 발생
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USERID,USERPWD);
			conn.prepareStatement(sql);
			pstmt.setInt(1, salary);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				soccer = new Soccer(rset.getInt("USERNO")
									,rset.getString("USERNAME")
									,rset.getString("USERPOSITION")
									,rset.getInt("SALARY")
									,rset.getString("USERTEAM")
									,rset.getDate("ENROLLDATE"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace(); 
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
			if(pstmt != null) {
				pstmt.close();
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return soccer;
		
	}
}
