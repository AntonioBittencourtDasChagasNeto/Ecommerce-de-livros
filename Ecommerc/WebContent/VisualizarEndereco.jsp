<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.dominio.Endereco"%>
<%@ page import="ecl.core.aplicacao.Result"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecl.dominio.EntidadeDominio"%>
<%@ page import="ecl.dominio.Bandeira"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Visualizar Cartão</title>

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
	height: 760px;
	border-color: orange;
}

input {
	border: solid 1px;
	font-size: 17px;
	color: #7f7e7e;
	border: none;
	background-color: #f7f7f7;
}

button {
	margin-bottom: 30px;
	margin-left: 10px;
}

.Fieldset3 {
	width: 300px;
	height: 200px;
	border-color: blue;
}

.legend {
	color: blue;
}

.checkbox {
	margin-left: 280px;
	margin-top: -30px;
}
</style>
</head>
<body>
	<jsp:include page="Menu.jsp" />

	<h2>Minha Conta</h2>
	<div class="col-md-12">

		<jsp:include page="OpcoesCliente.jsp" />
		<%
	Object id = request.getSession().getAttribute("idCliente");
List<Endereco> enderecos = new ArrayList<Endereco>();
try
{
	String sql = "SELECT * FROM endereco  WHERE IdCliente=\""+id+"\"";
	String driver = "com.mysql.jdbc.Driver";
	String serverName = "localhost";    //caminho do servidor do BD
    String mydatabase ="ecl_livros";        //nome do seu banco de dados
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useSSL=false";
	String user = "root";
	String password = "semsenha";
	Class.forName(driver);
	Connection conn = 
			DriverManager.getConnection( url, user, password);
			Statement stm =conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
	while(rs.next())
	{
		Endereco endereco = new Endereco();
		endereco.setId(rs.getInt("idendereco"));
		endereco.setBairro(rs.getString("Bairro"));
		endereco.setCep(rs.getString("CEP"));
		endereco.setCidade(rs.getString("Cidade"));
		endereco.setLogradouro(rs.getString("Logradouro"));
		endereco.setEstado(rs.getString("Estado"));
		endereco.setNumero(rs.getInt("Numero"));
		endereco.setPais(rs.getString("Pais"));
		endereco.setEntrega(rs.getString("Entrega"));
		endereco.setCobranca(rs.getString("Cobranca"));

		enderecos.add(endereco);
		
	}
}
catch(Exception ex)
{
	ex.printStackTrace();
	out.println("Erro " + ex.getMessage());
}
%>
		<table border="1" cellpadding="5">
			<tr>
				<th>Logradouro</th>
				<th>CEP</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>Pais</th>
				<th>Bairro</th>
				<th>Numero</th>
				<th>Opções</th>
			</tr>

			<%
		for(Endereco end : enderecos)
		{
			%>
			<tr>
				<td><%=end.getLogradouro()%></td>
				<td><%=end.getCep()%></td>
				<td><%=end.getCidade()%></td>
				<td><%=end.getEstado()%></td>
				<td><%=end.getPais()%></td>
				<td><%=end.getBairro()%></td>
				<td><%=end.getNumero()%></td>
				<td>

					<form action="AlterarEndereco.jsp" method="post">
						<button type="submit" name="operacao" value="Editar"
							class="button">Editar</button>
						<input type="hidden" name="Id" value="<%=end.getId()%>"></input> <input
							type="hidden" name="Logradouro" value="<%=end.getLogradouro()%>"></input>
						<input type="hidden" name="cep" value="<%=end.getCep()%>"></input>
						<input type="hidden" name="cidade" value="<%=end.getCidade()%>"></input>
						<input type="hidden" name="estado" value="<%=end.getEstado()%>"></input>
						<input type="hidden" name="pais" value="<%=end.getPais()%>"></input>
						<input type="hidden" name="bairro" value="<%=end.getBairro()%>"></input>
						<input type="hidden" name="Numero" value="<%=end.getNumero()%>"></input>
						<input type="hidden" name="Entrega" value="<%=end.getEntrega()%>"></input>
						<input type="hidden" name="Cobranca"
							value="<%=end.getCobranca()%>"></input>
					</form>
				</td>
			</tr>
			<%
		}
	%>

		</table>
	</div>
</body>
</html>