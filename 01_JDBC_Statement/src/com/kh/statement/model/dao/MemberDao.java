package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.vo.Member;

public class MemberDao {

	/*
	 * JDBC용 인터페이스 -Connection : 데이터베이스와의 세션(연결)을 나타내는 인터페이스 -> 데이터베이스와의 통신 채널 연결 ,
	 * 트랜잭션 관린 , SQL문을 실행을 위한 Statement 객체 생성 주의사항 : 메모리 누수를 방지하기 위해 항상 자원반납인
	 * close() 트랜잭션을 수동으로 관리한다면 DML수행 이후에는 반드시 commit/rollback -Statement : SQL문을
	 * 싫행하고 결과를 받아오기 위한 인터페이스 1. Statement : 정적SQL문 실행(완성된 SQL문 ) 실행 2.
	 * preparedStatment : 파라미터화된 SQL문(미완성 된) 실행 3.CallableStatement : 저장 프로시저 호출
	 * -ResultSet : SELECT문 실행결과를 담는 테이블형태의 데이터 셋 커서(cursor) 라는 개념을 이용해서 데이터에 접근 다양한
	 * 데이터타입 변환 메소드를 제공
	 * 
	 * DAO에서 일어나는 JDBC처리순서 1. JDBC 드라이버 등록 : 실제로 구현하는 것은 각 DBMS의 제조사들 에서 실행할 것이기 때문에
	 * 먼저 등록을 함 DBMS에서 제공하는 클래스를 리플렉션을 이용해 등록 2.Connection 객체생성 : 접속하고자 하는 DB정보를
	 * 전달하면서 Connection 객체 반환 3.Statement 객체 생성 : Connection 객체를 이용해서 생성 4. SQL문을
	 * 전달하면서 실행 : Statement 객체를 이용해서 SQL문을 실행 SELECT = executeQuery()호출 DML =
	 * executeUpdate() 호출 5. 결과받가 SELECR => ResultSet(조회된 데이터들이 테이블형태로 담김) 객체로 받기
	 * DML-> int (처리된 행 수) 로 받기 6. SELECT문이면 데이터가 어마어마하게 많이 들어옴 하지만 VIEW다시 보내야 할 값은
	 * 1개이다 때문에 ResultSet에 담겨있는 데이터를 하나하나씩 뽑아서 vo객체로 담기 DML문이면 트랜잭션을 수동으로 처리한다면
	 * commit/rollback 7. 자원반납 close() 생성의 역순으로 처리한다. 8. 결과값 반환 SELECT = > 6에서 가공한
	 * 형태를 반환 DML-> int(처리된 행 수 ) 하는 방법이나 도구는 바뀔 수 있지만 절차는 바뀌지 않음
	 * 
	 */

