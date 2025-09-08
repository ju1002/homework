package com.kh.board.model.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;

public class BoardDAO {
	public int insertBoard(Connection conn , Board board) {
		//변수 선언
		
		
	}
	public List<Board> selectBoardList(SqlSession session){
		return session.selectList("boardMapper.selectBoardList");
		
		
	}
	public Board selectBoard(SqlSession session , int boardNo) {
		return session.selectOne("boardMapper.selectBoard");
	}

	
	public int deleteBoard(Connection conn, int boardNo) {
		/*int result = 0;//sql싨행 결과니까 
		PreparedStatement pstmt =null; //sql 실행할 것
		String sql = """
				        
				           UPDATE 
				                BOARD
				           SET
				                DELETE_YN = 'Y'
				            WHERE
				                BOARD_NO = ?     
				""";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,boardNo); //사요자가 입력한 값을 세팅할 꺼임
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return 0;
		*/
		try(PreparedStatement pstmt = conn.prepareStatement("""
				        
				           UPDATE 
				                BOARD
				           SET
				                DELETE_YN = 'Y'
				            WHERE
				                BOARD_NO = ?     
				""")){pstmt.setInt(1, boardNo);
				return pstmt.executeUpdate();} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return  0;
				
			
		
	
		
	}
	
	
	
	


		
	public void outputHTML(Connection conn) {
		
		FileWriter fos = null;
		BufferedWriter bw = null;
		
		try {
			fos = new FileWriter("Template_BOARD.html");
			bw = new BufferedWriter(fos);
			List<Board> boardList = selectBoardList(session);
			String html = "<!DOCTYPE html>";
			html += "<html>";
			html += "<head><title>게시판이예용</title>";
			html += "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css'>";
			html += "<script src='https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js'></script>";
			html += "<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js'></script>";
			html += "<script src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js'></script>";
			html += "</head>";
			html += "<body>";
			html += "<h1 style='margin-bottom:30px; text-align:center'>JDBC 게시판 서비스입니다</h1>";
			html += "<table class='table'>";
			html += "<tr><th>제목</th><th>작성자</th><th>작성일</th></tr>";
			for(Board b : boardList) {
				html += "<tr>";
				html += "<td>" + b.getBoardTitle() + "</td>";
				html += "<td>" + b.getBoardWriter() + "</td>";
				html += "<td>" + b.getCreateDate() + "</td>";
				html += "</tr>";
			}
			html += "</table>";
			html += "</body>";
			html += "</html>";
			
			bw.write(html);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
