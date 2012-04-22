<% String add=request.getParameter("id");
session.setAttribute("theOrderAddress", add);%>
<%@page import="pizzatech.dbaccess.DBInterface"%>
<%@page import="pizzatech.model.Pizza"%>
<%@page import="java.text.DecimalFormat"%>
<%@page  import="pizzatech.model.Order"%>
<%@page import="java.util.*"  %>
<%@page import="pizzatech.dbaccess.DBImplementation" %>
<%DBInterface database = DBImplementation.getInstance();%>

   <%
    int ID = Integer.parseInt(add);
	ArrayList <Order> orders = database.getUndeliveredOrders();
	int i;
	for(i=0;i<orders.size();i++){
		if(orders.get(i).getId()==ID)
			orders.get(i).setDelivered(true);
	}
   response.sendRedirect("UnprocOrders.jsp");
   %>