<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-ms-box-sizing: border-box;
	box-sizing: border-box;
}

html {
	font-family: sans-serif;
	-ms-text-size-adjust: 100%;
}

.menu {
	width: 1310px;
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

.Title {
	font-size: 30px;
}

.col-md-12 {
	display: flex;
}

.col-md-4 {
	padding: 50px;
	margin-right: 150px;
	padding-top: 10px;
}

.sinopse {
	width: 400px;
	height: 400px;
}

.botao {
	float: right;
}

.categoria[multiple] {
	max-width: 40px;
	height: 10px;
}

.arquivo {
	height: 200px;
	width: 150px;
}

.col-md-4-3 {
	padding: 50px;
	padding-top: 10px;
}

fieldset {
	background-color: #f7f7f7;
}
</style>
<title>Cadastrar Livro</title>
</head>
<body>
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




	<h3>Cadastrar Livro</h3>
	<form action="/Ecommerc/" method="post">
		<fieldset name="fieldset">
			<div class="col-md-12">
				<div class="col-md-4">
					<p>Código de Barras do Livro</p>
					<input type="text" name="CodigoDeBarras" id="CodigoBarras" />

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

					<p>Título</p>
					<input type="text" name="Titulo" id="Titulo" maxlength="40" />

					<!-- Falta puxar o Editora em LIVRODAO -->
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
                            	while(rs.next())
                            	{
                            		%>
						<option value="<%=rs.getInt("id_Editora")%>"><%=rs.getString("editora") %></option>
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

					<p>ISBN</p>
					<input type="text" name="ISBN" id="ISBN" maxlength="13" />

					<p>Páginas</p>
					<input type="text" name="NumeroPaginas" id="NumeroPaginas"
						maxlength="4" />

					<p>Edição</p>
					<input type="text" name="Edicao" id="Edicao" maxlength="2" />
				</div>


				<div class="col-md-4">

					<%
                        Date data = new Date();
                        DateFormat df = new SimpleDateFormat("yyyy");
                        String ano = df.format(data);
                        %>

					<p>Ano</p>
					<input type="number" name="Ano" id="Ano" max="<%=ano%>" min="0"
						onKeyUp="if(this.value><%=ano%>){this.value='<%=ano%>';}else if(this.value < 0)
                        {this.value='0';}" />


					<!-- Falta puxar a Categoria m LIVRODAO -->
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

					<p>Altura</p>
					<input type="number" name="Altura" id="Altura" maxlength="6" />

					<p>Largura</p>
					<input type="number" name="Largura" id="Largura" maxlength="6" />

					<p>Peso</p>
					<input type="number" name="Peso" id="Peso" maxlength="6" />

					<p>Profundidade</p>
					<input type="number" name="Profundidade" id="Profundidade"
						maxlength="6" />
				</div>

				<div class="col-md-4-3">

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

					<p>Sinopse</p>
					<!--Definir no Banco de dados o Tamanho max-->
					<textarea type="text" name="Sinopse" id="Sinopse" cols="30"
						rows="10"> </textarea>

					<!-- Falta puxar a Imagem em LIVRODAO -->
					<p>Imagem</p>
					<input type="file" name="arquivo" />
					<p>
						<button type="submit" name="operacao" id="botao" value="salvar">Confirmar</button>
					</p>
					<!--- Redicrecionar pelo botão, para uma outra página.
                       <script type="text/javascript"
                            function Nova()
                            {
                            location.href=" index.html"
                            }
                            </script>

                            <input type="submit" value="Trocar Senha">
                             <input type="reset" value="Limpar">
                            <input type="button" value="cancelar" onClick="Nova()">->
                                        </div>
                            -->
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>