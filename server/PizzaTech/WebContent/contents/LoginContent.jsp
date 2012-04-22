<% if ((session.getAttribute("logged")!=null)){%>
		<br>Login: 
		<%} %>
<FORM METHOD=POST ACTION="SaveName.jsp">
<table border="0" cellspacing="0" cellpadding="0">
	 <tr>
	  <td>User Name: </td>
	  <td><input type="text" size="20" name="username" />
	 </tr>
	 <tr>
	  <td>Password: </td>
	  <td><input type="password" size="20" name="password" />
	 </tr>
	 <tr>
	  <td>&nbsp;</td>
	   <td><input type="submit" value="Submit"/></td>
	 </tr>
	</table>
</FORM>