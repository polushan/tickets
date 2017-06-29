<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="util.Answer, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>timetable</title>
</head>
<body>
<table border="1">
		<caption>Результат запроса</caption>
		<tr>
			<th>Куда</th>
			<th>Откуда</th>			
			<th>Название рейса</th>
			<th>Тип транспортагда</th>
			<th>Время прибытия</th>
			<th>Время отбытия</th>
		</tr>
	<%
	Answer answer = (Answer)request.getAttribute("timetable");
	List<String> to = answer.getTo();
	List<String> from = answer.getFrom();
	List<String> title = answer.getTitle();
	List<String> transportType = answer.gettransportType();
	List<String> arrival = answer.getArrival();
	List<String> departure = answer.getDeparture();	
	if (!to.isEmpty()) {
	    for (int i = 0; i < to.size(); i++) {
		     out.println("<tr>");
		     out.println("<td>" + to.get(i) + "</td>");
		     out.println("<td>" + from.get(i) + "</td>");
		     out.println("<td>" + title.get(i) + "</td>");
		     out.println("<td>" + transportType.get(i) + "</td>");
		     out.println("<td>" + arrival.get(i) + "</td>");
		     out.println("<td>" + departure.get(i) + "</td>");
		     out.println("</tr>");
	    }
	} else {
		out.println("<b>" + "Ничего не найдено" + "</b>");
	}
	
	%>
</table>
<a href="/"> Назад</a>
</body>
</html>