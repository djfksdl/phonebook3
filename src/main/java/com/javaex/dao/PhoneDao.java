package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {// db 관련된 일만 전문적으로 하는 Dao

	// 필드-없음 
	// 생성자 
	// 메소드-gs 
	// 메소드-일반

	// 전체 가져오기
	public List<PersonVo> personSelect() {//void로 해놨다가 주소를 따라가면 자료형이 저렇게 되어있어서 바꿔준다.return을 하기떄문에
		
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " select person_id ";
			query += " ,name ";
			query += " ,hp ";
			query += " ,company ";
			query += " from person ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);

			//실행
			rs = pstmt.executeQuery(); //위에 이미 정의해놨음.
			
		// 4.결과처리
			
			while(rs.next()) { //다음줄로가다가 끝나면 false나오는데 그때까지 반복해서 내려간다.
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				// db에서 가져온 데이터 vo로 묶기
				PersonVo personVo = new PersonVo(personId, name, hp , company);
				// 리스트에 주소추가
				personList.add(personVo);
//				System.out.println(personVo);
			}
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		} 
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		return personList;
	}
	
	// 등록
	public int personInsert(PersonVo personVo) { //묶은걸 주소를 써주기 ('자료형' '주소')
		int count = -1; //-1이라면 시작도 못한거임.
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Mysql) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//sql문 준비
			String query = "";
			query += " insert into person ";
			query += " values(null , ?, ? , ?) ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany() );
			
			// 실행
			count = pstmt.executeUpdate(); // 여기서 int하면 밑에 return 값이 살아있지 못한다. 그래서 위에다가 정의를 먼저 해준다.
			
			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}
	// 삭제
	
	

	// 수정

}
