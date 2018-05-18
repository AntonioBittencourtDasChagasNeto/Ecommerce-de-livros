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
<%@page import="java.util.ArrayList"%>
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
<title>Confirmar Inativar Livro</title>
</head>
<body>

	<%
    	Result resultado = new Result();
    	Object obj = new Object();
    	obj = request.getAttribute("resultado");
    	resultado = Result.class.cast(obj);
    	List <Livro> livros = new ArrayList<Livro>();
    	List <EntidadeDominio> entidades = resultado.getEntidades();
    	for(EntidadeDominio s: entidades){
    	Livro livro = (Livro)s;
    	livros.add(livro);
    	}
    	System.out.println(livros.get(0).getId());
    	System.out.println(livros.get(0).getTitulo());
	%>
	<div class="menu">
		<ul>
			<li><a href="#"> ADM </a></li>
			<li><a href="#"> Livros </a>
				<ul>
					<li><a href="/Ecommerc/CadastrarLivro.jsp"
						name="CadastrarLivro"> Cadastrar Livro </a></li>
					<li><a href="/Ecommerc/ConsultarLivro.jsp"
						name="ConsultarLivro"> Consultar Livro </a></li>
				</ul></li>
		</ul>

	</div>

	<h3>Confirmar Ativar Livro</h3>

	<form action="/Ecommerc/" method="post">
		<fieldset name="fieldset">
			<input type="hidden" name="Id" value="<%= livros.get(0).getId() %>">

			<!--Pegar o Nome do Livro (Opcional)-->
			<h4><%= livros.get(0).getTitulo() %></h4>
			<input type="hidden" name="Titulo"
				value="<%= livros.get(0).getTitulo() %>"></input>
			<!--Definir o tamanho da Justificativa no Banco de Dados-->
			<p>Jusfiticativa</p>
			<textarea type="text" name="Jusfiticativa" id="Jusfiticativa"
				cols="40" rows="10"> </textarea>

			<p>Categoria da Inativação</p>
			<select name="CategoriaJustificativa" id="CategoriaJustificativa">
				<%
                            try
                            {
                            	String sql = "SELECT * FROM ativar";
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
				<option value="<%=rs.getInt("idativar")%>"><%=rs.getString("motivo") %></option>
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
				<button type="submit" name="operacao" id="botao" value="ativar">Confirmar</button>
			</p>
		</fieldset>
	</form>
</body>
</html>