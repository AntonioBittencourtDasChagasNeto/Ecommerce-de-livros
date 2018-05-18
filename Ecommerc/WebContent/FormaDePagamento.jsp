<%@page import="ecl.dominio.Pedidos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.dominio.ItensPedido"%>
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
<%@ page import="ecl.dominio.Categoria"%>
<%@ page import="ecl.dominio.Cartao"%>
<%@ page import="ecl.dominio.Cupom"%>
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
	margin-left: 170px;
	top: 100px;
}

table {
	width: 1000px;
	_width: 1500px;
}

th, td {
	text-align: left;
	padding: 8px;
	width: 800px;
	_width: 1000px;
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

<title>Escolha um endereço de entrega</title>
<script language="javascript">
function preparar()
{	
	var n3 = parseFloat(document.getElementById("total").innerHTML);
	var n2 = parseFloat(document.getElementById("desconto").innerHTML);
	var n1 = parseFloat(document.getElementById("troco").innerHTML);
	document.getElementById("Desconto").value = n2;
	document.getElementById("Troco").value = n1;
	document.getElementById("Total").value = n3;
}
</script>
<script language="javascript">
function teste2(obj,value)
{	
	if (obj.checked == true) 
	{
			var n4 = parseFloat(value);
			var n3 = parseFloat(document.getElementById("total").innerHTML);
			var n2 = parseFloat(document.getElementById("desconto").innerHTML);
			document.getElementById("desconto").innerHTML = n2 + n4;
			if(n3 < parseFloat(document.getElementById("desconto").innerHTML))
				{
					var n1 = parseFloat(document.getElementById("desconto").innerHTML);
					document.getElementById("troco").innerHTML = n1 - n3;
				}else
					{
					document.getElementById("troco").innerHTML = 0;
					}
	}else
		{
		var n4 = parseInt(value);
		var n2 = parseInt(document.getElementById("desconto").innerHTML);
		var n3 = parseFloat(document.getElementById("total").innerHTML);
		document.getElementById("desconto").innerHTML = n2 - n4;
		if(n3 < parseFloat(document.getElementById("desconto").innerHTML))
		{
			var n1 = parseFloat(document.getElementById("desconto").innerHTML);
			document.getElementById("troco").innerHTML = n1 - n3;
		}else
			{
			document.getElementById("troco").innerHTML = 0;
			}
		}
	
}
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="MenuCli.jsp" />

		<%
	Result resultado = new Result();
	Object obj = new Object();
	obj = request.getAttribute("resultado");
	resultado = Result.class.cast(obj);

	List <Pedidos> itens = new ArrayList<Pedidos>();
	List <EntidadeDominio> entidades = resultado.getEntidades();
	for(EntidadeDominio s: entidades){
	Pedidos item = (Pedidos)s;
	itens.add(item);
	}
	request.removeAttribute("resultado");
	%>
		<h2>Carrinho</h2>
		<nav class="a">
		<div class="a">
			<h1>Desconto</h1>
			<strong id="desconto">0</strong>
		</div>
		<div class="a">
			<h1>Total</h1>
			<%
	Pedidos pedidos = new Pedidos();
		Object obj2 = new Object();
		double Total = 0;
		obj2 = request.getSession().getAttribute("Carrinho");
if(obj2 != null)
{
	pedidos = Pedidos.class.cast(obj2);
	for(ItensPedido s: pedidos.getItens()){
		Double margem = (s.getCusto()*(s.getMargem()/100));
		Double Preco = s.getCusto() + margem;
		Total = Total + ((Preco)*s.getQuantidade());
	}
}
%>
			<strong id="total"><%=Total%></strong>
		</div>
		<div class="a">
			<h1>Troco</h1>
			<strong id="troco">0</strong>
		</div>
		</nav>
		<article>
		<div>
			<form action="NovoCartao.jsp" method="post">
				<p>
					<input type="submit" class="button" value="    Novo cartão">
				</p>
			</form>
		</div>
		<form action="/Ecommerc/FinalizarCompra" method="post">
			<table>
				<tr>
					<th>Número no cartão</th>
					<th>Nome impresso</th>
					<th>Bandeira</th>
					<th>Código de segurança</th>
					<th>Escolher?</th>
				</tr>
				<%
  for(Cartao cartao : itens.get(0).getCartoes())
  {
	  %>
				<tr>
					<td><%=cartao.getNumero()%></td>
					<td><%=cartao.getNomeCartao()%></td>
					<td><%=cartao.getBandeira().getNome()%></td>
					<td><%=cartao.getCodigoDeSeguranca()%></td>
					<td><input type='checkbox' name="cartao" id="cartao"
						value="<%=cartao.getId()%>" /></td>
				</tr>
				<%
  }
  
  %>
			</table>
			<table>
				<tr>
					<th>Cupom de troca</th>
					<th>valor</th>
					<th>Escolher?</th>
				</tr>
				<%
  for(Cupom cupom : itens.get(0).getCupons())
  {
	  %>
				<tr>
					<td><%=cupom.getId()%></td>
					<td><%=cupom.getValor()%></td>
					<td><input type='checkbox' name="cupom" id="cupom"
						value="<%=cupom.getId()%>"
						onclick="teste2(this,<%=cupom.getValor()%>)" /></td>
				</tr>
				<%
  }
  
  %>
			</table>
			<h2>Cupom Promocional</h2>
			<input type='text' name="CupomPromocional" />
			<button type="submit" name="operacao" value="EntrarEstoque"
				class="button">Verificar Cupom</button>
			<input type="hidden" name="Total" id="Total" value=""></input> <input
				type="hidden" name="Desconto" id="Desconto" value=""></input> <input
				type="hidden" name="Troco" id="Troco" value=""></input>
			<button type="submit" name="operacao" value="FinalizarCompra"
				class="button" onclick="preparar()">Finalizar Compra</button>
		</form>
		</article>
	</div>

</body>
</html>