package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	//Connection 객체의 역할을 대신할 SqlSession객체를 만들어서 반환해주는 메소드흫 구현할 것
	
	public static SqlSession getSqlSession() {
	//외부에서 가져오니까 public 
	//계속해서 고정적으로 사용할 거니까 static SqlSession으로 받을 거니까 반환형도 똑같이
		SqlSession sqlSession = null;
//	mybatis-config.xml파일에 있는 내을 읽어와서
// 해당 DB와 접속된 SqlSession객체를 반환
		String config = "mybatis-config.xml";
		// /는 bin폴더(default output folder)를 의미함
		try {
		InputStream stream =	Resources.getResourceAsStream(config);
			//1단계 : SqlSessionFactoryBuilder 만들기
		//만드는 방법 : 기본생성자를 호출한다.
		//new SqlSessionFactioryBuilder();
		   // 2단계 : SqlSsessionFactiry 만들시
		//만드는 방법 : 메소드를 호출한다.
		// .build(접속 내용을 담을 파일을 읽어온 입력스트림)
		sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
	
	      //3단계 : SqlSession 만들기
		// 만드는 방법 : 메소드를 호출한다.
		//.openSession();
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return sqlSession;
		
	}
	
	

}
