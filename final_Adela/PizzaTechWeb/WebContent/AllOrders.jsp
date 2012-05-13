<%@ taglib uri="/WEB-INF/tlds/template.tld" prefix='template' %>
<% 
String userlog = (String)session.getAttribute("user");
if ((userlog==null)||(!userlog.equals("admin")))
	request.getRequestDispatcher("Index.jsp").forward(request,response);
else{%>
<template:insert template='Template.jsp'>
	<%--<template:put name="header" content='<link rel="stylesheet" type="text/css" href="css/admin.css"' direct='true' />
	<template:put name='title' content='Admin Page' direct='true'/> --%>
	<template:put name='content' content='contents/AllOrdersContent.jsp'/>
</template:insert>
<%}%>