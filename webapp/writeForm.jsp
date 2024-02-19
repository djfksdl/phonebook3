<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>전화번호부</h1>
<h2>등록폼</h2>
<p>
	전화번호를 등록하려면<br>
	아래 항목을 기입하고 "등록"버튼을 클릭하세요.
</p>
http://localhost:8080/phonebook3/pbc?name=황일영&hp=010&company=02 
<!--  pbc까지가 프로그램이다. 뒤에는 데이터임. -->

<form action="http://localhost:8080/phonebook3/pbc" method="POST"><!-- get으로 해서 먼저는 보이게하기 -->
	<div>
		<label>이름(name)</label> <!-- 한줄 전체를 요소라고함. 태그 + 내용 -->
		<input type="text" name="name" value=""><!-- name="name"은 다른거다.  -->
	</div>
	
	<div>
		<label>핸드폰(hp)</label>
		<input type="text" name="hp" value="">
	</div>
	
	<div>
		<label>회사(company)</label>
		<input type="text" name="company" value="">
	</div>
	
	<input type="text" name="action" value="insert"> <!-- action에 값을 넣어주기 위해서 만들어줌. 개발할땐 text로 놓고 마지막에 hidden으로 안보이게하기 -->
	<br>
	<button type="submit">등록</button>
</form>
<br>
<a href="">리스트페이지로 이동</a>


</body>
</html>