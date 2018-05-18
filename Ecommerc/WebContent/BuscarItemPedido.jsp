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
	margin-left: 250px;
	top: 100px;
}

table {
	width: 1300px;
	_width: 1300px;
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

div.a {
	position: absolute;
	top: 650px;
}
</style>

<title>Buscar item Pedido</title>
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
		<div class="menu">
			<ul>
				<li><a href="#"> Login </a></li>
				<li><a href="#"> Livros </a>
					<ul>
						<li><a href="CadastrarLivro.jsp" name="CadastrarLivro">
								Cadastrar Livro </a></li>
						<li><a href="ConsultarLivro.jsp" name="ConsultarLivro">
								Consultar Livro </a></li>
					</ul></li>
			</ul>

		</div>


		<h2>Consultar itens Troca</h2>
		<nav class="a">
		<div>
			<div>
				<p>Código do pedido</p>
				<input type="text" name="CodigoDeBarras" />
			</div>
		</div>

		<div>
			<div>
				<p>Código do item</p>
				<input type="text" name="item" />
			</div>
		</div>

		<div>
			<div>
				<p>Titulo</p>
				<input type="text" name="Titulo" />
			</div>
		</div>

		<div>
			<div>
				<p>SubTotal</p>
				<input type="text" name="Valor" />
			</div>
		</div>

		<div>
			<div>
				<p>Quantidade</p>
				<input type="text" name="Quantidade" />
			</div>
		</div>

		<div>
			<div>
				<p>CPF do cliente</p>
				<input type="text" name="CPF" />
			</div>
		</div>

		<div>
			<div>
				<p>Status da compra</p>
				<select name="Status" id="status" class="div-select">
					<option value=""></option>
					<option value="">EM PROCESSAMENTO</option>
					<option value="">EM TRANSITO</option>
					<option value="">EM TROCA</option>
					<option value="">TROCADO</option>
					<option value="">ENTREGUE</option>
				</select>
			</div>
		</div>
		</nav>
		<article>
		<table>
			<tr>
				<th>Código do pedido</th>
				<th>Código do item</th>
				<th>Titulo</th>
				<th>Quantidade</th>
				<th>Subtotal</th>
				<th>CPF do cliente</th>
				<th>Status</th>
				<th>Opções</th>
			</tr>
			<tr>
				<td>Código1</td>
				<td>Item1</td>
				<td>Titulo1</td>
				<td>Quantidade1</td>
				<td>Subtotal1</td>
				<td>CPF1</td>
				<td><select name="Status" id="status" class="div-select">
						<option value=""></option>
						<option value="">EM PROCESSAMENTO</option>
						<option value="">EM TRANSITO</option>
						<option value="" selected>EM TROCA</option>
						<option value="">TROCADO</option>
						<option value="">ENTREGUE</option>
				</select></td>
				<td><input type="button" name="operacao" value="ALTERAR"
					class="button"></td>
			</tr>
			<tr>
				<td>Código2</td>
				<td>Item2</td>
				<td>Titulo2</td>
				<td>Quantidade2</td>
				<td>Subtotal2</td>
				<td>CPF2</td>
				<td><select name="Status" id="status" class="div-select">
						<option value=""></option>
						<option value="">EM PROCESSAMENTO</option>
						<option value="">EM TRANSITO</option>
						<option value="" selected>EM TROCA</option>
						<option value="">TROCADO</option>
						<option value="">ENTREGUE</option>
				</select></td>
				<td><input type="button" name="operacao" value="ALTERAR"
					class="button"></td>
			</tr>

		</table>
		</article>
	</div>
	<div class="a">
		<input type="button" name="operacao" value="Filtrar" class="button">
	</div>
</body>
</html>