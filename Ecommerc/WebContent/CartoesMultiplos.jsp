<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
div.container {
	width: 100%;
}

.menu {
	width: 1473px;
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

border-collapse
:
 
collapse
;

    
width
:
 
100%;
}
nav {
	position: absolute;
	left: auto;
	width: 100px;
	height: 120px;
	border: 3px solid blue;
}

article {
	position: absolute;
	margin-left: 170px;
	top: 100px;
}

table {
	width: 1500px;
	_width: 1500px;
}

th, td {
	text-align: left;
	padding: 8px;
	width: 1000px;
	_width: 1500px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}

img {
	width: 70px;
}

h1 {
	background-color: #4CAF50;
	color: white;
	text-align: center;
	width: 150px;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

div.a {
	position: relative;
	width: 150px;
	height: 200px;
}

.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	width: 150px;
	padding: 15px 20px;
	text-align: left;
	text-decoration: none;
	font-size: 14px;
	cursor: pointer;
}
</style>

<title>Cartões multiplos</title>
<script>
function myFunction() {
	
		document.getElementById("cartao").innerHTML = 80;
	var n1 = parseInt(document.getElementById("valor1").value);
	var n2 = parseInt(document.getElementById("valor2").value);
	var n3 = parseInt(document.getElementById("total").innerHTML);
	document.getElementById("cartao").innerHTML = n3 - (n1+n2);
	if(document.getElementById("cartao").innerHTML <0)
	{
		document.getElementById("valor1").value = 10;
		document.getElementById("valor2").value = 10;
		document.getElementById("cartao").innerHTML = 80;
		alert("Dados inválidos");
	}

}
</script>

</head>

<body>
	<div class="container">
		<jsp:include page="MenuCli.jsp" />


		<h2>Carrinho</h2>
		<nav class="a">
		<div class="a">
			<h1>Total</h1>
			<strong id="total">100</strong>
		</div>
		<div class="a">
			<h1>Falta pagar</h1>
			<strong id="cartao">80</strong>
		</div>
		</nav>
		<article>
		<table>
			<tr>
				<th>Número no cartão</th>
				<th>Nome impresso</th>
				<th>Bandeira</th>
				<th>Código de segurança</th>
				<th>Quanto vai pagar?</th>
			</tr>
			<tr>
				<td>Numerocartao1</td>
				<td>Nomeimpresso1</td>
				<td>Bandeira1</td>
				<td>Código1</td>
				<td><input type="number" name="valor" id="valor1" value="10"
					min="10" onblur="myFunction()"></td>
			</tr>
			<tr>
				<td>Numerocartao1</td>
				<td>Nomeimpresso1</td>
				<td>Bandeira1</td>
				<td>Código1</td>
				<td><input type="number" name="valor" id="valor2" value="10"
					min="10" onblur="myFunction()"></td>
			</tr>

		</table>
		</article>
	</div>
	<form action="ConsultarLivroCliente.jsp" method="post">
		<button type="submit" name="operacao" value="EntrarEstoque"
			class="button">Finalizar Compra</button>

	</form>
</body>
</html>