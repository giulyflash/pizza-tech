<%@page import="pizzatech.dbaccess.DBInterface"%>
<%@page import="pizzatech.model.Pizza"%>
<%@page import="java.text.DecimalFormat"%><html>
<%@page  import="pizzatech.model.Order"%>
<%@page import="java.util.*"  %>
<%@page import="pizzatech.dbaccess.DBImplementation" %>
<% DBInterface database = DBImplementation.getInstance();%>
<% ArrayList <Order> orders = database.getAllOrders();  %>

<%
DecimalFormat df = new DecimalFormat("#.## RON");
for (int i=0; i<orders.size(); i++){%>
	<br />
	<div class="pizza">
	<b> Address: </b> <%= orders.get(i).getAddress()%>
   	<br />
   <b> Pizza: </b>
   	<div class="command_content">
	    <%Set ord = orders.get(i).getPizzas();%>
	    <% 
	    Iterator it = ord.iterator();
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