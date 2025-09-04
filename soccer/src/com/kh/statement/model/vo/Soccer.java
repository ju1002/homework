package com.kh.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Soccer {
	private int userNo;
	private String userName;
	private String userPosition;
	private int salary;
	private String userTeam;
	private Date enrollDate;
	
	public Soccer() {
		super();
	}


	public Soccer(String userName, String userPosition, int salary, String userTeam) {
		super();
		this.userName = userName;
		this.userPosition = userPosition;
		this.salary = salary;
		this.userTeam = userTeam;
	}


	public Soccer(int userNo, String userName, String userPosition, int salary, String userTeam, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userPosition = userPosition;
		this.salary = salary;
		this.userTeam = userTeam;
		this.enrollDate = enrollDate;
	}

	public Soccer(String userName, String userPosition, String userTeam) {
		super();
		this.userName = userName;
		this.userPosition = userPosition;
		this.userTeam = userTeam;
	}


	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Soccer [userNo=" + userNo + ", userName=" + userName + ", userPosition=" + userPosition + ", salary="
				+ salary + ", userTeam=" + userTeam + ", enrollDate=" + enrollDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(enrollDate, salary, userName, userNo, userPosition, userTeam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soccer other = (Soccer) obj;
		return Objects.equals(enrollDate, other.enrollDate) && salary == other.salary
				&& Objects.equals(userName, other.userName) && userNo == other.userNo
				&& Objects.equals(userPosition, other.userPosition) && Objects.equals(userTeam, other.userTeam);
	}
	
	
	

}
