package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERNAME = "BJY11";
	private final String USERPWD = "BJY111234";

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
	public int save(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null; // sql문 실행문 결과받기
		int result = 0;
		String sql = """
				INSERT
				           INTO
				                MEMBER
				           VALUES
				               (
				               SEQ_USERNO.NEXTVAL
				              , ?
				              , ?
				              , ?
				              , ?
				              , SYSDATE
				              )
				  """;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "BJY11", "BJY111234");
			conn.setAutoCommit(false);
			// PreparedStatement 객체 생성 시 SQL문을 미리 전달해야한다.
			pstmt = conn.prepareStatement(sql);
			// id,pwd,name,emamil의 값이 없음
			// 미완성된 sql문일 경우 완성시켜주시
			// 위치홀더에 값 바인딩 pstmt.set(?의 위치 , 실제값);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getEmamil());
			// pstmt.setString (홀더 순번, 값)
			// '값'(양옆에 홀따옴ㅍ표를 감싼 상태로 알아서 bind
			// pstmt.setInt(홀더 순번, 값)
			// 값 알아서 잘들어감 따옴표 안붙임

			result = pstmt.executeUpdate(); // 아까이미 sql을 보냈기 때문에 안보내도 됨
			if (result > 0) {
				conn.commit();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();

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

		return result;
	}

	public List<Member> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 멤버 객체만 담을 수 있는 리스트가 필요함
		List<Member> members = new ArrayList();

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, USERPWD);
			pstmt = conn.prepareStatement("""
					SELECT
					 	   USERNO
					 	 , USERID
					 	 , USERPWD
					 	 , USERNAME
					 	 , EMAIL
					 	 , ENROLLDATE
					 FROM
					       MEMBER
					 ORDER
					    BY
					       ENROLLDATE DESC
					     	""");

			// 이미 완성된 SQL문
			// sql문 실행 결과 받기
			rset = pstmt.executeQuery();
			// mappings
			while (rset.next()) {
				Member member = new Member();
				member.setUserNo(rset.getInt("USERNO"));
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLLDATE"));
				members.add(member);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
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
		return members;
	}

	public Member findById(String userId) {
		Member member = null;

		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement stmt = null;
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
						 WHERE
						       USERID = ?
				""";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, USERPWD);
			// 프리패얼 준비
			stmt = conn.prepareStatement(sql);
			// 값 채우기
			stmt.setString(1, userId);
			// 실행
			rset = stmt.executeQuery();
			if (rset.next()) {
				member = new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("EMAIL"), rset.getDate("ENROLLDATE"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

		return member;
	}

	public List<Member> findByKeyword(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList();
		String sql = """
						 SELECT
						        USERNO
						      , USERID
						      , USERPWD
						      , USERNAME
						      , EMAIL
						      ,ENROLLDATE
						   FROM
						        MEMBER
						   WHERE
						         USERNAME LIKE '%'||?||'%'
						    ORDER
						        BY
						          ENROLLDATE DESC

				""";
		try {
			Class.forName(DRIVER);

			conn = DriverManager.getConnection(URL, USERNAME, USERPWD);

			pstmt = conn.prepareStatement(sql);
			// 애는 만들면서 sql문 싸악
			// pstmt.setString(1."%"+keyword + "%");
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				members.add(new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("EMAIL"), rset.getDate("ENROLLDATE")));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
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

		return members;

	}

	public int update(PasswordDTO pd) {
		//update에서 할일 : 전달벋은  값을 가지고 값이존재하는 행을 찾아서 정보를 갱신해줌
		//얘가 맡은 일: SQL문 실행하고 결과받아오기
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = """
							UPDATE
							       MEMBER
							   SET
							       USERPWD = ?
							  WHERE 
							       USERID = ?
							    AND
							       USERPWD = ?
				""";
		try {
		Class.forName(DRIVER);
		
		conn = DriverManager.getConnection(URL, USERNAME , USERPWD);
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,pd.getNewPassword());
		pstmt.setString(2, pd.getUserId());
		pstmt.setString(3, pd.getUserPwd());
		
		
		result = pstmt.executeUpdate();
		if(result> 0) {
			conn.commit();
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {if(pstmt != null) 
			pstmt.close();}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try{
if(conn != null) {
	conn.close();
	
}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		return result;
	}
	public int delete(Member member) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = """
				         DELETE
				           FROM 
				                MEMBER
				           WHERE
				                USERID = ?
				             AND 
				                USERPWD =?
				""";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME,USERPWD);
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1,member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			
			
		} catch(ClassNotFoundException e) {
			
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
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*PreparedStatement가 Statement보다 좋음
	 * 1. 구문 분석 및  컴파일 최적화
	 * 
	 * stmt.executeUpdate(sql)
	 * pstmt.executeUpdate();
	 * Statement는 매번 SQL문을 파싱하고 실행 하지만 
	 * PreparedStatement는 SQL쿼리를 최로 2회만 피싱하고 실행계획을 캐싱(메모리에 올림)
	 * 재사용적인 측면에서 훨씬 효율적임
	 * 2. DB서버에 대한 트래픽 감소
	 * 쿼리 자체는 한 번만 전송하고 이후에는 바인딩할 값만 전송하기 때문에 효율적
	 * 동일 크ㅓ리를 반복 실행 할 때, 높은 트래픽이 몰리는 애플리케이션일 때 더욱더 효율적이다.
	 * DB작업 => 계획 세울 때 리소스를 많이 잡아먹음
	 * 
	 * 3. SQLinjection 방지
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
