<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Alterar Senha</title>

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
	height: 550px;
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
	<h2>Minha Conta</h2>
	<div class="col-md-12">

		<jsp:include page="OpcoesCliente.jsp" />

		<div class="col-md-8">
			<form action="/Ecommerc/AlterarSenhaResposta" method="post">
				<fieldset class="Fieldset2">

					<h3>Alterar Senha</h3>

					<div class="col-md-4-2">


						<p>Nova Senha</p>
						<input type="password" name="NovaSenha" value="">

						<p>Digite Novamente a nova senha</p>
						<input type="password" name="NovaSenhaConfirmacao" value="">

						<br>
						<button type="submit" name="operacao" id="botao"
							value="AlterarSenha">Confirmar</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>