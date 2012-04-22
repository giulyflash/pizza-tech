
<%@page import="pizzatech.dbaccess.DBInterface"%>
<%@page import="pizzatech.model.Ingredient"%>
<%@page import="pizzatech.model.Pizza"%>
<%@page import="java.util.*"  %>
<%@page import="pizzatech.dbaccess.DBImplementation" %>
<%@page import="pizzatech.model.MiscPizzaHelper"%>

<% 
//session.setAttribute("Ingreds",null);
ArrayList<Pizza> pizzaPerm;
if (session.getAttribute("allPizzaCache")==null)
{
pizzaPerm=DBImplementation.getInstance().getPermanentPizzas();
ArrayList<Pizza> pizzaSave=(ArrayList<Pizza>)session.getAttribute("save");
session.setAttribute("allPizzaCache",pizzaPerm);
if (pizzaSave!=null)
pizzaPerm.addAll(pizzaSave);
}
else
pizzaPerm=(ArrayList<Pizza>)session.getAttribute("allPizzaCache");


String sessionSet=request.getParameter("Ingred");
if (request.getParameter("act")!=null)
if (request.getParameter("act").equals("save"))
{
	if (session.getAttribute("Ingreds")==null)
	{
		out.print("Pizza asta e goala,nu o putem salva");			
	}
	else
	{
	if (session.getAttribute("save")==null)
	{		
		ArrayList<Pizza> p=new ArrayList<Pizza>();
		Pizza pizza=((Pizza)session.getAttribute("Ingreds"));
		if (MiscPizzaHelper.pizzaSeeker(request.getParameter("pizzaName"),pizzaPerm))
			out.print("Pizza cu acest nume exista deja");
		else
		{
		pizza.setName(request.getParameter("pizzaName"));
		pizza.setDescription("");
		if (pizza.getPrice()<0.0001)
		out.print("Pizza asta e goala,nu o putem salva");
		else
		{
		p.add(pizza);
		((ArrayList<Pizza>)session.getAttribute("allPizzaCache")).add(pizza);
		pizza.setIsPermanent(false);		
		session.setAttribute("save",p);
		session.setAttribute("Ingreds",null);
		out.print("Pizza a fost salvata.");
		return;
		}
		}
	}
	else
	{
		ArrayList<Pizza> p=(ArrayList<Pizza>)session.getAttribute("save");
		Pizza pizza=((Pizza)session.getAttribute("Ingreds"));
		if (MiscPizzaHelper.pizzaSeeker(request.getParameter("pizzaName"),pizzaPerm))
			out.print("Pizza cu acest nume exista deja");
		else
		{
		pizza.setName(request.getParameter("pizzaName"));
		pizza.setDescription("");
		pizza.setIsPermanent(false);
		if (pizza.getPrice()<0.0001)
		out.print("Pizza asta e goala,nu o putem salva");
		else
		{
		p.add(pizza);
		((ArrayList<Pizza>)session.getAttribute("allPizzaCache")).add(pizza);
		session.setAttribute("save",p);
		session.setAttribute("Ingreds",null);
		out.print("Pizza a fost salvata.");
		return;
		}
		}
	}
}
}
if (sessionSet!=null)
{
if (request.getParameter("act").equals("add"))
{
	if (session.getAttribute("Ingreds")!=null)
	{
	Pizza al = ((Pizza)(session.getAttribute("Ingreds")));
	al.addIngredient(DBImplementation.getInstance().getIngredientById(new Integer(sessionSet)));	
	session.setAttribute("Ingreds",al );
	}
	else
	{
		Pizza al=new Pizza();
		al.addIngredient(DBImplementation.getInstance().getIngredientById(new Integer(sessionSet)));		
		session.setAttribute("Ingreds",al );
	}
}
else
	{
	Pizza al = ((Pizza)(session.getAttribute("Ingreds")));
	Iterator<Ingredient> ing = al.getIngredients().iterator();	
    	while( ing.hasNext() ) {		
		Ingredient crt = ( Ingredient ) ing.next();
		if (crt.getId()==new Integer(sessionSet))
		{
		al.removeIngredient(crt);
		break;
		}
    	}
		
    session.setAttribute("Ingreds",al );
	}
}
 %>

<div style="background-color : white; width : 80%; border-radius : 1em; -moz-border-radius : 1em; margin : 0 auto; padding-bottom : 10px; padding-left : 10px;">
<center>
<table style="text-align: left; width: 70%; height: 289px;"
 border="0" cellpadding="2" cellspacing="2">
  <tbody>
    <tr>
      
    </tr>
    <tr>
      <td style="width: 301px; height: 252px; vertical-align: top;">
<% Pizza ingred=(Pizza)session.getAttribute("Ingreds");   %>
      <form method="post" action="<%=request.getRequestURL() %>" name="asta">
        <select name="Ingred">
<% ArrayList<Ingredient> ingreds=DBImplementation.getInstance().getIngredients();
for (int i=0;i<ingreds.size();i++)	
{
	Ingredient crt=ingreds.get(i);	
	%>
<option value="<%=crt.getId()%>"><%=crt.getName()%>(<%=Math.round(crt.getPrice()*100.0)/100.0 %>)</option>
<% }
	%>	
        </select>
        <input type="hidden" name="act" value="add" >
        <input type="submit" value="Adauga" >
      </form>
      <table style="text-align: left; width: 460px;">
        <tbody>

<%
   Double pretTotal=0.0;
   if (ingred!=null)
   {
   Iterator<Ingredient> ing = ingred.getIngredients().iterator();	
   while( ing.hasNext() ) {		
		Ingredient crt = ( Ingredient ) ing.next();
	%>
          <tr>
            <td style="width: 341px;"><%=crt.getName() %></td>
            <td style="width: 52px;"><%=(Math.round((crt.getPrice())*100.0))/100.0 %></td>
            <td style="width: 52px;"><form method="post" action="<%=request.getRequestURL() %>" ><input type="hidden" name="Ingred" value="<%=crt.getId() %>"><input type="hidden" name="act" value="remove"><input type="submit" value="sterge"></form></td>
          </tr>
<% }
	%>	

          <tr>
            <td style="width: 341px;">Pret total:</td>
            <td style="width: 52px;"><%=(Math.round(ingred.getPrice()*100.0))/100.0 %></td>
          </tr>
        </tbody>
      </table>
      <br>
      </td>
    </tr>
  </tbody>
</table>
<%} %>
</center>
<form method="post" action="<%=request.getRequestURL() %>" >Salveaza pizza cu numele:<input type="text" name="pizzaName" value=""><input type="hidden" name="act" value="save"><input type="submit" value="OK"></form>
</div>