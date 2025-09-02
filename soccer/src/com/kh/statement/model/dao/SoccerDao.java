package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.statement.model.vo.Soccer;

public class SoccerDao {
	private final String DRIVER = "oracle.jdbc.driber.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERID = 	"BJY11";
	private final String USERPWD =	"BJY111234";
	public void plus(Soccer soccer) {
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
		//PreparedStatement 객체를 생성해야함 생성과 동시에 sql문을 미리 전달한다
			pstmt = conn.prepareStatement(sql);
		//이제 비어있는 곳에 값을 넣어줌
		//넣어주는 방법은 일단 값들이 있는 주소로 가고 내가 값을 세팅 할꺼니까 set사용
			pstmt.setInt(1,soccer.getUserNo());
			pstmt.setString(2,soccer);
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
