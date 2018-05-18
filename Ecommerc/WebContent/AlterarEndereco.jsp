<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ecl.core.util.buscaCEP"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Alterar Endereço</title>

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
	height: 800px;
	border-color: orange;
}

input {
	border: solid 1px;
	font-size: 17px;
	color: #7f7e7e;
}

button {
	margin-left: 280px;
	margin-top: -100px;
}

.Fieldset3 {
	width: 300px;
	height: 680px;
	border-color: blue;
}

.legend {
	color: blue;
}
</style>

</head>
<body>
	<jsp:include page="Menu.jsp" />

	<h2>Minha Conta</h2>
	<div class="col-md-12">

		<jsp:include page="OpcoesCliente.jsp" />
		<%
    String CEP = request.getParameter("cep");
	String Estado = request.getParameter("estado");
	String Logradouro = request.getParameter("Logradouro");
	String Bairro = request.getParameter("bairro");
	String Cidade = request.getParameter("cidade");
	String Pais = request.getParameter("pais");
	String Id = request.getParameter("Id");
	String Cobranca = request.getParameter("Cobranca");
	String Entrega = request.getParameter("Entrega");
	int Numero = Integer.parseInt(request.getParameter("Numero"));
	%>
		<div class="col-md-8">
			<form action="/Ecommerc/AlterarEnderecoResposta" method="post">
				<fieldset class="Fieldset2">

					<h3>Alterar Endereço</h3>

					<fieldset class="Fieldset3">
						<legend class="legend"> Endereço </legend>

						<select name="Endereco" id="Endereco">
							<option value="Casa">Casa</option>
							<option value="Apartamento">Apartamento</option>
							<option value="Trabalho">Trabalho</option>
						</select>
						<p>CEP</p>
						<input name="cep" type="text" id="cep" size="10" maxlength="9"
							value="<%=CEP%>" /></input><br />
						<p>Logradouro</p>
						<input type="text" name="Logradouro" id="Logradouro"
							value="<%=Logradouro%>" size="40">

						<p>
							Bairro <input name="Bairro" type="text" id="Bairro" size="40"
								value="<%=Bairro %>" />
						</p>

						<p>
							Estado <input name="Estado" type="text" id="Estado" size="40"
								value="<%=Estado%>" />
						</p>

						<p>
							Cidade <input name="Cidade" type="text" id="Cidade" size="40"
								value="<%=Cidade%>" />
						</p>
						<button type="submit" name="operacao" value="buscarCEP"
							class="button">BuscarCEP</button>

						<p>Número</p>
						<input name="Numero" type="number" id="Numero" value="<%=Numero%>" />
						<p>
							País <input name="Pais" type="text" id="Pais" value="Brasil" />
						</p>
						<%
									if(Cobranca.equals("SIM"))
									{
									%>
						<p>
							Endereço Preferencial Cobrança: <input type="checkbox"
								checked="checked" name="Cobrança" checked="checked" value="SIM">
						</p>
						<%
									}
									else
									{
									 %>
						<p>
							Endereço Preferencial Cobrança: <input type="checkbox"
								checked="checked" name="Cobrança" value="SIM">
						</p>
						<%
									}
									 %>
						<%
									if(Entrega.equals("SIM"))
									{
									%>
						<p>
							Endereço Preferencial Entrega: <input type="checkbox"
								checked="checked" name="Entrega" checked="checked" value="SIM">
						</p>
						<%
									}
									else
									{
									 %>
						<p>
							Endereço Preferencial Entrega: <input type="checkbox"
								checked="checked" name="Entrega" value="SIM">
						</p>
						<%
									}
									 %>
						<p>Observação</p>
						<textarea type="text" name="Observacao" id="Observacao" cols="30"
							rows="4"> </textarea>
						<input type="hidden" name="Id" value="<%=Id%>"></input>
						<button type="submit" name="operacao" id="botao"
							value="AlterarEndereco">Confirmar</button>

					</fieldset>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>