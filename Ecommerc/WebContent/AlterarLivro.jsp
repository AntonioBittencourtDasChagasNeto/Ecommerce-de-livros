<%@page import="java.util.ArrayList"%>
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

	<!--		https://pt.stackoverflow.com/questions/242787/passando-atributos-de-uma-servlet-para-uma-jsp	
        -->

	<%
	Result resultado = new Result();
	Object obj = new Object();
	obj = request.getAttribute("resultado");
	resultado = Result.class.cast(obj);
	System.out.println("Sera que eu existo? " +  resultado.getMsg());
	List <Livro> livros = new ArrayList<Livro>();
	List <EntidadeDominio> entidades = resultado.getEntidades();
	for(EntidadeDominio s: entidades){
	Livro livro = (Livro)s;
	livros.add(livro);
	}
	%>
	<h3>Cadastrar Livro</h3>
	<form action="/Ecommerc/" method="post">
		<fieldset>
			<div class="col-md-12">
				<div class="col-md-4">

					<p>Código de Barras</p>
					<input type="text" name="CodigoDeBarras"
						value="<%= livros.get(0).getCodigoBarras()%>" />

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
                        		//Se for diferente da Editora já selecionada
                        		if(rs.getInt("id_Autor") != livros.get(0).getAutor().getId()){
                        		%>
						<option value="<%=rs.getInt("id_Autor")%>"><%=rs.getString("autor") %></option>
						<%
                        		}
                        		else{
                        			%>
						<option value="<%=livros.get(0).getAutor().getId()%>" selected><%=rs.getString("autor") %></option>
						<%
                        		}
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
					<input type="text" name="Titulo"
						value="<%= livros.get(0).getTitulo() %>" />

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
                            		//Se for diferente da Editora já selecionada
                            		if(rs.getInt("id_Editora") != livros.get(0).getEditora().getId()){
                            		%>
						<option value="<%=rs.getInt("id_editora")%>"><%=rs.getString("editora") %></option>
						<%
                            		}
                            		else{
                            			%>
						<option value="<%=livros.get(0).getEditora().getId()%>" selected><%=rs.getString("editora") %></option>
						<%
                            		}
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
					<input type="text" name="ISBN"
						value="<%= livros.get(0).getISBN() %>" />

					<p>Páginas</p>
					<input type="text" name="NumeroPaginas"
						value="<%= livros.get(0).getNumeroPaginas() %>" />

					<p>Edição</p>
					<input type="text" name="Edicao"
						value="<%= livros.get(0).getEdicao()%>" />
				</div>

				<div class="col-md-4">

					<p>Ano</p>
					<input type="number" name="Ano"
						value="<%= livros.get(0).getAno()%>" />

					<!-- Ver a Categoria-->
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
								int Verificar = 0;
								for(Categoria cat : livros.get(0).getIdcategoria())
								{
									%>
						<option value="<%=cat.getId()%>" selected><%=cat.getNome()%></option>
						<%
									
								}
                            	while(rs.next())
                            	{
                            		Verificar = 0;
                            		for(Categoria cat : livros.get(0).getIdcategoria()){
                            			if(rs.getInt("id_Categoria") == cat.getId())
                            			{	//Verifica se é uma categoria selecionada pelo usuario
                            				Verificar = 1;	
                            		}	
                            			if(Verificar == 0)
                            		{
                            			%>
						<option value="<%=rs.getInt("id_Categoria")%>"><%=rs.getString("categoria")%></option>
						<%
                            		}
                            			
                            	}
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
					<input type="text" name="Altura"
						value="<%= livros.get(0).getDimensoes().getAltura() %>" />

					<p>Largura</p>
					<input type="text" name="Largura"
						value="<%= livros.get(0).getDimensoes().getLargura()%>" />

					<p>Peso</p>
					<input type="text" name="Peso"
						value="<%= livros.get(0).getDimensoes().getPeso() %>" />

					<p>Profundidade</p>
					<input type="text" name="Profundidade"
						value="<%= livros.get(0).getDimensoes().getProfundidade()%>" />

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
                            		if(rs.getInt("id_Precificacao") == livros.get(0).getPrecificacao().getTipo())
                            		{
                            		%>
						<option value="<%=livros.get(0).getPrecificacao().getTipo()%>"
							selected><%=rs.getString("Precificacao") %>
						</option>
						<%                        		                        			     		
                            	}else
                            	{
                            		%>
						<option value="<%=rs.getInt("id_Precificacao")%> "><%=rs.getString("Precificacao") %></option>
						<%
                            	}
           
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
					<textarea type="text" name="Sinopse"><%=livros.get(0).getSinopse()%></textarea>
					<input type="hidden" name="Id" value="<%=livros.get(0).getId()%>"></input>
					<button type="submit" name="operacao" value="alterar" id="botao">Confirmar</button>
					</p>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>