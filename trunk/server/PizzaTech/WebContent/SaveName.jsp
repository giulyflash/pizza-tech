<% String name = request.getParameter( "username" ); session.setAttribute( "theName", name );%>
<% String passw = request.getParameter( "password" ); session.setAttribute("thePass", passw );

if (name==null)
	name="";
if (passw==null)
	passw="";
if ((passw.equals(""))||(name.equals("")))
	response.sendRedirect("PassAndNameReq.jsp");
else{
	if(passw.equals("parola")){
		session.setAttribute("user", "admin");
		response.sendRedirect("Index.jsp");
	}
	else
		response.sendRedirect("WrongPass.jsp");
}
%>