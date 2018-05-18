<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.dominio.Cartao"%>
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
	System.out.println("Id da session = " + id);
List<Cartao> cartoes = new ArrayList<Cartao>();
try
{
	String sql = "SELECT * FROM cartao join bandeira ON cartao.idcartao = bandeira.idBandeira WHERE idCliente=\""+id+"\"";

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
		Bandeira bandeira = new Bandeira();
		Cartao cartao = new Cartao();
		cartao.setId(rs.getInt("idcartao"));
		cartao.setNumero(rs.getString("numerocartao"));
		cartao.setCodigoDeSeguranca(rs.getInt("codigoseguranca"));
		cartao.setNomeCartao(rs.getString("nomecartao"));
		bandeira.setId(rs.getInt("cartao.idBandeira"));
		bandeira.setNome(rs.getString("bandeira.bandeiraNome"));
		cartao.setBandeira(bandeira);
		cartoes.add(cartao);
		
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
				<th>Numero</th>
				<th>Nome no cartão</th>
				<th>Código de segurança</th>
				<th>Bandeira</th>
				<th>Opções</th>
			</tr>

			<%
		for(Cartao car : cartoes)
		{
			%>
			<tr>
				<td><%=car.getNumero()%></td>
				<td><%=car.getNomeCartao()%></td>
				<td><%=car.getCodigoDeSeguranca()%></td>
				<td><%=car.getBandeira().getNome()%></td>
				<td>

					<form action="AlterarCartao.jsp" method="post">
						<button type="submit" name="operacao" value="Editar"
							class="button">Editar</button>
						<input type="hidden" name="Id" value="<%=car.getId()%>"></input> <input
							type="hidden" name="Numero" value="<%=car.getNumero()%>"></input>
						<input type="hidden" name="CodigoDeSeguranca"
							value="<%=car.getCodigoDeSeguranca()%>"></input> <input
							type="hidden" name="IdBandeira"
							value="<%=car.getBandeira().getId()%>"></input> <input
							type="hidden" name="NomeCartao" value="<%=car.getNomeCartao()%>"></input>
						<input type="hidden" name="Preferencial"
							value="<%=car.getPreferencial()%>"></input>
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