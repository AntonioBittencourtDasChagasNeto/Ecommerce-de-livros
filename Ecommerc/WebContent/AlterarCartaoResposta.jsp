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
<%@ page import="ecl.dominio.Cliente"%>
<%@ page import="ecl.core.aplicacao.Result"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecl.dominio.EntidadeDominio"%>
<%@ page import="ecl.dominio.Bandeira"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Alterar Cartão</title>

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
	height: 560px;
	border-color: orange;
}

input {
	border: solid 1px;
	font-size: 17px;
	color: #7f7e7e;
}

button {
	margin-left: 180px;
	margin-top: -70px;
}

.Fieldset3 {
	width: 300px;
	height: 460px;
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
	request.removeAttribute("resultado");
	%>
	<h2>Minha Conta</h2>
	<div class="col-md-12">

		<jsp:include page="OpcoesCliente.jsp" />

		<div class="col-md-8">
			<form action="/Ecommerc/AlterarCartaoResposta" method="post">
				<fieldset class="Fieldset2">

					<h3>Alterar Cartão</h3>

					<fieldset class="Fieldset3">
						<legend class="legend"> Cartão de Crédito </legend>

						<p>Número</p>
						<input type="number" name="NumeroCartao" id="NumeroCartao"
							value="<%=clientes.get(0).getCartao().getNumero()%>">

						<p>Nome no Cartão</p>
						<input type="text" name="NomeCartao" id="NomeCartao"
							value="<%=clientes.get(0).getCartao().getNomeCartao()%>">

						<p>Bandeira</p>
						<select name="Bandeira" id="Bandeira">
							<%
                            try
                            {
                            	String sql = "SELECT * FROM bandeira";
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
                            		if(rs.getInt("idbandeira") != clientes.get(0).getCartao().getBandeira().getId()){
                            		%>
							<option value="<%=rs.getInt("idbandeira")%>"><%=rs.getString("bandeiraNome") %></option>
							<%
                            		}
                            		else{
                            			%>
							<option
								value="<%=clientes.get(0).getCartao().getBandeira().getId()%>"
								selected><%=rs.getString("bandeiraNome") %></option>
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

						<p>Código de Segurança</p>
						<input type="password" name="CodigoCartao" id="CodigoCartao"
							value="<%=clientes.get(0).getCartao().getCodigoDeSeguranca()%>">
						<br> <label> *O cartão será utilizado inicialmente
							como seu preferencial, mas poderá ser alterado no perfil.</label> <br>
						<%
									if(clientes.get(0).getCartao().getPreferencial().equals("SIM"))
									{
									%>
						<p>
							<input type="checkbox" name="checkbox" checked="checked">
							Preferencial
						</p>
						<%
									}
									else
									{
									 %>
						<p>
							<input type="checkbox" name="checkbox"> Preferencial
						</p>
						<%
									}
									 %>
						<input type="hidden" name="Id"
							value="<%=clientes.get(0).getCartao().getId()%>"></input>
						<button type="submit" name="operacao" id="botao"
							value="AlterarCartao">Alterar</button>
					</fieldset>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>