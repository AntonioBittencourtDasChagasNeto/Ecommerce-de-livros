<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.menu {
	width: 1473px;
	height: 50px;
	background-color: #0411f7;
	margin-top: -16px;
	margin-left: -8px;
}

.menu ul {
	font: 16px arial;
	list-style: none;
	position: relative;
}

.menu ul li {
	width: 150px;
	float: left;
}

.menu a {
	padding: 15px;
	display: block;
	text-decoration: none;
	text-align: center;
	background-color: blue;
	color: white;
}

.menu ul ul {
	position: absolute;
	visibility: hidden;
}

.menu ul li:hover ul {
	visibility: visible;
}

.menu a:hover {
	background-color: #6a71f2;
	color: #555;
}

.menu ul ul li {
	float: none;
	border-bottom: solid 1px #ccc;
}

.menu ul ul li a {
	background-color: #6a71f2;
}
</style>
<title>Entrada Estoque</title>
</head>
<body>
	<%
  		String Titulo = request.getParameter("Titulo");
    	String id = request.getParameter("Id");
	%>
	<div class="menu">
		<ul>
			<li><a href="#"> Login </a></li>
			<li><a href="#"> Livros </a>
				<ul>
					<li><a href="CadastrarLivro.jsp" name="CadastrarLivro">
							Cadastrar Livro </a></li>
					<li><a href="ConsultarLivro.jsp" name="ConsultarLivro">
							Consultar Livro </a></li>
				</ul></li>
		</ul>

	</div>

	<h3>Entrada Estoque</h3>

	<form action="/Ecommerc/" method="post">
		<fieldset name="fieldset">

			<!--Pegar o Nome do Livro (Opcional)-->
			<h4><%=Titulo%></h4>

			<br>

			<!--Definir o tamanho da Justificativa no Banco de Dados-->
			<p>Quantidade de Livros</p>
			<input type="text" name="QuantidadeLivros" id="QuantidadeLivros">

			<p>Valor de Custo</p>
			<input type="text" name="Custo" id="Custo">

			<p>Fornecedor(a)</p>
			<select name="Fornecedor" id="Autor">
				<%
                            try
                            {
                            	String sql = "SELECT * FROM fornecedor";
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
                        	%>
				<option selected></option>
				<%
                            	while(rs.next())
                            	{
                            		%>
				<option value="<%=rs.getInt("id_fornecedor")%>"><%=rs.getString("fornecedor") %></option>
				<%
                            	}
                            }
                            catch(Exception ex)
                            {
                            	ex.printStackTrace();
                            	out.println("Erro " + ex.getMessage());
                            }
                            
                            %>
			</select>

			<p>Grupo de Precificação</p>
			<select name="Precificacao" id="Precificacao">
				<%
                            try
                            {
                            	String sql = "SELECT * FROM precificacao";
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
                            		%>
				<option value="<%=rs.getInt("id_precificacao")%>"><%=rs.getString("precificacao") %></option>
				<%
                            		
                            	}
                            }
                            catch(Exception ex)
                            {
                            	ex.printStackTrace();
                            	out.println("Erro " + ex.getMessage());
                            }
                            
                            %>
			</select>



			<p>
				<!--Direcionar o botao pra outra jsp-->
				<input type="hidden" name="Id" value="<%=id%>"></input>
				<button type="submit" name="operacao" id="botao"
					value="EntradaEstoque">Confirmar</button>

			</p>

		</fieldset>

	</form>
</body>
</html>