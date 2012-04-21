<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<%@page import="client.DBInterface"%><html>
<head>
		<template:get name='header'/>
		<style type="text/css">
		#main_panel
		{
			background-color : #CC6600;
			width : 900px;
			margin : 0 auto;
			border-radius : 1em;
			-moz-border-radius : 1em;
			padding-top : 3px;
			height : 850px;
			min-width : 700px;
		}
		
		#logo_panel
		{
			height : 180px;
			border-radius : 1em;
			-moz-border-radius : 1em;
			margin-left : 5px;
			margin-right : 5px;
			background : url('pics/bucatar.png') no-repeat white;
			background-size: 100%;
		}
		
		#navigation_panel 
		{
			text-align : center;
			margin-top : 10px;
			width : 580px;
			margin-left : auto;
			margin-right : auto;
			-moz-user-select: none;
		}
		
		ul#navigation_bar
		{
			list-style-type : none;
			display : inline;
		}
		
		ul#navigation_bar li
		{
			font-weight : bold;
			cursor-style : 
			display : inline;
			border-style : solid;
			border-width : 4px;
			border-color : #CC6600;
			background-color : #FF6633;
			padding-top : 2px;
			color : white;
			width : 105px;
			height : 25px;
			float : left;
			margin-left : 3px;
			cursor : pointer;
			border-radius : 0.5em;
			-moz-border-radius : 0.5em;
			font-size : 14px;
		}
		
		ul#navigation_bar li:hover
		{	
			border-color : #FF6633;
		}
		
		#shadow_noutati
		{
			float : right;
			width : 300px;
			height : 300px;
			margin-right : 10px;
			margin-top : 10px;
		}
		
		.shadow
		{
			color : #663300;
			//border-bottom :  solid;
			//border-right :  solid;
			border-width : 2px;
			border-radius : 1em;
			-moz-border-radius : 1em;
		}
		
		.border 
		{
			//border : solid;
			border-width : 2px;
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
			selection { background: none; }
			-moz-selection { background: none; }
		}
		
		#shadow_admin {
			margin-top : 40px;
			width : 300px;
		}
		
		#admin
		{
			height : 100%;
			color : black;
			background-color : white;
			text-align : center;
			border-radius : 1em;
			-moz-border-radius : 1em;
			height:150px;
		}
		
		#shadow_continut
		{
			float : left;
			width : 570px;
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
		
		#loginForm
		{
			margin-top : 5px;
			margin-left : 10px;
		}
		
		.title
		{
			background-color : #CC9966;
			border-top-left-radius : 1em;
			-moz-border-radius-topleft : 1em;
			border-top-right-radius : 1em;
			-moz-border-radius-topright : 1em;
			font-weight: bold; 
		}
		
		.thumb
		{
			height : 80px;
			float : left;
			margin-top : 10px;
			margin-left : 10px;
		}
		
		.pizza_details
		{
			padding-top : 10px;
			padding-right : 10px;
			width : 150px;
			float : right;
			text-align : left;
		}
		
		.pizza:after
		{
			content: ".";
			display: block;
			height: 0;
			clear: both;
			visibility: hidden;
		}
		#dimm_panel
		{
			height : 100%;
			width : 100%;
			opacity : 0.2;
			z-index : 0;
			display : none;
		}
		
		#photo_viewer
		{
			opacity : 1;
			-moz-opacity : 1;
			background-color : white;
			border-style : solid;
			border-width : 0.5px;
			width : 300px;
			height : 230px;
			position : absolute;
			top : 40%;
			left : 40%;
			border-radius : 1em;
			-moz-border-radius : 1em;
		}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><template:get name='title'/></title>
</head>
<body>

		<div id="main_panel">
			<div id="logo_panel">
				<br />
				<br />			
			</div>			
			<div id="navigation_panel">
				<ul id="navigation_bar">
					<li onclick="window.location.href = 'Index.jsp'">
						Acasa
					</li>
					<li onclick="window.location.href = 'Produse.jsp'">
						Produse
					</li>
					<li onclick="window.location.href = 'DoIt.jsp'">
						DoItYourself
					</li>
					<li onclick="window.location.href = 'Comanda.jsp'">
						Comanda
					</li>
					<li onclick="window.location.href = 'Contact.jsp'">
						Contact
					</li>
				</ul>
			</div>
			<br />
			<br />
			
			<template:get name='content'/>
		</div>
</body>
</html>