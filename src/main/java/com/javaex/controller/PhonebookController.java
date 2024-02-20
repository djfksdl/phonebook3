package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {// HttpServlet을 상속받는걸 매번쓰기 귀찮아서 자동적으로 servlet을 만들어준거임
	//필드
	private static final long serialVersionUID = 1L;
    
	//생성자- 기본생성자 사용
//    public PhonebookController() {
//        super(); //디폴드 생성자라 지워도되는데, 만약 다른생성자가 있으면 지우면 안됨!
//    }

    //메소드-gs

    //메소드-일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// void라 따로 return하는건 없음. 이안에서 짜야함. 
//		response.getWriter().append("Served at: ").append(request.getContextPath()); 기본적으로 있는 애인데 쌤경험한바론 필요없어서 지워버림

		
		System.out.println("PhonebookController.goGet()");// doGet()메소드가 실행되는거 확인하기 위해서 쓴것
		
		String action = request.getParameter("action"); // 메소드써서 꺼내주고 java 변수로 담음
		System.out.println(action);
		
		//접수는 한군데인데 2가지가 작동이 될 수 없기때문에 의사표현을 주소로 해야함.
//		if(action.equals("wform") ) {//주소가 아닌 글자비교니까 .equals}
		if("wform".equals(action)) { //그대로 쓰면 값을 안보낼때 null point예외상황이 생길 수 있기때문에 반대로 써서 그 상황을 줄임
			System.out.println("wform:등록폼");
			
			//jsp 한테 html그리기 응답(response)해라 .일을 넘기는 작업 => '포워드'
			RequestDispatcher rd=  request.getRequestDispatcher("/writeForm.jsp");//수사 넘기는거임.담당자를 정해준다고 생각하면 됨. 
			rd.forward(request, response); // 가지고있는 객체 주소를 넘겨줌.따로 send같은거 안써줘도 여기에 써주면 넘어감.
		}else if("insert".equals(action)) {
			System.out.println("wform:등록");
			
			//값을 저장하는 방법
			String name = request.getParameter("name"); //변수는 이름이 달라도됨. 헷갈려서 같은 이름 써주긴함. 
			String hp = request.getParameter("hp");
			String company = request.getParameter("company"); // request에 있는 메소드를 써줌(getParameter)
			
			//Vo로 묶기. 지금은 3개인데 더 많아질 수 있음. 하나 이상되면 묶어주는게 좋다.
//			-> VO를 만들어줘서 한꺼번에 보내준다.
			PersonVo personVo = new PersonVo(name, hp, company); //생성자 추가적으로 만들기 싫으면 set으로 가져오면 됨
			System.out.println(personVo.toString());
			
			System.out.println(name);
			System.out.println(hp);
			System.out.println(company);
			
			//db관련 업무
			PhoneDao phoneDao = new PhoneDao();//메모리에 올려야 personInsert같은 메소드를 쓸 수 있다.
			
			//db에 저장 -> 연결하기위해서 build path, Deployment Assembly설정
			phoneDao.personInsert(personVo);
			
			//리다이렉트- 빈화면인데 주소 숨기고가서 엔터치는것과 똑같다. 그래서 빈화면에서 리스트 나오는 화면으로 바뀐다.
			response.sendRedirect("/phonebook3/pbc?action=list"); //주소 앞에 부분 삭제해도됨
			
			//리다이렉트 사용으로 주석처리
//			//db에서 전체 데이터 가져오기
//			List<PersonVo> personList = phoneDao.personSelect();
////			System.out.println(personList);
//			
//			//request에 담기
//			request.setAttribute("personList", personList); //앞에는 문자열이고 뒤에는 주소를 넣어주기=>request안에 주소(0x999)를 넣어준것임/ "이름"이 jsp에서도 같아야 인식할 수 있음.(주의)
//			
//			//포워드
//			RequestDispatcher rd=  request.getRequestDispatcher("/list.jsp");
//			rd.forward(request, response);//같이 넘겨줘야해서 꼭 써줘야함.
			
		}else if("list".equals(action)){
			System.out.println("list:리스트");
			
			//db사용
			PhoneDao phoneDao = new PhoneDao();//Dao써야해서 메모리에 올림- 어제 만든것과 동일한.pSelect() 쓸 수 있어서 따로 안만들고 만든거 씀
			
			//리스트 가져오기
			List<PersonVo> personList = phoneDao.personSelect();
//			System.out.println(personList);
			
			//데이터 담기 후 포워드
			request.setAttribute("personList", personList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			
			
			
		}else if("delete".equals(action)) {
			System.out.println("delete:삭제");
			int no = Integer.parseInt(request.getParameter("no"));// String이라 캐스팅 ㄱㄱ
			System.out.println(no);
			
			// db사용
			PhoneDao phoneDao = new PhoneDao();

			// 삭제
			phoneDao.personDelete(no);
			
			// 리다이렉트 - 반응이니까 request가 아닌 response를 써준다.
			response.sendRedirect("/phonebook3/pbc?action=list"); 
		}
//		else if() {
//			
//		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
