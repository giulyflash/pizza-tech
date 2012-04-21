<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String userlog = (String)session.getAttribute("user");
if ((userlog==null)||(!userlog.equals("admin"))){
	session.setAttribute("logged","fals");
	request.getRequestDispatcher("Index.jsp").forward(request,response);
}
else {
	session.invalidate();
	response.sendRedirect("Index.jsp");
	}%>    