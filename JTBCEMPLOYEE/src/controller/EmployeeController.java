package controller;

import java.sql.Connection;
import java.util.List;

import static Template.EmployeeTemplate.*;
import model.service.EmployeeService;
import model.vo.Employee;

public class EmployeeController {
	private Connection conn = null;
	public List<Employee> findAll() {
		List<Employee> employees =new EmployeeService().findAll();
		conn = getConnection();
		return employees;
		
		
	}
	public Employee findUser(String userName) {
		Employee employee =new EmployeeService().findUser(conn,userName);
		return employee;
	}
	public Employee findLevel(String level) {
		Employee employee =new EmployeeService().findLevel(conn,level);
		conn = getConnection();
		return employee;	
	}
	public Employee selectUser(int userNo) {
		Employee employee = new EmployeeService().selectUser(conn,userNo);
		conn = getConnection();
		return employee;
		}
	}


