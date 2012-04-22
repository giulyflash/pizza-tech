<%@page import="pizzatech.model.Pizza"%>
<%@page import="pizzatech.dbaccess.DBImplementation"%>
<%@page import="pizzatech.dbaccess.DBInterface"%>
<%@page import="java.util.Date"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template'%>

<template:insert template='Template.jsp'>
	<template:put name='title' content='Pizeria C11E4' direct='true' />
	<template:put name="header"
		content='<link rel="stylesheet" type="text/css" href="css/index.css"'
		direct='true' />
	<template:put name='content' content='contents/IndexContent.jsp' />
</template:insert>
<%
	DBInterface db = DBImplementation.getInstance(getServletContext());

	Pizza p = new Pizza();
	p.setName("a");
	p.setDate("b");
	p.setDescription("b");
	p.setIsPermanent(false);
	p.setLink("sds");

	db.addPizza(p);
%>

