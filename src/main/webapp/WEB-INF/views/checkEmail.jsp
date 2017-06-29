<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sure?</title>
</head>
<body>
<p>Вам на почту было отправлен проверочный код. Введите его в форму ниже</p>
${tryAgain}<br/>
<form action="/checkEmail" method="post">
		Code: <input name="code" type="text" /><br />
		<input type="submit" name="enter" value="enter" />
</form>
</body>
</html>