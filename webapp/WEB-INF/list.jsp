<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> <!-- import시켜주는것 -->
<%@ page import="com.javaex.vo.PersonVo" %>
<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("personList");//아까 set으로 넣었기때문에 get으로 꺼내준다.별명인 "personList"를 넣어줬기때문에 이걸로 꺼내온다.
	// 주소를 꺼냄. 따라가면 List<personVO>가 있다. 
	//포워드하면서 보낼때 데이터가 여러개 보낼때도 있는데, 다 받을 수 있도록 오브젝트로 만들어놨음. 그래서 받는 순간 꺼낼때는 시각을 넓혀서 형변환을 해줘야함. (List<PersonVo>)는 캐스팅임.
	//그럼 personList가 0x999라 주소가 들어가있는것!
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
		<tr>
			<td>
			<%=personList.get(i).getPersonId() %>
			<a href="/phonebook3/pbc?action=delete&no=<%=personList.get(i).getPersonId() %>">[삭제]</a>
			</td>
			<td>
			<a href="/phonebook3/pbc?action=update&
			no=<%=personList.get(i).getPersonId()%>&
			name=<%=personList.get(i).getName()%>&
			hp=<%=personList.get(i).getHp()%>&
			company=<%=personList.get(i).getCompany()%>">[수정]
			</td> <!-- 실제 수정이 아닌 수정폼을 불러오는것! -->
		</tr>
	</table>
	<br>
	<% 
	}
	%>

</body>
</html>