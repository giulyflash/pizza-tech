<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<%@page import="client.DBImplementation"%>
<%@page import="client.Ingredient"%>
<%@page import="client.Pizza"%><html>
<head>
		<style type="text/css">
		#main_panel
		{
			background-color : #74E600;
			width : 70%;
			margin : 0 auto;
			border-radius : 1em;
			-moz-border-radius : 1em;
			padding-top : 3px;
			height : 800px;
		}
		
		#logo_panel
		{
			background-color : white;
			height : 100px;
			border-radius : 1em;
			-moz-border-radius : 1em;
			margin-left : 5px;
			margin-right : 5px;
		}
		
		#navigation_panel 
		{
			text-align : center;
			margin-top : 10px;
			margin-left : 5%;
			width : 80%;
		}
		
		ul#navigation_bar
		{
			list-style-type : none;
			display : inline;
			padding-left : 0px;
		}
		
		ul#navigation_bar li
		{
			font-weight : bold;
			cursor-style : 
			display : inline;
			border-style : solid;
			border-width : 4px;
			border-color : #74E600;
			background-color : #FF7A00;
			color : white;
			width : 100px;
			height : 20px;
			float : left;
			margin-left : 3px;
			cursor : pointer;
			border-radius : 0.5em;
			-moz-border-radius : 0.5em;
			font-size : 14px;
		}
		
		ul#navigation_bar li:hover
		{
			border-color : #FF7A00;
		}
		
		#shadow_noutati
		{
			float : right;
			width : 30%;
			height : 300px;
			margin-right : 10px;
			margin-top : 10px;
		}
		
		.shadow
		{
			color : #4B9500;
			border-bottom :  solid;
			border-right :  solid;
			border-width : 2px;
			border-radius : 1em;
			-moz-border-radius : 1em;
		}
		
		.border 
		{
			border : solid;
			border-width : 1px;
			border-radius : 1em;
			-moz-border-radius : 1em;			
		}
		
		#noutati
		{
			height : 100%;
			color : black;
			background-color : white;
			text-align : center;
			border-radius : 1em;
			-moz-border-radius : 1em;
			height:300px;
		}
		
		#shadow_continut
		{
			float : left;
			width : 65%;
			text-align : center;
			margin-left : 10px;
		}
		
		#continut
		{
			color : black;
			background-color : white;
			border-radius : 1em;
			-moz-border-radius : 1em;
			height : 600px;
		}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Pizzeria C11E4</title>
</head>
<body>
		<div id="main_panel">
			<div id="logo_panel">
				<br />
				Aici o sa fie o poza<br />
				faina facuta de Vali cu un bucatar...
			</div>			
			<div id="navigation_panel">
				<ul id="navigation_bar">
					<li>
						Acasa
					</li>
					<li>
						Produse
					</li>
					<li>
						DoItYourself
					</li>
					<li>
						Comanda
					</li>
					<li>
						Contact
					</li>
				</ul>
			</div>
			<br />
			<br />
			<div id="shadow_continut" class="shadow">
				<div class="border">
					<div id="continut">
						<%=DBImplementation.getInstance(getServletContext()).getAllOrders().get( 0 ).getPrice()%>>
						
					</div>
				</div>
			</div>
			<div id="shadow_noutati" class="shadow">
				<div class="border">
					<div id="noutati">
						Noutati
					</div>			
				</div>
			</div>
		</div>
</body>
</html>