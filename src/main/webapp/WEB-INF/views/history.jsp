<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="model.User, model.Request, java.util.ArrayList, java.util.List"%>
<%@ page import="dao.UserDao" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>History</title>
</head>
<body>
	<table border="1">
		<caption>Последние завопросы</caption>
		<tr>
			<th>Откуда</th>
			<th>Куда</th>
			<th>Когда</th>
			<th>Тип транспорта</th>
		</tr>
		<jsp:useBean id="userDao" class="dao.UserDao" />
		<%
			User user = (User) request.getSession(true).getAttribute("user");
			Request req = null;
			if (user != null) {
				List<Request> history = userDao.getHistory(user);
				if (!history.isEmpty()) {
					for (int i = 0; i < history.size(); i++) {
						req = history.get(i);
						out.println("<tr>");
						out.println("<td>" + req.getFrom() + "</td>");
						out.println("<td>" + req.getTo() + "</td>");
						out.println("<td>" + req.getDate() + "</td>");
						out.println("<td>" + req.getTransportType() + "</td>");
						out.println("</tr>");
					}
				}
			} else {
				req = (Request) request.getSession(true).getAttribute("lastRequest");
				if (req != null) {
					out.println("<tr>");
					out.println("<td>" + req.getFrom() + "</td>");
					out.println("<td>" + req.getTo() + "</td>");
					out.println("<td>" + req.getDate() + "</td>");
					out.println("<td>" + req.getTransportType() + "</td>");
					out.println("</tr>");
				}
			}
		%>
	</table>
	<a href="/"> Перейти к поиску</a>
</body>
</html>