package com.kh.common;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	/*JDBC과정 중 반복적으로 쓰이는 구문들을 각각의 메소드로 정의해둘  클래스
	 * 중복된 코드들을 메소드로 분리하여   재사용함
	 * 자바에서 재사용,공유와 관련된 키워드 static
	 * 이 클래스의 모든 메소드는 static으로 선언
	 * 0~8번 중에 중복으로 뺄만한거 커넥션 , 드라이버,예외처리 구문, 트랜잭션처리도무조건 할 수 있도록 예외처리를 해야험 또한 null일 수도 있기 때문에 if해서 null처리도해야했음 
	 *  
	 * 
	 */
	
	
	public static void RegistDriver() {
		//드라이버 등록 하는 메소드 프로그램 실행 중 한번만 실행
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		//DB의 연결정보를 가지고 있는 Connection객체를 생성해서 반환해주는 메소드
		//커넥션 객체 생성 메소드 커넥션 타입으로 받아줌
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE"
															,"BJY11"
															,"BJY111234");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	return null;//정상적으로 연결을 못했다 그럼 NULL값을 반환하자
	}
	
	
	public static void commit(Connection conn) {
		//트랜잭션 처리 메소드 Connection객체를 이용해서 commit시켜주는 메소드
		
		//커넥션을 이용하는거니까 매개변수로 받아줌
		try {
		if(conn !=null) {
			conn.commit();
			
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
		public static void rollback(Connection conn) {
			//commit과 같은 용도로 rollback
			
			try {
		
			if(conn !=null) {
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
		//JDBC용 객체를  반납해주는 메소드 (각 객체별로)
		//Connection객체를 전달 받아서 반납해주는 메소드
		public static void close(Connection conn) {
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Statement stmt) {
			//정적바인딩 Overroding을 해서 동일한 클래스의 이름을 사용가능
			//Statement객체를 전달 받아서 반납해주는 메소드
			//Statement는 PrepatedStatement의 부모 객체이기 때문이다.
			//다형성 적용을 통해 PreparedStatement객체도 Statement타입으로 받 을 수 있음
			try {
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(ResultSet rset) {
			try{if(rset != null) {
				rset.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	
	
}

		}
