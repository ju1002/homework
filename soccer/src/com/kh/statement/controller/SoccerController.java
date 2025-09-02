package com.kh.statement.controller;

import com.kh.statement.model.dao.SoccerDao;
import com.kh.statement.model.vo.Soccer;

/**view에서 넘어온 요청을 처리해주는 클래스
 * 1. 메소드로 전달받은 데이터를 가공처리 한 뒤 DAO로 전달
 * 2. DAO로부터 받은 반환 값을 받아서 view로 반환
 * 
 */
public class SoccerController {
	/**
	 * @param userName
	 * @param userPosition
	 * @param userTeam
	 * 뷰로부터 전달 받은 문자열 값 4개를 DAO 로 전달하는메소드
	 */
	public void plus(String userName, String userPosition , String userTeam) {
		//DAO로 값이 넘어가기 위해서는 1개의 값으로 넘어가야 한다.
		//그러므로 여기서 가공을 해서 넘겨줘야한다.
		Soccer soccer = new Soccer(userName, userPosition, userTeam);
		new SoccerDao().plus(soccer);
		
		
	}

}
