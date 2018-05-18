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
<%@ page import="ecl.dominio.Pedidos"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table, td, th {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
	width: 1000px;
}

td {
	height: 50px;
	vertical-align: bottom;
}

p {
	position: absolute;
	left: 850px;
	top: 50px;
}

.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	width: 450px;
	padding: 15px 20px;
	text-align: center;
	text-decoration: none;
	font-size: 14px;
	cursor: pointer;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="MenuCli.jsp" />
	<%
	Result resultado = new Result();
	Object obj = new Object();
	obj = request.getAttribute("resultado");
	resultado = Result.class.cast(obj);
	ItensPedido pedido = new ItensPedido();
	List <EntidadeDominio> entidades = resultado.getEntidades();
	for(EntidadeDominio s: entidades){
	ItensPedido item = (ItensPedido)s;
	pedido = item;
	}
	request.removeAttribute("resultado");
	%>
	<div>
		<h2>
			<img
				src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
		</h2>
		<div>
			<%
    		Double margem = (pedido.getCusto()*(pedido.getMargem()/100));
			Double Preco = pedido.getCusto() + margem;
    		%>
			<h1><%=pedido.getLivro().getTitulo()%></h1>
		</div>
		<div>
			<h3>
				Preço R$
				<%=Preco%></h3>
		</div>
		<div>Ficha técnica</div>
		<div>
			<form action="/Ecommerc/Carrinho" method="post">
				<input type="hidden" name="Id"
					value="<%=pedido.getLivro().getId()%>"></input> <input
					type="hidden" name="Custo" value="<%=pedido.getCusto()%>"></input>
				<input type="hidden" name="Margem" value="<%=pedido.getMargem()%>"></input>
				<input type="hidden" name="altura"
					value="<%=pedido.getLivro().getDimensoes().getAltura()%>"></input>
				<input type="hidden" name="largura"
					value="<%=pedido.getLivro().getDimensoes().getLargura()%>"></input>
				<input type="hidden" name="profundidade"
					value="<%=pedido.getLivro().getDimensoes().getProfundidade()%>"></input>
				<input type="hidden" name="peso"
					value="<%=pedido.getLivro().getDimensoes().getPeso()%>"></input> <input
					type="hidden" name="Titulo"
					value="<%=pedido.getLivro().getTitulo()%>"></input> <input
					type="hidden" name="id_estoque" value="<%=pedido.getIdEstoque()%>"></input>
				<p>
					<input type="submit" class="button" name="operacao"
						value="AdicionarCarrinho">
				</p>
			</form>

			</form>
		</div>
	</div>
	<table>
		<tr>
			<th></th>
			<th></th>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Código de barras</td>
			<td><%=pedido.getLivro().getCodigoBarras()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Autor</td>
			<td><%=pedido.getLivro().getAutor().getNome()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Título</td>
			<td><%=pedido.getLivro().getTitulo()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">ISBN</td>
			<td><%=pedido.getLivro().getISBN()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Páginas</td>
			<td><%=pedido.getLivro().getNumeroPaginas()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Edicão</td>
			<td><%=pedido.getLivro().getEdicao()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Editora</td>
			<td><%=pedido.getLivro().getEditora().getNome()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Ano</td>
			<td><%=pedido.getLivro().getAno()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Categoria</td>
			<%
     String categoria = "";
     for(Categoria cat : pedido.getLivro().getIdcategoria())
     {
    	 categoria = categoria + " , " + cat.getNome();
     }
     %>
			<td><%=categoria%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Altura</td>
			<td><%=pedido.getLivro().getDimensoes().getAltura()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Largura</td>
			<td><%=pedido.getLivro().getDimensoes().getLargura()%></td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Peso</td>
			<td><%=pedido.getLivro().getDimensoes().getPeso()%> KG</td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Profundidade</td>
			<td><%=pedido.getLivro().getDimensoes().getProfundidade()%> cm</td>

		</tr>
		<tr>
			<td style="background-color: powderblue;">Sinopse</td>
			<td><%=pedido.getLivro().getSinopse()%></td>

		</tr>
	</table>
</body>
</html>