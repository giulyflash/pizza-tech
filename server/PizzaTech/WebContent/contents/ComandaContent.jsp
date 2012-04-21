
<%@page import="client.DBInterface"%>
<%@page import="client.Ingredient"%>
<%@page import="client.Pizza"%>
<%@page import="client.Order"%>
<%@page import="java.util.*"  %>
<%@page import="client.DBImplementation" %>
<%@page import="client.MiscPizzaHelper"%>

<%
	ArrayList<Pizza> pizzas=(ArrayList<Pizza>)session.getAttribute("cos");
	HashMap<String,Boolean> hm= new HashMap<String,Boolean>();
	HashMap<String,Pizza> single= new HashMap<String,Pizza>();
		
	Order order=new Order();
	if (pizzas!=null)
	{
		for (int i=0;i<pizzas.size();i++)
		order.addPizza( ((Pizza)(pizzas.get(i)).clone()));				
	}
	if (request.getParameter("adresa")!=null)
	{
		
		Iterator<Pizza> iterator = order.getPizzas().iterator();		
		while ( iterator.hasNext() ) 
		{
			Pizza pizza=iterator.next();
			if (!pizza.getIsPermanent())
				if (single.get(pizza.getName())==null)
				{
					DBImplementation.getInstance().addPizza(pizza);
					single.put(pizza.getName(),pizza);
				}
				else
				pizza.setId(single.get(pizza.getName()).getId());
		}
		
		order.setAddress(request.getParameter("adresa"));
		order.setDelivered(false);		
		DBImplementation.getInstance().addOrder(order);
		out.print("Comanda a fost trimisa.");
		session.setAttribute("cos",null);
		return; 
	}
%>

<center>
<div style="background-color : white; width : 80%; border-radius : 1em; -moz-border-radius : 1em;">
<table style="text-align: left; width: 70%; height: 289px;"
 border="0" cellpadding="2" cellspacing="2">
  <tbody>
    <tr>
      Produse Comandate:
    </tr>
    <tr>
      <td style="width: 301px; height: 252px; vertical-align: top;">
      <form method="post" action="<%=request.getRequestURL() %>" name="asta" />       
<table border="1" style="text-align: left; width: 460px;">
<%  
	Iterator<Pizza> iterator = order.getPizzas().iterator();	
	while ( iterator.hasNext() ) {
			
			Pizza pizza = iterator.next();
			if (hm.get(pizza.getName())==null)
			{
				hm.put(pizza.getName(),true);
				double price;
				/*if (pizza.getIsPermanent())
					price=DBImplementation.getInstance().getPizzaById(pizza.getId()).getPrice();
				else
					price=pizza.getPrice();
				*/
	%>
	<tr>
	<td style="width:500px"><%= pizza.getName() %>(pret unit:<%= (Math.round(pizza.getPrice()*100.0))/100.0 %>)</td><td><%=MiscPizzaHelper.pizzaCount(pizza,pizzas) %></td>
	</tr>
<% } 
	}
%>
</table>



</div>
<br />
Pret total comanda:<b><%= (Math.round(order.getPrice()*100.0))/100.0 %></b><br>
<form method="post" action="<%=request.getRequestURL() %>" >Comanda la adresa: <input type="text" name="adresa"> <input type="submit" value="comanda"></form>
