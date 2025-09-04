package model.vo;

import java.util.Objects;

public class Employee {
	private String userNo;
	private int salary;
	private String userName;
	private String userPart;
	private String userLevel;
	public Employee(String userNo, int salary, String userName, String userPart, String userLevel) {
		super();
		this.userNo = userNo;
		this.salary = salary;
		this.userName = userName;
		this.userPart = userPart;
		this.userLevel = userLevel;
	}
	
	public Employee(String userName, String userPart) {
		super();
		this.userName = userName;
		this.userPart = userPart;
	}

	public Employee() {
		super();
	}
	public String getUserNo() {
		return userNo;
	}
	public int getSalary() {
		return salary;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPart() {
		return userPart;
	}
	public String getUserLevel() {
		return userLevel;
	}
	@Override
	public int hashCode() {
		return Objects.hash(salary, userLevel, userName, userNo, userPart);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return salary == other.salary && Objects.equals(userLevel, other.userLevel)
				&& Objects.equals(userName, other.userName) && userNo == other.userNo
				&& Objects.equals(userPart, other.userPart);
	}
	@Override
	public String toString() {
		return "employee [userNo=" + userNo + ", salary=" + salary + ", userName=" + userName + ", userPart=" + userPart
				+ ", userLevel=" + userLevel + "]";
	}
	
}
