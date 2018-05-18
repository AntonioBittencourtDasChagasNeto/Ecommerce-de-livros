<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ecl.core.util.buscaCEP"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.dominio.Cliente"%>
<%@ page import="ecl.core.aplicacao.Result"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecl.dominio.EntidadeDominio"%>
<%@ page import="ecl.dominio.Categoria"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Menu do Cliente</title>

<style>
.col-md-12 {
	display: flex;
}

.col-md-8 {
	display: flex;
}

.col-md-4-2 {
	padding: 30px;
	margin-top: -20px;
}

fieldset {
	margin: 30px;
	border: 2px solid orange;
	border-radius: 10px;
	background-color: #f7f7f7;
	padding: 10px;
	width: 300px;
	height: 90px;
	text-align: left;
}

.Fieldset {
	margin: 30px;
	border: 2px solid orange;
	border-radius: 10px;
	background-color: #f7f7f7;
	padding: 10px;
	width: 300px;
	height: 120px;
	text-align: left;
}

legend {
	color: orange;
	font-size: 18px;
}

.a {
	font-size: 15px;
	text-decoration: none;
	color: #919191;
	margin-left: 10px;
}

.a:hover {
	color: blue;
}

.Fieldset2 {
	width: 700px;
	height: 550px;
	border-color: orange;
}

input {
	border: none;
	background-color: #f7f7f7;
	font-size: 17px;
	color: #7f7e7e;
}
</style>
</head>
<body>
	<%
	Result resultado = new Result();
	Object obj = new Object();
	obj = request.getAttribute("resultado");
	resultado = Result.class.cast(obj);

	List <Cliente> clientes = new ArrayList<Cliente>();
	List <EntidadeDominio> entidades = resultado.getEntidades();
	for(EntidadeDominio s: entidades){
	Cliente cliente = (Cliente)s;
	clientes.add(cliente);
	}
	Cliente cli = clientes.get(0);
	request.getSession().setAttribute("cliente", cli);
	request.removeAttribute("resultado");
	%>
	<jsp:include page="MenuCli.jsp" />

	<h2>Minha Conta</h2>

	<form action="ServletECL" method="get">
		<div class="col-md-12">

			<div class="col-md-4">
				<fieldset>
					<legend> Pedidos </legend>
					<a href="Pedidos.jsp" class="a"> Todos os meus Pedidos </a> <br>
					<a href="TrocaPedido.jsp" class="a"> Trocas de Pedidos </a>
				</fieldset>

				<fieldset>
					<legend> Dados </legend>
					<a href="AlterarCliente.jsp" class="a"> Alterar Dados </a> <br>
					<a href="AlterarSenha.jsp" class="a"> Alterar Senha </a>
				</fieldset>

				<fieldset class="Fieldset">
					<legend> Endereço </legend>
					<a href="NovoEndereco.jsp" class="a"> Novo Endereço </a> <br>
					<a href="VisualizarEndereco.jsp" class="a"> Alterar Endereço </a>
				</fieldset>

				<fieldset>
					<legend> Cartão </legend>
					<a href="VisualizarCartao.jsp" class="a"> Visualizar Cartão </a> <br>
					<a href="NovoCartao.jsp" class="a"> Novo Cartão </a>
				</fieldset>

			</div>

			<div class="col-md-8">
				<fieldset class="Fieldset2">
					<h3>Meus Dados Pessoais</h3>
					<div class="col-md-4-2">

						<p>Nome</p>
						<input type="text" name="Nome"
							value="<%=clientes.get(0).getNome()%>">

						<p>CPF</p>
						<input type="number" name="CPF"
							value="<%=clientes.get(0).getCpf()%>">

						<p>Data de Nascimento</p>
						<input type="text" name="DataNascimento" value="19/04/1934">

						<p>Telefone</p>
						<input type="number" name="Telefone"
							value="<%=clientes.get(0).getTelefone()%>">

						<p>Email</p>
						<input type="text" name="Email"
							value="<%=clientes.get(0).getEmail()%>">
						<p>Senha</p>
						<input type="password" name="Senha"
							value="<%=clientes.get(0).getSenha()%>">
					</div>
				</fieldset>
			</div>
		</div>
	</form>
</body>
</html>