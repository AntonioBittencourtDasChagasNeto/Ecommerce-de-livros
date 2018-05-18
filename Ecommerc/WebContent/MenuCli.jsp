<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Login.css">
<title>Menu</title>

<style>
* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-ms-box-sizing: border-box;
	box-sizing: border-box;
}

html {
	font-family: sans-serif;
	-ms-text-size-adjust: 100%;
}

.menu {
	width: 1600px;
	height: 50px;
	background-color: #0411f7;
	margin-top: -16px;
	margin-left: -8px;
	z-index: 1;
}

.menu ul {
	font: 16px arial;
	list-style: none;
	position: relative;
	z-index: 1;
}

.menu ul li {
	width: 150px;
	float: left;
	z-index: 1;
}

.menu a {
	padding: 15px;
	display: block;
	text-decoration: none;
	text-align: center;
	background-color: blue;
	color: white;
	z-index: 1;
}

.menu ul ul {
	position: absolute;
	visibility: hidden;
	z-index: 1;
}

.menu ul li:hover ul {
	visibility: visible;
	z-index: 1;
}

.menu a:hover {
	background-color: #6a71f2;
	color: #555;
	z-index: 1;
}

.menu ul ul li {
	float: none;
	border-bottom: solid 1px #ccc;
	z-index: 1;
}

.menu ul ul li a {
	background-color: #6a71f2;
	z-index: 1;
}

.Title {
	font-size: 30px;
}
</style>
</head>
<body>

	<div class="menu">
		<ul>
			<li><a href="#"> Login </a>
				<ul>
					<li><a href="Login.jsp"> Entrar </a></li>
					<li><a href="CadastrarCliente.jsp"> Cadastrar-se</a></li>
				</ul></li>
			<li>
				<form action="/Ecommerc/ConsultarLivroCliente" method="post">
					<button type="submit" name="operacao" id="botao"
						value="VisualizarCliente">Confirmar</button>
				</form>
			</li>

			<li><a href="Carrinho.jsp"> Carrinho </a></li>
		</ul>
	</div>
</body>
</html>