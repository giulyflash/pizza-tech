<%@page import="client.DBInterface"%>
<%@page import="client.Pizza"%>
<%@page import="java.text.DecimalFormat"%><html>
<%@page  import="client.Order"%>
<%@page import="java.util.*"  %>
<%@page import="client.DBImplementation" %>
<% DBInterface database = DBImplementation.getInstance(getServletContext());%>
<% List <Order> orders = database.getDeliveredOrders(); %>

<div >
Sunt <%= orders.size() %> comenzi preluate:
</div> 
<% 
DecimalFormat df = new DecimalFormat("#.## RON");
for (int i=0; i<orders.size(); i++){%>
	
	<br />
	<div class="pizza">
	<b> Adresa: </b>
	 <%= database.getDeliveredOrders().get(i).getAddress()%>
   <br />
   <b> Comanda: </b>
   	<div class="command_content">
    <%Set ord = database.getDeliveredOrders().get(i).getPizzas();%>
    <% 
    Iterator it = ord.iterator();
    String pizza;
    int j=0;
    while (it.hasNext()) { 
    	j++;%>
    	<div>
    		<%pizza = ((Pizza)it.next()).getName(); %>
    		<%= j %>.&nbsp;<%= pizza %>	
    	</div>		
    <% 	} %>
    </div>
    <b>Pret:</b> <%= df.format(orders.get(i).getPrice()) %>
    </div>
<% }%>