
<%@page import="client.DBInterface"%>
<%@page import="client.Ingredient"%>
<%@page import="client.Pizza"%>
<%@page import="client.Order"%>
<%@page import="client.MiscPizzaHelper"%>
<%@page import="java.util.*"  %>
<%@page import="client.DBImplementation" %>
<%
ArrayList<Pizza> pizzaPerm;
ArrayList<Pizza> p=new ArrayList<Pizza>();
if (request.getParameter("id")!=null)
{	
	pizzaPerm=(ArrayList<Pizza>)session.getAttribute("allPizzaCache");
	{
	if (session.getAttribute("cos")==null)
	{		
		

		if (request.getParameter("added")!=null)
		{
			for (int i=0;i<pizzaPerm.size();i++)
			if (pizzaPerm.get(i).getName().equals(request.getParameter("id")))
				p.add(pizzaPerm.get(i));
		}		
		session.setAttribute("cos",p);
		
	}
	else
	{
		p=(ArrayList<Pizza>)session.getAttribute("cos");						
		
		if (request.getParameter("added")!=null)
		{
		for (int i=0;i<pizzaPerm.size();i++)
			if (pizzaPerm.get(i).getName().equals(request.getParameter("id")))
		p.add(pizzaPerm.get(i));
		}
			else
				if (request.getParameter("sub")!=null)
				{
					for (int i=0;i<p.size();i++)
						if (p.get(i).getName().equals(request.getParameter("id")))
					{
						p.remove(i);
						break;
					}
				}
		
		session.setAttribute("cos",p);
	}
	}
}
else
{
	pizzaPerm=DBImplementation.getInstance().getPermanentPizzas();
	ArrayList<Pizza> pizzaSave=(ArrayList<Pizza>)session.getAttribute("save");
	if (pizzaSave!=null)
	pizzaPerm.addAll(pizzaSave);
	session.setAttribute("allPizzaCache",pizzaPerm);
	p=(ArrayList<Pizza>)session.getAttribute("cos");
	//session.setAttribute("cos",null);
}
%>
<div style="background-color : white; width : 80%; border-radius : 1em; -moz-border-radius : 1em; margin : 0 auto; padding-bottom : 10px; padding-left : 10px;">
<center>
<table style="text-align: left; width: 70%; height: 289px;"
 border="0" cellpadding="2" cellspacing="2">
  <tbody>
    <tr>
      Produse:
    </tr>
    <tr>
      <td style="width: 301px; height: 252px; vertical-align: top;">
      <form method="post" action="<%=request.getRequestURL() %>" name="asta" >       
<table border="1" style="text-align: left; width: 460px;">
<%
for (int i=0;i<pizzaPerm.size();i++)	
{
	Pizza crt=pizzaPerm.get(i);	
	%>
	<tr>
	<td style="width:500px"><%= crt.getName() %>(<%= (Math.round(crt.getPrice()*100.0))/100.0 %>)</td><td style="width:500px"><%= crt.getDescription() %></td> <td><form method="post" action="<%=request.getRequestURL() %>" ><input type="hidden" name="id" value="<%= crt.getName() %>"></input><input type="submit" name="added" value="+"><input type="submit" name="sub" value="-"><br><%=MiscPizzaHelper.pizzaCount(crt,p) %></form></td>
	</tr>
<% }%>
</table>
</center>
<div style="background-color : white; width : 80%; border-radius : 1em; -moz-border-radius : 1em;">