<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ecl.dominio.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Alterar Cliente</title>

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

.Fieldset2 {
	width: 700px;
	height: 570px;
	border-color: orange;
}

input {
	border: solid 1px;
	font-size: 17px;
	color: #7f7e7e;
}

button {
	margin: 15px;
	margin-left: 144px;
}
</style>
</head>
<body>
	<jsp:include page="Menu.jsp" />
	<%
		Object obj = new Object();
		obj = request.getSession().getAttribute("cliente");
		Cliente cli = Cliente.class.cast(obj);
	%>

	<h2>Minha Conta</h2>
	<div class="col-md-12">

		<jsp:include page="OpcoesCliente.jsp" />

		<div class="col-md-8">
			<form action="/Ecommerc/AlterarClienteResposta" method="post">
				<fieldset class="Fieldset2">

					<h3>Alterar Dados</h3>

					<div class="col-md-4-2">

						<p>Nome</p>
						<input type="text" name="Nome" value="<%=cli.getNome()%>">

						<p>CPF</p>
						<input type="number" name="Cpf" value="<%=cli.getCpf()%>">

						<p>Data de Nascimento</p>
						<input type="text" name="DataNascimento" value="19/04/1934">

						<p>Sexo</p>

						<select name="Sexo" id="Sexo">
							<option value="Feminino">Feminino</option>
							<option value="Masculino">Masculino</option>

						</select>
						<p>DDD</p>
						<input type="number" name="DDD" value="<%=cli.getDDD()%>">
						<p>Telefone</p>
						<input type="number" name="Telefone"
							value="<%=cli.getTelefone()%>">

						<p>Email</p>
						<input type="text" name="Email" value="<%=cli.getEmail()%>">

						<br>
						<button type="submit" name="operacao" id="botao" value="alterar">Confirmar</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>