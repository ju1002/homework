package Template;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EmployeeTemplate {
	//jdbc과정 중 반복적을 쓰이는 구문들을 각각의 메소드로 정의 해둘 클래스
	//커넥션이나, 드라이버 등록이나, 예외처리 구문 이나 트랜잭션 처리구문 자원반납해주는 구문
	public static void RegistDriver()
	{//드라이버 등록하는 메소드
		try {
			 Class.forName("oracle.jdbt.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			/*conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE" //커넥션 객체 생성
											    , "BJY11"
											    , "BJY111234");
											    */
			Properties prop =new Properties();
			prop.load(new FileInputStream("resource/driver.properties"));
			
			conn = DriverManager.getConnection(prop.getProperty("URL"),prop.getProperty("USERNAME"),prop.getProperty("PASSWORD"));
			conn.setAutoCommit(false); //자동 말고 직접  커밋할꺼임
			return conn; //연결 성공 시 conn을 반환
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null; //연결 못할 시 null값 반환
		//커넥션 객체를 생성하는 메소드
		//커넥션 타입으로  받는다/
	}
	public static void commit(Connection conn) {
		//연결을 먼저 하고 트랜잭션 처리 해야하니까 매개변수로 conn을 넣어줌
		try {
		if(conn != null) {
					conn.commit();
		}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			
		
	}
	//자원반납해주는게 conn도있고 statement sql을 실행하는 구문 도 있고 결과르 받아주는 resultset도 있다
	public static void close(Connection conn)
	{	
		try {
		if(conn != null) {
				conn.close();
		}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}
	public static void close(Statement stmt) {
		//다형성 적용하여
		//PreparedStatement도 해야하는데 Statement가 부모객체여서 Statement를 해도 상관없음
		try {
		if(stmt !=null) {
				stmt.close();
		}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}
	public static void close(ResultSet rset) {
		try {
		if(rset != null ) {
				rset.close();
		}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
}
