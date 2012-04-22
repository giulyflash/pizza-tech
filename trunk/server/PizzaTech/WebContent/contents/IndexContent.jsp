
<%@page import="pizzatech.dbaccess.DBInterface"%>
<%@page import="pizzatech.dbaccess.DBImplementation"%>


<% 	boolean logat = false;
	String loggedUser = (String)session.getAttribute("user");	
	if (loggedUser != null)
		if (loggedUser.equals("admin"))
			logat = true;
%>
<div id="shadow_continut" class="shadow">
		<div class="border">
			<div id="continut">
				<div class="title">
					Despre Noi
				</div>
				
				<div id="poza_camin">
				</div>
				<br/>
				Cea mai noua pizzerie din Complexul Studentesc Timisoara!
				<br />
				<ul style="text-align:left; margin-left : 30px;">	
					<li>nonconformista</li>
					<li>direct la tine in camin (Caminul 11 Bucatarie Etaj 4)</li>
					<li>poti sa ne trimiti link-ul spre reteta ta :)</li>
					<li>poti participa la preparare</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="shadow_noutati" class="shadow">
	<div class="border">
		<div id="noutati">
			<div class="title">
				Noutati
			</div>
			<div class="pizza">
				<img class="thumb" src="pics/vegetariana.jpg" />
				<div class="pizza_details">
					<b>Pizza vegetariana</b>
					<br />
					rosii, masline, mozzarella, ardei 
				</div>
			</div>
			<div class="pizza">
				<img class="thumb" src="pics/napoli.jpg" />
				<div class="pizza_details">
					<b>Pizza napoli</b>
					<br />
					capere, anchois, masline, mozzarella, ardei 
				</div>
			</div>
			<div class="pizza">
				<img class="thumb" src="pics/napoli.jpg" />
				<div class="pizza_details">
					<b>Pizza desert</b>
					<br />
					mozzarella, sunca, ananas, kiwi
				</div>
			</div>
		</div>			
	</div>
	<div id="shadow_admin" class="shadow">
		<div class="border">
			<div id="admin">
				<div class="title">
					<% if (!logat) { %>
						Admin Login
					<% } else { %>
						Administrator
					<% } %>
				</div>
				<% if (!logat) { %>
					<div id="loginForm">
						<FORM METHOD=POST ACTION="SaveName.jsp" style="margin-top:18px;">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>User Name:&nbsp;</td>
									<td><input type="text" size="20" name="username" />
								</tr>
								<tr>
									<td>Password: </td>
									<td><input type="password" size="20" name="password" />
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Submit" style="margin-top:5px;"/></td>
								</tr>
							</table>
						</FORM>
					</div>
				</div>			
			<% } else { %>
				Hello, <%= session.getAttribute( "theName" ) %>!
				<br />
				<br />
								
				<form method="post" action="ProcOrders.jsp" style="display:inline;">
					   <input type="submit" value="Processed orders" class="admin_buttons" style="width:140px;"/>
				</form>	
					
				<form method="post" action="UnprocOrders.jsp" style="display:inline;">	
					   <input type="submit" value="Unprocessed orders" class="admin_buttons" style="width:140px;"/>
				</form>
				
				<form method="post" action="AllOrders.jsp" style="display:inline;">
					  <input type="submit"  value="Visualize All Orders" class="admin_buttons" style="width:140px;"/>
				</form>	  
				
				<form method="post" action="LogOut.jsp" style="display:inline;">
					<input type="submit" value="Log out" class="admin_buttons" style="width:140px;" />
				</form>
			<% } %>
		</div>
	</div>
</div>