<%@page import="client.DBInterface"%>
<%@page import="client.Pizza"%>
<%@page  import="client.Order"%>
<%@page import="java.util.*"  %>
<%@page import= "java.text.DecimalFormat" %>
<%@page import="client.DBImplementation" %>
<% DBInterface database = DBImplementation.getInstance(getServletContext());%>
<% List <Order> orders = database.getAllOrders();  %>
<html>
<%
DecimalFormat df = new DecimalFormat("#.## RON");
for (int i=0; i<orders.size(); i++){%>
	<br />
	<div class="pizza">
	<b> Address: </b> <%= orders.get(i).getAddress()%>
   	<br />
   <b> Pizza: </b>
   	<div class="command_content">
	    <%Set<Pizza> ord = orders.get(i).getPizzas();%>
	    <% 
	    Iterator<Pizza> it = ord.iterator();
	    Pizza pizza;
	    int j = 0;
	    while (it.hasNext()) { %>
	    	<%j++; 
	    	  pizza = (Pizza)it.next(); %>
	    	<%= j %>. <%= pizza.getName() %>			
	    	<br />
	    <% 	} %>
    </div>
    <b>Pret:</b> <%= df.format(orders.get(i).getPrice()) %>
    </div>
<% }%>