	public int save(Member member) {
		// 필요한 변수를 먼저 선언 및 null값으로 초기화
		Connection conn = null; // 접속할 DB서버와의 연결정보를 덤는 객체
		Statement stmt = null; // SQL문 실행 후 결과를 받기 위한 객체
		int result = 0; // DML수행 후 결과를 받기 위한 변수
		// SQL문 (정적인 형태)
		/*
		 * INSERT INTO MEMBER VALUES ( SEQ_USERNO.NEXTVAL , '사용자가 입력한 아이디 값' , '사용자가 입력한
		 * 비밀번호 값' , '사용자가 입력한 이름 값' , '사용자가 입력한 이메일 값' , SYSDATE )
		 */
		String sql = "INSERT " + "INTO " + "MEMBER " + "VALUES " + "(" + "SEQ_USERNO.NEXTVAL" + ", '"
				+ member.getUserId() + "'" + ", '" + member.getUserPwd() + "'" + ", '" + member.getUserName() + "'"
				+ ", '" + member.getEmamil() + "'" + ", SYSDATE " + ")";

		System.out.println(sql);
		// SQL문을 문법적으로 올바르게 작성하지 못했다면
		// SQLSyntaxErrorException

		try {
			// 1)JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 1. 오타가 날 경우 2. 프로젝트에 라이브러리를 추가 하지 않아서 진짜로 클래스를 못찾는 경우
			// ClassNotFoundException발생
			// 2.Connection 객체 생성 (DB와 연경-> URL, 사용자이름 , 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "BJY11", "BJY111234");

			/*
			 * new Connection () 이런 느낌으로 객체를 생성 한다. 계속 쓰려면 변수에 커넥션의 주소값을 담아야함 근데 무슨 타입? 커넥션
			 * 타입 1. URL을 잘목 적음 2.사용자 계정명을 잘목 적을 수 있음 3. 비밀번호를 잘목 적을 수 있음 4. 서버가 안켜져 있을 수 있음
			 * 5. 접속 권한을 부여받지 못했을 수 있음 SQLException이 발생
			 */
			// AutoCommit끄기
			conn.setAutoCommit(false);

			// Statement 객체 생성
			stmt = conn.createStatement(); // new Statement(conn); 이런 느킴임 힙영역에 올리기만 하면 날라가니까 변수에 담아줘야 함 그럴려면
											// statement타입에 담아놔야지
			// 4,5 DB에 완성된 SQL문을 전달하면서 실행도 하고 결과도 받고
			// insert문이니까 executeUpdate()호출 한다.
			result = stmt.executeUpdate(sql);
			// INSERT시 값에 문제가 있을 수 있음
			// 자료형이 맞지 않음
			// 제약조건에 위배
			// 데이터이 크기가 컬럼의 크가보다 큼
			// SQLException 발생
			// 6 트랜잭션 처리 성공했을 때 commit을 처리할거임
			if (result > 0) {
				conn.commit();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7. 자원반납 생성된 역순으로 close() 호출
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		// 8결과반환
		return result;

	}

	public void findAll() {
		//DB한테 해달라해야함 그럼 JDBC객체를 생성
		Connection conn =null; //연결된  DB의 정보를 담는 객체
		Statement stmt = null; // SQL문을 실행하고 결과를 받기 위한 객체
		ResultSet rset = null; // SELECR문을 수행하고 조회 결괴값들이 담길 객체
		
		String sql = """
				         SELECT
				                USERNO
				               ,USERID
				               ,USERPWD
				               ,USERNAME
				               ,EMAIL
				               ,ENROLLDATE
				          FROM
				                MEMBER
				         ORDER
				            BY
				               ENROLLDATE DESC
				""";
		
		try {
			//드라이버 등록
		Class.forName("oracle,jdbc.driver.OracleDriver");
		//오라클 db에 연결 그럴려면 connection 객체 생성
		conn = DriverManager.getConnection("jdbc:oracle:this:@115.90.212.20:10000:XE"
				                  ,"BJW11"
				                  ,"BJW111234");
				//get 반환 받는거니까 받을 공간 변수를 존재 
		//sql문 실행 statement객체 생성 해서 실행
		 stmt = conn.createStatement();
		//만든것을 변수에 담아놔야 함
		 // sql문(select) 실행  실행하면 결과가 반환 되는데 (resultset)타입으로 나옴 
		 rset = stmt.executeQuery(sql);
		 //6. Mapping한다 함 Mapping이란 ? 서로 다른 형태의  데이터 모델 간의 연결을 정의 하는 과정이다.
		 // 자바는 데이터를 객체 형태로 다루고
		 // 관계형 데이터베이스는 테이블 형태로 다룬다.
		 // 
		 
		 //현재 조회 결과는 ResultSet에 담겨있음
		 // 한 행씩 뽑아서 Vo객체의 필드에 담기
		//reset.next()
		 //커서를 한 줄 아래로 옯겨주고 존재한다 true/ 존재하지 않는다 false
		 /*while(rset.next()) {
			 //현재 rset의 커서가 가리키고 있는 행의 데이터를 하나하나씩 뽑아서 MemberVo의 필드의 대입
			 Memeber member = new Member();
			 //ResultSet객체로부터 어떤 컬럼의 값을 뽑을 건지 메소드를 호출하면서 컬럼명을 명시
			 /*rset.getInt(컬럼명) :  정수형 값을 int형으로 매핑할 때
			  * rset.getString(컬럼명) : 문자열형 값을 String 형으로 매핑할 때
			  * rset.getDate(컬럼명) : 날짜 형 값을 java.sql.data형으로 매핑할 때
			  */
			 //메소드 호출 시 반환값이 있다면 값이 라고 생각해라
			 //int userNo = rset.getInt("USERNO");
			/* member.setUsetNO(rset.getInt("USERNO"));
			 member.setuserId(rset.getString("USERID"));
			member.setUserNamerse (rset.getString("USERPWD"));
			member.setEamil(rset.getString("Email"));
			member.setEnrollDate(rset.getDate("ENROLLDATE"));
		*/		
			 //Resullt에서 조회된 결과값
//		한 행에 모든 컬럼의 데이터 값을
//		각각의 필드에 담아 Member 객체로 옯겨담으면 끝
//		조회된 Member들을 싹 다 돌려보내야 함
//		배열특)크기가 정해져햐 됨
//		조회 결과가 몇행일지 특정지을 수 없음
//	   여러 정보를 담아줄 저장소=> Liset

	public Member findById(String userId) {	
			//필여한 변수 선언
			//jdbc관련 인터페이스
			Member member = null;
			//connection, Statement, ResultSet
//			Connection conn =  null;
//			Statement stmt = null;
//			ResultSet rset = null;
			//실행할 SQL문 (완성문)
			/*SELECT
			 *       USERNO
			 *       USERID
			 *     , USERPWD
			 *     , USERNAME
			 *     , EMAIL
			 *     , EMROLLDATE
			 *   FROM
			 *        MEMBER
			 *  WHERE
			 *        USERID = '사용자가 입력한 ID값'
			 */
			
			String sql ="""
					       SELECT
					               USERNO
					             , USERID
					             , USERPWD
					             , USERNAME
					             , EMAMIL
					             , ENROLLDATE
					         FROM
					               MEMBER
					         WHER 
					               USERID = 
					""";
			sql+="'"+userId+"'";
			
			
			try {
				//jdbc driver등록
				Class.forName("oracle.jdbc.driver.OracleDriver");
				try(Connection conn =DriverManager.getConnection("jdbc:oracle:this:@115.90.212.20:100000:XE"
									 , "BJY11"
									 , "BJY111234");
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery(sql)){
					
					//조회결과가 담긴 ResultSet객체에서 조회결과가 존재하다면 VO객체의 필드에 옯겨담기
					//아이디의 유티크제약을 걸어서 결과가 1만 존재하기 때문에 if 사용
					//Id가지고 검색 한 행만 조회 
					if(rset.next()) {
						member = new Member(rset.getInt("USERNO")
											, rset.getString("USERID")
											, rset.getString("USERPWD")
											, rset.getString("USERNAME")
											, rset.getString("EMAMIL")
										   , rset.getDate("ENROLLDATE"));
}
				}catch(SQLException e) {
e.printStackTrace();}
catch(ClassNotFoundException e) {
e.printStackTrace();}
return member;
			
			
			
			
			
			
			
			
			
		
				
			 
			 
		 
		 
	
		
		
		
	
	
	
	
	
	
	
		}

	public List<Member> findByKeyword(String keyword){
			List<Member> members = new ArrayList();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rset = null;
			/*
			 * SELECT
			 *         USERNO
			 *       , USERID
			 *       , USERPWD
			 *       , USERNAME
			 *       , EMAIL
			 *       , EMROLLDATE
			 *   FROM
			 *        MEMBER
			 *   WHERE
			 *         USERNAME LIKE '%사용자가 입력한 값%'
			 *    ORDER
			 *       BY
			 *          ENROLLDATE DESC
			 */
			String sql = "SELECT "
					             + "USERNO"
					            + ", USERID"
					            + ", USERPWD"
					            + ", USERNAME"
					            + ", EMAIL"
					            + ", ENROLLDATE "
					       +"FROM "
					            +"MEMBER "
					     +"WHERE "
					           +"USERNAME LIKE '%" +keyword +"%' "
					     +"ORDER "
					           +"BY "
					              +"ENROLLDATE DESC";
			try {Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE)"
												,"BJY11"
												,"BJY111234");
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			//ResultSet객체에서 각핼에 접근하면서 조회결과가 있다면 컬럼의 값을 뽑아서 vo객체에 필드에 대입한뒤
			//Lise의 요소로 추가함
			while(rset.next()) {
				members.add(new Member(rset.getInt("USERNO")
									  ,rset.getString("USERID")
									  ,rset.getString("USERPWD")
									  ,rset.getString("USERNAME")
									  ,rset.getString("EMAIL")
									  ,rset.getDate("ENROLLDATE")));
				
			}
			
			
			
			
			
				
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rset.close():
					stmt.close();
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
	
			return members;
			
			}

}
