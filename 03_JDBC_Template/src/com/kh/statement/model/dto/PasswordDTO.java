package com.kh.statement.model.dto;

public class PasswordDTO {
	/*DTO(DATA TRANSFER OBJECT)
	 * 데이터를 전송하는데 목적을 둔 객체
	 * 이동할 때 사용
	 * 사실 VO클래스는 불변성을 가져야하기 때문에 SETTER메소드를 가지고 있을 수 없음
	 * 데이터 전송이 목적일 떈 VO대신 DTO클래스를 만들어서 계층간 이동하는 용도로 사용함.
	 *데이터 전송의 목적이 있다.
	 **/
	private String userId;
	private String userPwd;
	private String newPassword;
	public PasswordDTO(String userId, String userPwd, String newPassword) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.newPassword = newPassword;
	}
	public PasswordDTO() {
		super();
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}

