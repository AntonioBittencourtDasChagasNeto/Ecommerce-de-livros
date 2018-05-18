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

<title>Consultar Livro dm</title>
</head>

<body>


	<div class="menu">
		<ul>
			<li><a href="#"> Login </a></li>
			<li><a href="#"> Livros </a>
				<ul>
					<li><a href="/Ecommerc/CadastrarLivro.jsp"
						name="CadastrarLivro"> Cadastrar Livro </a></li>
					<li><a href="/Ecommerc/ConsultarLivro.jsp"
						name="ConsultarLivro"> Consultar Livro </a></li>
				</ul></li>
		</ul>

	</div>
	<!-- p>Todays Date: <%= new java.util.Date() %></p -->
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
			<button type="submit" name="operacao" value="consultar">Filtrar</button>
		</div>
	</form>

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
    	Result resultado = new Result();
    	Object obj = new Object();
    	try{
    	obj = request.getAttribute("resultado");
    	if (obj != null)
    	{
    	resultado = Result.class.cast(obj);
    	List <Livro> livros = new ArrayList<Livro>();
    	List <EntidadeDominio> entidades = resultado.getEntidades();
    	try{
    		if(entidades != null)
    		{
    	for(EntidadeDominio s: entidades){
    	Livro livro = (Livro)s;
    	livros.add(livro);
    	}
    		}
    	}catch (Exception e){
    		throw e;
    	}
    	if(livros != null)
    	{
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
			 if(liv.getStatus().equals("ATIVADO")){
				 %>
				<form action="/Ecommerc/book/save" method="get">
					<input type="hidden" name="Id" value="<%=liv.getId()%>"></input> <input
						type="hidden" name="Titulo" value="<%=liv.getTitulo()%>"></input>
					<input type="submit" name="operacao" value="Inativar"></input>
				</form> <%				 		 
			 }else if (liv.getStatus().equals("INATIVADO")){
				 %>
				<form action="/Ecommerc/book/save" method="post">
					<button type="submit" name="operacao" value="ativarConsulta">ativar</button>
					<input type="hidden" name="Id" value="<%=liv.getId()%>"></input>
				</form> <%	
			 }
			%>
				<form action="/Ecommerc/book/save" method="post">
					<button type="submit" name="operacao" value="visualizar">editar</button>
					<input type="hidden" name="Id" value="<%=liv.getId()%>"></input>
				</form>

				<form action="/Ecommerc/EntradaEstoque.jsp" method="post">
					<button type="submit" name="operacao" value="EntrarEstoque">EntrarEstoque</button>
					<input type="hidden" name="Id" value="<%=liv.getId()%>"></input> <input
						type="hidden" name="Titulo" value="<%=liv.getTitulo()%>"></input>
				</form>
			</td>
		</tr>
		<%
		}
    	
    	} // segundo if
    	
    	} //if
    	}catch(Exception e){
    		throw e;
    	}
	%>

	</table>


</body>
</html>

