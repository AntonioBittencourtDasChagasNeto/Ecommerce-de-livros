<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.core.aplicacao.Result"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecl.dominio.EntidadeDominio"%>
<%@ page import="ecl.dominio.Categoria"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.dominio.Livro"%>
<%@ page import="ecl.core.aplicacao.Result"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecl.dominio.EntidadeDominio"%>
<%@ page import="ecl.dominio.Pedidos"%>
<%@ page import="ecl.dominio.ItensPedido"%>
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

<title>Carrinho</title>
<script type="text/javascript">
	function id( el ){
		return document.getElementById( el );
	}
	function menos( id_qnt,id_sub,id_preco ) 
	{
		var qnt = parseInt( id( id_qnt ).value );
		if( qnt > 0 )
			{
			id( id_qnt ).value = qnt - 1; 
		var n1 = parseInt(document.getElementById(id_preco).innerHTML);
		  var n2 = parseInt( id( id_qnt ).value );
		  var n3 = parseInt(document.getElementById("total").innerHTML)
		document.getElementById(id_sub).innerHTML = n1 * n2;
		  document.getElementById("total").innerHTML = n3 - n1;
			}
	} 
	function mais( id_qnt,id_sub,id_preco )
	{
		id( id_qnt ).value = parseInt( id( id_qnt ).value ) + 1;
		 var n1 = parseInt(document.getElementById(id_preco).innerHTML);
		  var n2 = parseInt( id( id_qnt ).value );
		  var n3 = parseInt(document.getElementById("total").innerHTML);
		document.getElementById(id_sub).innerHTML = n1 * n2;
		document.getElementById("total").innerHTML = n3 + n1;
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
			<%
Pedidos resultado = new Pedidos();
Object obj = new Object();
double Total = 0;
obj = request.getSession().getAttribute("Carrinho");
if(obj != null)
{
	resultado = Pedidos.class.cast(obj);
	for(ItensPedido s: resultado.getItens()){
		Double margem = (s.getCusto()*(s.getMargem()/100));
		Double Preco = s.getCusto() + margem;
		Total = Total + ((Preco)*s.getQuantidade());
	}
}

%>
			<strong id="total"><%=Total%></strong>
		</div>
		</nav>
		<article>
		<table>
			<tr>
				<th>Item</th>
				<th>Preço</th>
				<th>Quantidade</th>
				<th>Subtotal</th>
			</tr>
			<%
	int contador = 0;
	obj = request.getSession().getAttribute("Carrinho");
	if(obj != null)
	{
		resultado = Pedidos.class.cast(obj);
		for(ItensPedido s: resultado.getItens()){
			Double margem = (s.getCusto()*(s.getMargem()/100));
			Double Preco = s.getCusto() + margem;
	%>
			<tr>
				<td><img
					src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
					<%=s.getLivro().getTitulo()%></td>
				<td id="preco3"><%=Preco%></td>
				<td><form action="/Ecommerc/Carrinho" method="post">

						<button type="submit" name="operacao" value="DiminuirCarrinho">-</button>
						<input type="text" name="quantidade" id="quantidade3"
							value="<%=s.getQuantidade()%>" size="1" readonly="readonly" /> <input
							type="hidden" name="Index" value="<%=contador%>"></input>
						<button type="submit" name="operacao" value="AumentarCarrinho">+</button>
						<button type="submit" name="operacao" value="Remover">Remover</button>
					</form></td>
				<td id="subtotal3"><%=(Preco)*s.getQuantidade()%></td>
			</tr>
			<%
  	contador++;
 										 }//for
	}//if
	%>

		</table>
		</article>
	</div>
	<form action="/Ecommerc/FreteEndereco" method="post">
		<button type="submit" name="operacao" value="VisualizarEndereco"
			class="button">Finalizar Compra</button>
	</form>
</body>
</html>