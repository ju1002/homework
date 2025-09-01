package com.kh.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

/*
 * 
 * vo(value Object) => 값 객체 값을 담는 곳
 * 무슨 값? 테이블의 한 행에 대한  데이터를 기록할 저장용 객체
 * 클래스가 가지는 속성? 필드  객체의 속성을 담는 것 
 * 
 * 
 * vo 클래스의 필드 구성 자체를 member테이블의 컬럼들과 유사하게 구성
 * number와 enrolldate 는 사용자에게 값을 입력 받는게 아니라
 * sequencr밒 default값을 조건으로 사용하는 경우
 * 해당 필드를 제외한 나머지 필드값을 초기화 할 수 있는 생성자를 추가로 구성해둘것
 */

public class Member {
	
	  private int userNo;
	  private String userId; 
	  private String userPwd;
	  private String userName;
	  private String email;
	  private Date enrollDate;
	  //db테이블을 참고하여 컬러과 유사하게 구성
	  /*
	   * USERNO NUMBER PRIMARY KEY
	   * USERID VARCHAR2(15) UNIQUE NOT NULL,
	    USERPWD VARCHAR2(20) NOT NULL,
	    USERNAME VARCHAR2(15) NOT NULL,
	    EMAIL VARCHAR2(30),
	    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
	   */
	  
	  
	  
	public Member() {
		super();
	}

//기본값이 존재하는 컬럼을 제외하고 나머지 필드값만 초기화 해주는 생성자 

	public Member(String userId, String userPwd, String userName, String emamil) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = emamil;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmamil() {
		return email;
	}

	public void setEmamil(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", enrollDate=" + enrollDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enrollDate, userId, userName, userNo, userPwd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(email, other.email) && Objects.equals(enrollDate, other.enrollDate)
				&& Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName)
				&& userNo == other.userNo && Objects.equals(userPwd, other.userPwd);
	}

	
	  
	
	
	
	
	
	

}
