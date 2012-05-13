<%@ taglib uri="/WEB-INF/tlds/template.tld" prefix='template' %>
<%String userlog = (String)session.getAttribute("user");
if ((userlog==null)||(!userlog.equals("admin"))){
	session.setAttribute("logged","fals");
	request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
	}
else{
	session.setAttribute("logged",null);%>
<template:insert template='Template.jsp'>
  <template:put name='title' content='Admin Page' direct='true'/>
  <template:put name='content' content='contents/AdminContent.jsp'/>
</template:insert>
<%} %>
