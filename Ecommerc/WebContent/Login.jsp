<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
fieldset {
	width: 50%;
	margin: 0px auto;
	border-color: blue;
}

.col-md-12 {
	margin-left: 200px;
}

.col-md-12 a {
	font: arial;
	font-size: 12px;
}

body {
	background-color: #f7f7f7;
}

.botao {
	font-weight: bold;
	display: flex;
}
</style>

<title>Login</title>

</head>

<body>
	<jsp:include page="Menu.jsp" />

	<h3>Login</h3>
	<form action="/Ecommerc/MenuCliente" method="post">

		<fieldset>
			<div class="col-md-12">

				<p>Usuário</p>

				<input type="text" name="Usuario" id="Usuario">

				<p>Senha</p>

				<input type="text" name="Senha" id="Senha"> <br> <a
					href="EsqueceuSenha.jsp" name="EsqueceuSenha"
					style="margin-left: 70px;"> Esqueceu a Senha</a>

				<div class="botao">
					<button type="submit" name="operacao" id="botao" value="Login">Confirmar</button>
					<a href="MenuCliente.jsp"><button type="button" name="botao2"
							id="botao2">Cancelar</button></a>
				</div>
			</div>
		</fieldset>
	</form>

</body>
</html>