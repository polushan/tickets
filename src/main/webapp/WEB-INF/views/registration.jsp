<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="true" autoFlush="true" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>registration</title>
</head>
<body>
    ${tryAgain}<br/>
	<form method="post">
		Email: <input name="email" type="text" maxlength=20 /><br />
		Login: <input name="login" type="text" maxlength=20 /><br />
		Password: <input name="password" type="password" maxlength=20 /><br />
		<input type="submit" name="enter" value="enter" />
	</form>
<a href="/"> Вернуться к поиску</a>
</body>
</html>