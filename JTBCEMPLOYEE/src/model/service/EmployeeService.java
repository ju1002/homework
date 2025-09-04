package model.service;

import java.sql.Connection;
import java.util.List;

import Template.EmployeeTemplate;
import model.dao.EmployeeDAO;
import model.vo.Employee;

public class EmployeeService {
	//트랜잭션관리, 예외처리 및 반환 conection객체 생성 해서  보내주기
	Connection conn = null;
	public List<Employee> findAll() {
		Connection conn = EmployeeTemplate.getConnention(); //커넥션 객체 생성
		List<Employee> employees =new EmployeeDAO().findAll(conn);//커넥션을 보내줘야 DAO 에서도 연결을 함
		
		return employees;
	
	
	}
	public void findUser(Connection conn ,String userName) {
		new EmployeeDAO().findUser(conn,userName);
		
	}

}
