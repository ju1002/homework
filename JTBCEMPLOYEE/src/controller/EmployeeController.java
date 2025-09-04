package controller;

import java.sql.Connection;
import java.util.List;

import model.service.EmployeeService;
import model.vo.Employee;

public class EmployeeController {
	private Connection conn = null;
	public List<Employee> findAll() {
		List<Employee> employees =new EmployeeService().findAll();
		return employees;
		
		
	}
	public void findUser(Connection conn ,String userName) {
		new EmployeeService().findUser(conn,userName);
	}

}
