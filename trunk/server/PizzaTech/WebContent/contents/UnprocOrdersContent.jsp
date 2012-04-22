<%@page import="pizzatech.dbaccess.DBInterface"%>
<%@page import="pizzatech.model.Pizza"%>
<%@page import="java.text.DecimalFormat"%><html>
<%@page  import="pizzatech.model.Order"%>
<%@page import="java.util.*"  %>
<%@page import="pizzatech.dbaccess.DBImplementation" %>
<% DBInterface database = DBImplementation.getInstance();%>

<% ArrayList <Order> orders = database.getUndeliveredOrders(); %>

<div >
Sunt <%= orders.size() %> comenzi nepreluate:
</div> 

<% 
DecimalFormat df = new DecimalFormat("#.## RON");
String address;
int id;
for (int i=0; i<orders.size(); i++){
	id=database.getUndeliveredOrders().get(i).getId();
	address=database.getUndeliveredOrders().get(i).getAddress();%>	
	<br />
	<div class="pizza">
	<b> Adresa: </b>
	<%= database.getUndeliveredOrders().get(i).getAddress()%>
	<br />
   <b> Comanda: </b>
   	<div class="command_content">
    <%Set ord = database.getUndeliveredOrders().get(i).getPizzas();%>
    <% 
    Iterator it = ord.iterator();
    String pizza;
    int j = 0;
    while (it.hasNext()) { 
    	j++; %>
    	<div>
	    	<%pizza = ((Pizza)it.next()).getName(); %>
	    	<%= j %>.&nbsp;<%= pizza %>	
    	</div>		
    <% 	} %>
    <FORM METHOD=POST ACTION="Proceseaza.jsp" >
    <input type="hidden" name="id" value="<%=id%>" />
    <input type="submit" size="20" value="proceseaza" align="right"/>
    </FORM>
    </div>
    <b>Pret:</b> <%= df.format(orders.get(i).getPrice()) %>
    </div>
    
<% }%>