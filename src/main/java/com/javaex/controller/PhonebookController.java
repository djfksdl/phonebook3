package com.javaex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
    
	//생성자- 기본생성자 사용
//    public PhonebookController() {
//        super(); //다른생성자가 있으면 지우면 안됨!
//    }

    //메소드-gs

    //메소드-일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath()); 

		
		System.out.println("PhonebookController.goGet()");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		//한개씩만 실행할 수 있어서 if문 만들어줘서 000~
//		if(action.equals("wform") ) {//주소가 아닌 글자비교니까 .equals}
		if("wform".equals(action)) { //반대로 써주면 000할 수 있는 경우를 줄일 수 있다? 12:40
			System.out.println("wform:등록폼");
			
			//jsp 한테 html그리기 응답해라 => '포워드'라고 부름
			RequestDispatcher rd=  request.getRequestDispatcher("/writeForm.jsp");//수사 넘기는거임
			rd.forward(request, response);
		}else if("insert".equals(action)) {
			System.out.println("wform:등록");
			
			String name = request.getParameter("name"); //변수는 이름이 달라도됨. 헷갈려서 같은 이름 써주긴함. 
			String hp = request.getParameter("hp");
			String company = request.getParameter("company"); // request에 있는 메소드를 써줌(getParameter)
			
			//Vo로 묶기. 하나 이상되면 묶어주는게 좋다.
//			-> VO를 만들어줘서 한꺼번에 보내준다.
			PersonVo personVo = new PersonVo(name, hp, company); //생성자 추가적으로 만들기 싫으면 set으로 가져오면 됨
			System.out.println(personVo.toString());
			
			System.out.println(name);
			System.out.println(hp);
			System.out.println(company);
			
			//db에 저장 -> 연결하기위해서 build path, Deployment Assembly설정
			PhoneDao phoneDao = new PhoneDao();//메모리에 올려야 personInsert.메소드를 쓸 수 있다. 
			phoneDao.personInsert(personVo);
			
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
