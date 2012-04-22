<%
try{ String userlog = (String)session.getAttribute("user");
if ((userlog==null)||(!userlog.equals("admin")))
	request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
	//response.sendRedirect("AdminLogin.jsp");
else {%>
Hello, <%= session.getAttribute( "theName" ) %>
<br>Aici vine template-ul corespunzator administratorului
<form method="post" action="AllOrders.jsp">
	  <input type="submit"  value="Visualize All Orders" />
</form>	  
	
<form method="post" action="UnprocOrders.jsp">	
	   <input type="submit" value="Unprocessed orders"/>
</form>
<form method="post" action="ProcOrders.jsp">
	   <input type="submit" value="Processed orders"/>
</form>	

<form method="post" action="LogOut.jsp">
	<input type="submit" value="Log out"/>
</form> 
<% }	
}
catch (NullPointerException np){
response.sendRedirect("AdminLogin.jsp");
}%>  