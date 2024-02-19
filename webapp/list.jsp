<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("personList");//아까 set으로 넣었기때문에 get으로 꺼내준다.별명인 "personList"를 넣어줬기때문에 이걸로 꺼내온다.
	//시각을 넓혀서 형변환을 해줘야함. (List<PersonVo>)는 캐스팅임.
	System.out.println(personList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>전화번호부</h1>
<h2>리스트</h2>
<p>
	등록된 전화번호 리스트입니다.
</p>
<% for(int i=0; i<personList.size(); i++){
	%>
	
	<table border="1">
		<tr>
			<th>이름(name)</th>
			<td><%= personList.get(i).getName() %></td>
		</tr>
		<tr>
			<th>핸드폰(hp)</th>
			<td><%= personList.get(i).getHp() %></td>
		</tr>
		<tr>
			<th>회사(company)</th>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
	</table>
	<br>
	<% 
	}
	%>

</body>
</html>