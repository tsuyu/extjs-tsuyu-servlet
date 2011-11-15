<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String theme = request.getParameter("theme");
	session.setAttribute("theme",theme);
	response.sendRedirect("main.jsp");
%>