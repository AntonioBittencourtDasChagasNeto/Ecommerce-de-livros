<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="ecl.dominio.ItensPedido"%>
<%@ page import="ecl.core.aplicacao.Result"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecl.dominio.EntidadeDominio"%>
<%@ page import="ecl.dominio.Categoria"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
.column {
	float: left;
	width: 300px;
	padding: 20px;
	height: 400px; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.row:after {
	content: "";
	display: table;
	clear: both;
	margin-top: 300px;
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
	width: 215px;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
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
</style>
<title>Insert title here</title>
</head>
<body>

	<div class="container">


		<jsp:include page="MenuCli.jsp" />

		<%
	Result resultado = new Result();
	Object obj = new Object();
	obj = request.getAttribute("resultado");
	resultado = Result.class.cast(obj);

	List <ItensPedido> itens = new ArrayList<ItensPedido>();
	List <EntidadeDominio> entidades = resultado.getEntidades();
	for(EntidadeDominio s: entidades){
	ItensPedido item = (ItensPedido)s;
	itens.add(item);
	}
	request.removeAttribute("resultado");
	%>
		<nav>
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
				<select name="Autor" id="Autor" class="div-select">
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
		<div>
			<div>
				<p>Editora</p>
				<select name="Editora" id="Editora" class="div-select">
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
				<select name="Categoria" multiple="multiple" class="div-select">
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

		<div>
			<form action="ConsultarLivroCliente.jsp" method="post">
				<p>
					<input type="submit" class="button2" value="Filtrar">
				</p>
			</form>
		</div>
		</nav>

		<article>
		<h2>Livros</h2>
		<%
	int contador = 1;
	int contador2 = 1;
	int tamanho = itens.size();
	for(ItensPedido it : itens)
	{
		if(contador == 1 && contador2 != tamanho)
		{
			%>
		<div class="row">
			<div class="column">
				<h2>
					<img
						src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
				</h2>
				<p>
					<a><%=it.getLivro().getTitulo()%></a>
				</p>
				<%
    		Double margem = (it.getCusto()*(it.getMargem()/100));
			Double Preco = it.getCusto() + margem;
    		%>
				Preço: R$
				<%=Preco%>
				<form action="/Ecommerc/Carrinho" method="post">
					<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
					<input type="hidden" name="Custo" value="<%=it.getCusto()%>"></input>
					<input type="hidden" name="Margem" value="<%=it.getMargem()%>"></input>
					<input type="hidden" name="altura"
						value="<%=it.getLivro().getDimensoes().getAltura()%>"></input> <input
						type="hidden" name="largura"
						value="<%=it.getLivro().getDimensoes().getLargura()%>"></input> <input
						type="hidden" name="profundidade"
						value="<%=it.getLivro().getDimensoes().getProfundidade()%>"></input>
					<input type="hidden" name="peso"
						value="<%=it.getLivro().getDimensoes().getPeso()%>"></input> <input
						type="hidden" name="Titulo" value="<%=it.getLivro().getTitulo()%>"></input>
					<input type="hidden" name="id_estoque"
						value="<%=it.getIdEstoque()%>"></input> <input type="submit"
						class="button" name="operacao" value="AdicionarCarrinho"
						align="middle">
				</form>
				<form action="/Ecommerc/FichaTecnica" method="post">
					<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
					<input type="submit" class="button" name="operacao"
						value="Detalhes" align="middle">
				</form>
			</div>
			<%
			contador2++;
			contador++;
			continue;
		}
		if((contador == 2 || contador == 3) && contador2 != tamanho)
		{
			%>
			<div class="column">
				<h2>
					<img
						src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
				</h2>
				<p>
					<a><%=it.getLivro().getTitulo()%></a>
				</p>
				<%
    		Double margem = (it.getCusto()*(it.getMargem()/100));
			Double Preco = it.getCusto() + margem;
    		%>
				Preço: R$
				<%=Preco%>
				<form action="/Ecommerc/Carrinho" method="post">
					<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
					<input type="hidden" name="Custo" value="<%=it.getCusto()%>"></input>
					<input type="hidden" name="Margem" value="<%=it.getMargem()%>"></input>
					<input type="hidden" name="altura"
						value="<%=it.getLivro().getDimensoes().getAltura()%>"></input> <input
						type="hidden" name="largura"
						value="<%=it.getLivro().getDimensoes().getLargura()%>"></input> <input
						type="hidden" name="profundidade"
						value="<%=it.getLivro().getDimensoes().getProfundidade()%>"></input>
					<input type="hidden" name="peso"
						value="<%=it.getLivro().getDimensoes().getPeso()%>"></input> <input
						type="hidden" name="Titulo" value="<%=it.getLivro().getTitulo()%>"></input>
					<input type="hidden" name="id_estoque"
						value="<%=it.getIdEstoque()%>"></input> <input type="submit"
						class="button" name="operacao" value="AdicionarCarrinho"
						align="middle">
				</form>
				<form action="/Ecommerc/FichaTecnica" method="post">
					<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
					<input type="submit" class="button" name="operacao"
						value="Detalhes" align="middle">
				</form>
			</div>
			<%
			contador2++;
			contador++;
			continue;
		}
		if(contador == 4 && contador2 != tamanho)
		{
			%>
			<h2>
				<img
					src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
			</h2>
			<p>
				<a><%=it.getLivro().getTitulo()%></a>
			</p>
			<%
    		Double margem = (it.getCusto()*(it.getMargem()/100));
			Double Preco = it.getCusto() + margem;
    		%>
			Preço: R$
			<%=Preco%>
			<form action="/Ecommerc/Carrinho" method="post">
				<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
				<input type="hidden" name="Custo" value="<%=it.getCusto()%>"></input>
				<input type="hidden" name="Margem" value="<%=it.getMargem()%>"></input>
				<input type="hidden" name="Titulo"
					value="<%=it.getLivro().getTitulo()%>"></input> <input
					type="hidden" name="id_estoque" value="<%=it.getIdEstoque()%>"></input>
				<input type="hidden" name="altura"
					value="<%=it.getLivro().getDimensoes().getAltura()%>"></input> <input
					type="hidden" name="largura"
					value="<%=it.getLivro().getDimensoes().getLargura()%>"></input> <input
					type="hidden" name="profundidade"
					value="<%=it.getLivro().getDimensoes().getProfundidade()%>"></input>
				<input type="hidden" name="peso"
					value="<%=it.getLivro().getDimensoes().getPeso()%>"></input> <input
					type="submit" class="button" name="operacao"
					value="AdicionarCarrinho" align="middle">
			</form>
			<form action="/Ecommerc/FichaTecnica" method="post">
				<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
				<input type="submit" class="button" value="Detalhes" align="middle">
			</form>
		</div>
	</div>
	<%
			contador2++;
			contador = 1;
			continue;
		}
		if(contador2 == tamanho && contador == 1)
		{
			%>
	<div class="row">
		<div class="column">
			<h2>
				<img
					src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
			</h2>
			<p>
				<a><%=it.getLivro().getTitulo()%></a>
			</p>
			<%
    		Double margem = (it.getCusto()*(it.getMargem()/100));
			Double Preco = it.getCusto() + margem;
    		%>
			Preço: R$
			<%=Preco%>
			<form action="/Ecommerc/Carrinho" method="post">
				<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
				<input type="hidden" name="Custo" value="<%=it.getCusto()%>"></input>
				<input type="hidden" name="Margem" value="<%=it.getMargem()%>"></input>
				<input type="hidden" name="altura"
					value="<%=it.getLivro().getDimensoes().getAltura()%>"></input> <input
					type="hidden" name="largura"
					value="<%=it.getLivro().getDimensoes().getLargura()%>"></input> <input
					type="hidden" name="profundidade"
					value="<%=it.getLivro().getDimensoes().getProfundidade()%>"></input>
				<input type="hidden" name="peso"
					value="<%=it.getLivro().getDimensoes().getPeso()%>"></input> <input
					type="hidden" name="Titulo" value="<%=it.getLivro().getTitulo()%>"></input>
				<input type="hidden" name="id_estoque"
					value="<%=it.getIdEstoque()%>"></input> <input type="submit"
					class="button" name="operacao" value="AdicionarCarrinho"
					align="middle">
			</form>
			<form action="/Ecommerc/FichaTecnica" method="post">
				<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
				<input type="submit" class="button" name="operacao" value="Detalhes"
					align="middle">
			</form>
		</div>
	</div>
	<%
			continue;
		}
		
		if(contador2 == tamanho && contador != 1)
		{
			%>
	<div class="column">
		<h2>
			<img
				src="https://viverdeblog.com/wp-content/uploads/2017/01/imagens-como-escrever-um-livro-019.png" />
		</h2>
		<p>
			<a><%=it.getLivro().getTitulo()%></a>
		</p>
		<%
    		Double margem = (it.getCusto()*(it.getMargem()/100));
			Double Preco = it.getCusto() + margem;
    		%>
		Preço: R$
		<%=Preco%>
		<form action="/Ecommerc/Carrinho" method="post">
			<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
			<input type="hidden" name="Custo" value="<%=it.getCusto()%>"></input>
			<input type="hidden" name="Margem" value="<%=it.getMargem()%>"></input>
			<input type="hidden" name="altura"
				value="<%=it.getLivro().getDimensoes().getAltura()%>"></input> <input
				type="hidden" name="largura"
				value="<%=it.getLivro().getDimensoes().getLargura()%>"></input> <input
				type="hidden" name="profundidade"
				value="<%=it.getLivro().getDimensoes().getProfundidade()%>"></input>
			<input type="hidden" name="peso"
				value="<%=it.getLivro().getDimensoes().getPeso()%>"></input> <input
				type="hidden" name="Titulo" value="<%=it.getLivro().getTitulo()%>"></input>
			<input type="hidden" name="id_estoque" value="<%=it.getIdEstoque()%>"></input>
			<input type="submit" class="button" name="operacao"
				value="AdicionarCarrinho" align="middle">
		</form>
		<form action="/Ecommerc/FichaTecnica" method="post">
			<input type="hidden" name="Id" value="<%=it.getLivro().getId()%>"></input>
			<input type="submit" class="button" name="operacao" value="Detalhes"
				align="middle">
		</form>
	</div>
	</div>
	<%
			continue;
		}
		
	}
	%>

	</article>

	<footer>Copyright &copy; abcchagas@hotmail.com</footer>

	</div>

</body>
</html>