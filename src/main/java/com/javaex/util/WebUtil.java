package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {//static을 쓰면 new안해도 미리 올라가 있a
	//필드
	//생성자
	//메소드 gs
	//메소드 일반
	public static void forward(HttpServletRequest request
						,HttpServletResponse response
						,String path ) throws ServletException, IOException {//경로알려달라는것
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response); //예외처리: 밖으로 던져버리기
	}
	public static void redirect(HttpServletRequest request //파라미터 바뀌면 힘드니까 구색 맞추기
						,HttpServletResponse response
						,String url) throws ServletException, IOException {//주소 알려달라는것
		response.sendRedirect(url);
	}
}	
