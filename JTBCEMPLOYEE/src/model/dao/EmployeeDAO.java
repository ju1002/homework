package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Template.EmployeeTemplate.*;
import model.vo.Employee;

//sql문을 실행하고 db로부터 결과를 받아줌 
public class EmployeeDAO {
		public List<Employee> findAll(Connection conn) {
			//조회하니까 결과가 resultSet이와야 하고 
			//실행할 PreparedStatement도 와야함
			//일단 내가 조회할게 전체 조회니까 그걸 한번에 받기 위해서는 list로 받아야겠지
			//0필요한 변수 선언
			ResultSet rset =null;
			PreparedStatement pstmt = null;
			List<Employee> employees = new ArrayList();
			String sql = """
								SELECT
								       E.JOB_CODE
								     , SALARY
								     , EMP_NAME
								     , DEPT_TITLE
								     , JOB_NAME
								 FROM
								      EMPLOYEE E
								 JOIN
								       JOB J
								   ON 
								     (J.JOB_CODE = E.JOB_CODE)
								 JOIN
								       DEPARTMENT D
								   ON 
								      (D.DEPT_ID = E.DEPT_CODE)
								     
					""";
			
			//1.드라이버 등록template에서 했고
			//2. 커넥션 객체 생성 했고
			//3. preparedStatement 통해 sql뮨실행
			try {
			pstmt = conn.prepareStatement(sql);
//	  		4.결과값을 받아줌		
			rset = pstmt.executeQuery();
			while(rset.next()) {
			//매핑
			Employee employee =new Employee(rset.getString("JOB_CODE")
											,rset.getInt("SALARY")
											,rset.getString("EMP_NAME")
											,rset.getString("DEPT_TITLE")
											,rset.getString("JOB_NAME"));
			employees.add(employee); //매핑한걸 넣어야지 배열에
			//6.트랜잭션
			}
		  commit(conn);
			
											
		
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				//7.자원반납
				close(rset);
				close(pstmt);
			}
			//8.결과 반환
			return employees;
			
		}
		public void findUser(Connection conn , String userName) {
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			
			String sql =
					"""
					SELECT
					 
					
					"""
			
			
			
			
			
			
			
			
			
			
		}
		
	}

	


