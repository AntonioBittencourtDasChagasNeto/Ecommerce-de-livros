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
	System.out.println("Id do endereco = " + clientes.get(0).getEndereco().getId());
	request.removeAttribute("resultado");
	%>
	<h2>Minha Conta</h2>
	<div class="col-md-12">

		<jsp:include page="OpcoesCliente.jsp" />

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
							value="<%= clientes.get(0).getEndereco().getCep()%>" /></input><br />
						<p>Logradouro</p>
						<input type="text" name="Logradouro" id="Logradouro"
							value="<%= clientes.get(0).getEndereco().getLogradouro()%>"
							size="40">

						<p>
							Bairro <input name="Bairro" type="text" id="Bairro" size="40"
								value="<%= clientes.get(0).getEndereco().getBairro()%>" />
						</p>

						<p>
							Estado <input name="Estado" type="text" id="Estado" size="40"
								value="<%= clientes.get(0).getEndereco().getEstado()%>" />
						</p>
						<p>
							Cidade <input name="Cidade" type="text" id="Cidade" size="40"
								value="<%= clientes.get(0).getEndereco().getCidade()%>" />
						</p>
						<button type="submit" name="operacao" value="buscarCEP"
							class="button">BuscarCEP</button>

						<p>Número</p>
						<input name="Numero" type="number" id="Numero"
							value="<%= clientes.get(0).getEndereco().getNumero()%>" />
						<p>
							País <input name="Pais" type="text" id="Pais" value="Brasil" />
						</p>

						<p>
							Endereço Preferencial Cobrança: <input type="checkbox"
								checked="checked" name="Cobrança" value="Cobrança">
						</p>
						<p>
							Endereço Preferencial Entrega: <input type="checkbox"
								checked="checked" name="Entrega" value="Entrega">
						</p>

						<p>Observação</p>
						<textarea type="text" name="Observacao" id="Observacao" cols="30"
							rows="4"> </textarea>
						</p>
						<input type="hidden" name="Id"
							value="<%=clientes.get(0).getEndereco().getId()%>"></input>
						<button type="submit" name="operacao" id="botao"
							value="AlterarEndereco">Confirmar</button>

					</fieldset>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>