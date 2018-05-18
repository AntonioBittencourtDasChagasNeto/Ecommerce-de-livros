<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#grafico {
	min-width: 310px;
	max-width: 800px;
	height: 400px;
	margin: 0 auto
}

.menu {
	width: 1600px;
	height: 50px;
	background-color: #0411f7;
	margin-top: -16px;
	margin-left: -8px;
}

.menu ul {
	font: 16px arial;
	list-style: none;
	position: relative;
}

.menu ul li {
	width: 150px;
	float: left;
}

.menu a {
	padding: 15px;
	display: block;
	text-decoration: none;
	text-align: center;
	background-color: blue;
	color: white;
}

.menu ul ul {
	position: absolute;
	visibility: hidden;
}

.menu ul li:hover ul {
	visibility: visible;
}

.menu a:hover {
	background-color: #6a71f2;
	color: #555;
}

.menu ul ul li {
	float: none;
	border-bottom: solid 1px #ccc;
}

.menu ul ul li a {
	background-color: #6a71f2;
}
</style>

</head>
<body>
	<jsp:include page="Menu.jsp" />
	<h1>Dominios</h1>
	<select name="Status" id="status" class="div-select">
		<option value=""></option>
		<option value="">EDITORA</option>
		<option value="">AUTOR</option>
		<option value="" selected>BANDEIRA CARTÃO</option>
		<option value="">CATEGORIA LIVRO</option>
		<option value="">CATEGORIA JUSTIFICATIVA INATIVAR</option>
		<option value="">CATEGORIA JUSTIFICATIVA ATIVAR</option>
	</select>
	<button type="submit" name="operacao" value="CadastrarDominio"
		class="button">Adicionar</button>
	<div>
		<h2>Precificação</h2>
		Nome <input type="text" name="Nome"> Margem de lucro <input
			type="number" name="Nome">
		<button type="submit" name="operacao" value="CadastrarDominio"
			class="button">Adicionar</button>
	</div>


</body>
</html>