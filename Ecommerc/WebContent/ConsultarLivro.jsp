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

<style>
div.container {
	width: 100%;
}

header, footer {
	padding: 1em;
	color: white;
	background-color: black;
	clear: left;
	text-align: center;
}

nav {
	float: left;
	max-width: 160px;
	margin: 0;
	padding: 1em;
}

nav ul {
	list-style-type: none;
	padding: 0;
}

nav ul a {
	text-decoration: none;
}

article {
	margin-left: 170px;
	padding: 2em;
	overflow: hidden;
}

img {
	width: 220px;
}

* {
	box-sizing: border-box;
}

/* Create four equal columns that floats next to each other */
table {
	width: 1000px;
	_width: 1300px;
}

th, td {
	text-align: left;
	padding: 8px;
	width: 1000px;
	_width: 1500px;
}

.menu {
	width: 1600px;
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

.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	width: 100px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
}

.button2 {
	background-color: #4CAF50;
	border: none;
	color: white;
	width: 100px;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.div-select {
	width: 150px; /* Tamanho final do select */
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>
<title>Consultar Livro adm</title>
</head>

<body>
	<div class="container">
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
		<nav>
			<form action="/Ecommerc/" method="get">
				<div>
					<div>
						<div>
							<p>Código de barras(a)</p>
							<input type="text" name="CodigoDeBarras" />
						</div>
					</div>

					<div>
						<div>
							<p>Título(a)</p>
							<input type="text" name="Titulo" />
						</div>
					</div>

					<div>
						<div>
							<p>Autor(a)</p>
							<select name="Autor" id="Autor">
								<%
                            try
                            {
                            	String sql = "SELECT * FROM autor";
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
								<option value="<%=rs.getInt("id_Autor")%>"><%=rs.getString("autor") %></option>
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
						</div>
					</div>
				</div>

				<div>
					<div>
						<div>
							<p>Editora</p>
							<select name="Editora" id="Editora">
								<%
                            try
                            {
                            	String sql = "SELECT * FROM editora";
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
								<option value="<%=rs.getInt("id_editora")%>"><%=rs.getString("editora") %></option>
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
						</div>
					</div>

					<div>
						<div>
							<p>Busque um ano</p>
							<input type="text" name="Ano" />
						</div>
					</div>

					<div>
						<div>
							<p>Categoria</p>
							<select name="Categoria" multiple="multiple">
								<%
                            try
                            {
                            	String sql = "SELECT * FROM Categoria";
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
								<option value="<%=rs.getInt("id_Categoria")%>"><%=rs.getString("categoria") %></option>
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
						</div>
					</div>

				</div>

				<div>
					<button type="submit" name="operacao" value="consultar"
						class="button2">Filtrar</button>
				</div>
			</form>
		</nav>
		<%
 				List<Livro> livros = new ArrayList<Livro>();
                            try
                            {
                            	String sql = "SELECT * FROM livro";
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
                            		Livro liv = new Livro();
                            		liv.setId(rs.getInt("idlivro"));
                        			liv.setTitulo(rs.getString("livTitulo"));
                        			liv.setCodigoBarras(rs.getString("livCodigoDeBarras"));
                        			liv.setISBN(rs.getString("livISBN"));
                        			liv.setEdicao(rs.getInt("livEdicao"));
                        			liv.setStatus(rs.getString("livStatus"));
                        			livros.add(liv);
                            		
                            	}
                            }
                            catch(Exception ex)
                            {
                            	ex.printStackTrace();
                            	out.println("Erro " + ex.getMessage());
                            }
                            
                            %>

		<article>
			<table border="1" cellpadding="5">
				<tr>
					<th>Titulo</th>
					<th>Código de Barras</th>
					<th>ISBN</th>
					<th>Edição</th>
					<th>Status</th>
					<th>Opções</th>
				</tr>

				<%
		for(Livro liv : livros)
		{
			%>
				<tr>
					<td><%=liv.getTitulo()%></td>
					<td><%=liv.getCodigoBarras()%></td>
					<td><%=liv.getISBN()%></td>
					<td><%=liv.getEdicao()%></td>
					<td><%=liv.getStatus()%></td>
					<td>
						<%
			String status = liv.getStatus();
			 if(status.equals("ATIVADO")){
				 %>
						<form action="/Ecommerc/book/save" method="get">
							<input type="hidden" name="Id" value="<%=liv.getId()%>"></input>
							<input type="hidden" name="Titulo" value="<%=liv.getTitulo()%>"></input>
							<input type="submit" name="operacao" value="Inativar"
								class="button"></input>
						</form> <%				 		 
			 }else if (status.equals("INATIVADO")){
				 %>
						<form action="/Ecommerc/" method="post">
							<button type="submit" name="operacao" value="Ativar"
								class="button">ativar</button>
							<input type="hidden" name="Id" value="<%=liv.getId()%>"></input>
							<input type="hidden" name="Titulo" value="<%=liv.getTitulo()%>"></input>
						</form> <%	
			 }
			%>
						<form action="/Ecommerc/book/save" method="post">
							<button type="submit" name="operacao" value="visualizar"
								class="button">editar</button>
							<input type="hidden" name="Id" value="<%=liv.getId()%>"></input>
						</form>

						<form action="EntradaEstoque.jsp" method="post">
							<button type="submit" name="operacao" value="EntrarEstoque"
								class="button">EntrarEstoque</button>
							<input type="hidden" name="Id" value="<%=liv.getId()%>"></input>
							<input type="hidden" name="Titulo" value="<%=liv.getTitulo()%>"></input>
						</form>
					</td>
				</tr>
				<%
		}
	%>

			</table>
		</article>
	</div>
</body>
</html>

