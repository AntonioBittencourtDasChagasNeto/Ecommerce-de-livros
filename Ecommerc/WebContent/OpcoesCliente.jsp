<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Opções Cliente</title>
<style>
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
</style>
</head>
<body>
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

		<fieldset>
			<legend> Endereço </legend>
			<a href="NovoEndereco.jsp" class="a"> Novo Endereço </a> <br> <a
				href="VisualizarEndereco.jsp" class="a"> Visualizar Endereço </a> <br>
		</fieldset>

		<fieldset>
			<legend> Cartão </legend>
			<a href="VisualizarCartao.jsp" class="a"> Visualizar Cartão </a> <br>
			<a href="NovoCartao.jsp" class="a"> Novo Cartão </a>
		</fieldset>
	</div>

</body>
</html>