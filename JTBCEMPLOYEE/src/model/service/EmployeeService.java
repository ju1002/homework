package model.service;

import static Template.EmployeeTemplate.close;
import static Template.EmployeeTemplate.commit;
import static Template.EmployeeTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import model.dao.EmployeeDAO;
import model.vo.Employee;

public class EmployeeService {
	//트랜잭션관리, 예외처리 및 반환 conection객체 생성 해서  보내주기
	Connection conn = null;
	public List<Employee> findAll() {
		Connection conn = getConnection(); //커넥션 객체 생성
		List<Employee> employees =new EmployeeDAO().findAll(conn);//커넥션을 보내줘야 DAO 에서도 연결을 함
		
		return employees;
	
	
	}
	public Employee findUser(Connection conn ,String userName) {
		Employee employee =new EmployeeDAO().findUser(conn,userName);
		return employee;
		
	}
	public Employee findLevel(Connection conn,String level) {
		conn= getConnection();
		Employee employee = new EmployeeDAO().findLevel(conn,level);
		
		return employee;
	}
	public Employee selectUser(Connection conn , int userNo) {
		conn = getConnection();
		Employee employee = new EmployeeDAO().selectUser(conn,userNo);
		return employee;
	}

}
