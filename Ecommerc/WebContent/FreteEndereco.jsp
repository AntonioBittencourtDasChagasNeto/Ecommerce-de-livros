<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<%@ page import="ecl.dominio.Endereco"%>
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
function teste2(obj)
{	
	if (obj.checked == true) 
	{
		if (obj.id == "escolha1")
		{
			document.getElementById("escolha2").disabled = true;
			document.getElementById("escolha3").disabled = true;
			document.getElementById("frete").innerHTML = 13;
			document.getElementById("total").innerHTML = 113;
		}
		else if (obj.id == "escolha2")
		{
			document.getElementById("escolha1").disabled = true;
			document.getElementById("escolha3").disabled = true;
			document.getElementById("frete").innerHTML = 10;
			document.getElementById("total").innerHTML = 110;
		}
		else if (obj.id == "escolha3")
		{
			document.getElementById("escolha1").disabled = true;
			document.getElementById("escolha2").disabled = true;
			document.getElementById("frete").innerHTML = 12;
			document.getElementById("total").innerHTML = 112;
		}	
	}
	else
	{
		document.getElementById("escolha1").disabled = false;
		document.getElementById("escolha2").disabled = false;
		document.getElementById("escolha3").disabled = false;
		document.getElementById("frete").innerHTML = 0;
		document.getElementById("total").innerHTML = 100;
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

			<h1>Frete</h1>
			<%
 	Result resultado = new Result();
 	Object obj = new Object();
 	obj = request.getAttribute("resultado");
 	List <Endereco> enderecos = new ArrayList<Endereco>();
 	List <Pedidos> pedido = new ArrayList<Pedidos>();
 	if(obj != null)
 	{
 	resultado = Result.class.cast(obj);

 	
 	List <EntidadeDominio> entidades = resultado.getEntidades();
 	for(EntidadeDominio s: entidades){

 	Endereco end = (Endereco)s;
 	enderecos.add(end);

 	}
 	request.removeAttribute("resultado");
 %>

			<strong id="frete"><%=enderecos.get(0).getFrete()%></strong>
			<%
 	}else
 	{
 		%>
			<strong id="frete">0</strong>
			<%
 	}
%>
		</div>
		<div class="a">
			<h1>Carrinho</h1>
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
		</nav>
		<article>
		<div>
			<form action="NovoEndereco.jsp" method="post">
				<p>
					<input type="submit" class="button" value="  Novo Endereco">
				</p>
			</form>

		</div>
		<table>
			<tr>
				<th>Logradouro</th>
				<th>Número</th>
				<th>Bairro</th>
				<th>CEP</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>País</th>
				<th>Escolher?</th>
			</tr>
			<%
  for(int i = 0;i < enderecos.size();i++)
  {
  %>
			<tr>
				<td><%=enderecos.get(i).getLogradouro() %></td>
				<td><%=enderecos.get(i).getNumero() %></td>
				<td><%=enderecos.get(i).getBairro() %></td>
				<td><%=enderecos.get(i).getCep() %></td>
				<td><%=enderecos.get(i).getCidade() %></td>
				<td><%=enderecos.get(i).getEstado() %></td>
				<td><%=enderecos.get(i).getPais() %></td>
				<td><form action="/Ecommerc/FreteEndereco" method="post">
						<input type="hidden" name="Id"
							value="<%=enderecos.get(i).getId()%>"></input> <input
							type="hidden" name="Cep" value="<%=enderecos.get(i).getCep()%>"></input>
						<button type="submit" name="operacao" value="CalcularFrete"
							class="button">Calcular Frete</button>
					</form></td>
				<%
  }

    %>
			</tr>
		</table>
		</article>
	</div>
	<form action="/Ecommerc/FormaDePagamento" method="post">

		<button type="submit" name="operacao" value="FormaDePagamento"
			class="button">Próximo</button>

	</form>
</body>
</html>