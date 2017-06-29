<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page autoFlush="true" errorPage="error.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<form method="post">
		Login: <input name="login" type="text" maxlength=20 /><br />
		Password: <input name="password" type="password" maxlength=20 /><br />
		<input type="submit" name="enter" value="enter" />
	</form>
<a href="/"> Вернуться к поиску</a>
</body>
</html>