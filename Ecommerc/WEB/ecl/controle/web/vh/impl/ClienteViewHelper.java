package ecl.controle.web.vh.impl;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Estoque;
import ecl.dominio.ItensPedido;
import ecl.dominio.Pedidos;
import ecl.dominio.Cliente;
import ecl.dominio.Precificacao;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import ecl.core.aplicacao.*;
import ecl.controle.web.vh.IViewHelper;
import ecl.dominio.Cartao;
import ecl.dominio.Bandeira;
import ecl.dominio.Dimensoes;
import ecl.dominio.Editora;
import ecl.dominio.Endereco;




public class ClienteViewHelper implements IViewHelper {

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cliente cliente = new Cliente();
		
			String operacao = request.getParameter("operacao");
			System.out.println(" EU SOU ESSE CARA = "+ operacao );
			
		if(operacao.trim().equals("alterar"))
		{

		String Nome = request.getParameter("Nome");
		String cpf = request.getParameter("Cpf");
		String DataNascimento = request.getParameter("Datanascimento");
		Object obj = new Object();
		obj = request.getSession().getAttribute("idCliente");
		int id = Integer.class.cast(obj);
		String sexo = request.getParameter("Sexo");
		String DDD = request.getParameter("DDD");
		String telefone = request.getParameter("Telefone");
		String email = request.getParameter("Email");
		//Lembrete Converter dados não String
		if(id != 0){
			cliente.setId(id);
		}
		
		if(cpf != null && !cpf.trim().equals("")){
			
			cliente.setCpf(cpf);
		}

		
		if(DataNascimento != null && !DataNascimento.trim().equals("")){
			cliente.setDataNascimento(DataNascimento);
			
		}
		
		if(Nome != null && !Nome.trim().equals("")){
			cliente.setNome(Nome);
		}
		else
		{
			cliente.setNome("");
		}
		
		if(telefone != null && !telefone.trim().equals("")){
			cliente.setTelefone(telefone);
		}
		
		if(email != null && !email.trim().equals("")){
			cliente.setEmail(email);
		}
		if(DDD != null && !DDD.trim().equals("")){
			cliente.setDDD(DDD);
		}
		if(email != null && !email.trim().equals("")){
			cliente.setEmail(email);
		}
		
		
		}// if_Alterar
		
		if(operacao.equals("AlterarSenha"))
		{
			Object obj = new Object();
			obj = request.getSession().getAttribute("idCliente");
			int id = Integer.class.cast(obj);
			
			String senha = request.getParameter("Senha");
			String ConfirmarSenha = request.getParameter("ConfirmarSenha");
			
			if(id != 0){
				cliente.setId(id);
			}
			
			if(senha != null && !senha.trim().equals("")){
				
				cliente.setSenha(senha);
			}
			if(ConfirmarSenha != null && !ConfirmarSenha.trim().equals("")){
				
				cliente.setConfirmarSenha(ConfirmarSenha);
			}
			
		}
		
		if(operacao.equals("Login"))
		{
		
			String senha = request.getParameter("Senha");
			String Usuario = request.getParameter("Usuario");			
			if(senha != null && !senha.trim().equals("")){
				
				cliente.setSenha(senha);
			}
			else
			{
				cliente.setSenha("");
			}
			if(Usuario != null && !Usuario.trim().equals("")){
				
				cliente.setEmail(Usuario);
			}
			else
			{
				cliente.setEmail("");
			}
			
		}
		
		if(operacao.equals("CalcularFrete"))
		{
			Pedidos p = new Pedidos();
			Endereco endereco = new Endereco();
			Cliente cli = new Cliente();
			String cep = request.getParameter("Cep");
			int idEnd= Integer.parseInt(request.getParameter("Id"));
			Object obj2 = request.getSession().getAttribute("idCliente");
			int id = Integer.class.cast(obj2);
			endereco.setCep(cep);
			endereco.setId(idEnd);
			cli.setId(id);
			Object obj = new Object();
			double Total = 0;
			obj = request.getSession().getAttribute("Carrinho");
			if(obj != null)
			{
				p = Pedidos.class.cast(obj);
			}	
			p.setEndereco(endereco);
			p.setCliente(cli);
			request.getSession().setAttribute("Carrinho", p);
			return p;

			
		}

		if(operacao.equals("buscarCEP") || operacao.equals("AlterarEndereco"))
		{
			int idendereco = Integer.parseInt(request.getParameter("Id"));
			String CEP = request.getParameter("cep");
			String Estado = request.getParameter("Estado");
			String Logradouro = request.getParameter("Logradouro");
			String Bairro = request.getParameter("Bairro");
			String Cidade = request.getParameter("Cidade");
			String Entrega = request.getParameter("Entrega");
			String Cobranca = request.getParameter("Cobranca");
			int Numero = Integer.parseInt(request.getParameter("Numero"));
			Endereco endereco = new Endereco();
			
			if(Numero != 0){
				
				endereco.setNumero(Numero);
				cliente.setEndereco(endereco);
			}
			else
			{
				endereco.setNumero(0);
				cliente.setEndereco(endereco);
			}
			if(idendereco != 0){
				
				endereco.setId(idendereco);
				cliente.setEndereco(endereco);
				System.out.println("Id do endereco = " + idendereco);
			}
			if(CEP != null && !CEP.trim().equals("")){
				
				endereco.setCep(CEP);
				cliente.setEndereco(endereco);
			}
			else
			{
				endereco.setCep("");
				cliente.setEndereco(endereco);
			}
			if(Entrega != null && !Entrega.trim().equals("")){
				
				endereco.setEntrega("NÃO");
				cliente.setEndereco(endereco);
			}
			else
			{
				endereco.setEntrega("SIM");
				cliente.setEndereco(endereco);
			}
			if(Cobranca != null && !Cobranca.trim().equals("")){
				
				endereco.setCobranca("NÃO");
				cliente.setEndereco(endereco);
			}
			else
			{
				endereco.setCobranca("SIM");
				cliente.setEndereco(endereco);
			}
			if(Estado != null && !Estado.trim().equals("")){
				
				endereco.setEstado(Estado);
				cliente.setEndereco(endereco);
			}
			else
			{
				endereco.setEstado("");
				cliente.setEndereco(endereco);
			}
			if(Logradouro != null && !Logradouro.trim().equals("")){
				
				endereco.setLogradouro(Logradouro);
				cliente.setEndereco(endereco);
			}
			else
			{
				endereco.setLogradouro("");
				cliente.setEndereco(endereco);	
			}
			if(Bairro != null && !Bairro.trim().equals("")){
				
				endereco.setBairro(Bairro);
				cliente.setEndereco(endereco);
			}
			if(Cidade != null && !Cidade.trim().equals("")){
				
				endereco.setCidade(Cidade);
				cliente.setEndereco(endereco);
			}
			
		}
		
		if(operacao.equals("AlterarCartao"))
		{
			String id = request.getParameter("Id");	//Aqui tem que ser a session Tem que ter o id do cartao
			String Nome = request.getParameter("NomeCartao");
			String Numero = request.getParameter("NumeroCartao");
			int idbandeira = Integer.parseInt(request.getParameter("Bandeira"));
			String codigoSeguranca = request.getParameter("CodigoCartao");
			String Preferencial = request.getParameter("Preferencial");
			Cartao cartao = new Cartao();
			Bandeira bandeira = new Bandeira();
			
			if(id != null && !id.trim().equals("")){
				cartao.setId(Integer.parseInt(id));
			}
			if(Preferencial != null && !Preferencial.trim().equals("")){
				cartao.setPreferencial("SIM");
			}
			else
			{
				cartao.setPreferencial("NÃO");
			}
			if(Nome != null && !Nome.trim().equals("")){
				
				cartao.setNomeCartao(Nome);
				System.out.println("Nome no cartao = " + Nome);
			}
			if(Numero != null && !Numero.trim().equals("")){
				
				cartao.setNumero(Numero);
			}
			if(codigoSeguranca != null && !codigoSeguranca.trim().equals("")){
				
				cartao.setCodigoDeSeguranca(Integer.parseInt(codigoSeguranca));
			}
			if(idbandeira != 0){
				
				bandeira.setId(idbandeira);
			}
			cartao.setBandeira(bandeira);
			cliente.setCartao(cartao);
			
		}
		if(operacao.equals("AlterarSenha"))
		{
			Object obj = request.getSession().getAttribute("idCliente");
			String id = obj.toString();	
			String Senha = request.getParameter("NovaSenha");
			String SenhaNovamente = request.getParameter("NovaSenhaConfirmacao");
			
			if(id != null && !id.trim().equals("")){
				cliente.setId(Integer.parseInt(id));
			}
			if(Senha != null && !Senha.trim().equals("")){
				cliente.setSenha(Senha);
			}
			else
			{
				cliente.setSenha("");
			}
			if(SenhaNovamente != null && !SenhaNovamente.trim().equals("")){
				
				cliente.setConfirmarSenha(SenhaNovamente);

			}
			else
			{
				cliente.setConfirmarSenha("");
			}
			

			
		}
		
		if(operacao.equals("VisualizarEndereco"))
		{
			Object obj = request.getSession().getAttribute("idCliente");
			String id = obj.toString();				
			if(id != null && !id.trim().equals("")){
				cliente.setId(Integer.parseInt(id));
			}
		}
		
		if(operacao.equals("CalcularFrete"))
		{
			Object obj = request.getSession().getAttribute("idCliente");
			String id = obj.toString();				
			if(id != null && !id.trim().equals("")){
				cliente.setId(Integer.parseInt(id));
			}
		}
		return cliente;
	}

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 */
	public void setView(Result resultado, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		System.out.println("MENSAGEM FINAL :" + resultado.getMsg());
		System.out.println("OPERACAO FINAL :" + operacao);
		if(resultado.getMsg() == null) {
		
		if(resultado.getMsg() == null && operacao.trim().equals("AlterarSenha")){
				d= request.getRequestDispatcher("/AlterarSenhaResposta.jsp");  	
			}
		if(resultado.getMsg() == null && operacao.trim().equals("CalcularFrete")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/FreteEndereco.jsp");  
		}
		if(resultado.getMsg() == null && operacao.trim().equals("VisualizarEndereco")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/FreteEndereco.jsp");  	
		}
		
		if(resultado.getMsg() == null && operacao.trim().equals("CalcularFrete")){
			Pedidos pedidos = new Pedidos();
			Endereco pedido = (Endereco)resultado.getEntidades().get(0);
			System.out.println("Frete no VH igual a = " + pedido.getFrete());
			Object obj2 = new Object();
			double Total = 0;
			obj2 = request.getSession().getAttribute("Carrinho");
			if(obj2 != null)
			{
				pedidos = Pedidos.class.cast(obj2);
				for(ItensPedido s: pedidos.getItens()){
					Double margem = (s.getCusto()*(s.getMargem()/100));
					Double Preco = s.getCusto() + margem;
					Total = Total + ((Preco)*s.getQuantidade());
														}
			}
			pedidos.setTotal(Total + pedido.getFrete());
			request.getSession().setAttribute("Carrinho", pedidos);
			d= request.getRequestDispatcher("/FreteEndereco.jsp");  	
		}
		if(resultado.getMsg() == null && operacao.trim().equals("buscarCEP")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/AlterarEnderecoResposta.jsp");  	
		}
		if(resultado.getMsg() == null && operacao.trim().equals("AlterarEndereco")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/AlterarEnderecoResposta.jsp");  	
		}
		if(resultado.getMsg() == null && operacao.trim().equals("AlterarCartao")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/AlterarCartaoResposta.jsp");  	
		}
		if(resultado.getMsg() == null && operacao.trim().equals("Login")){
			request.getSession().setAttribute("idCliente", resultado.getEntidades().get(0).getId());
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/MenuCliente.jsp");  	
		}
		if(resultado.getMsg() == null && operacao.trim().equals("salvar")){
			d= request.getRequestDispatcher("/ConsultarLivro.jsp");  	
		}
		
		
		if(resultado.getMsg() == null && operacao.equals("Inativar")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/ConfirmarInativarLivro.jsp");  			
		}
		if(resultado.getMsg() == null && operacao.equals("Ativar")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/ConfirmarAtivarLivro.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("consultar")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/ConsultarResposta.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("visualizar")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/AlterarLivro.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("alterar")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/AlterarClienteResposta.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("inativar")){
			d= request.getRequestDispatcher("/ConsultarLivro.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("EntradaEstoque")){
			d= request.getRequestDispatcher("/ConsultarLivro.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("ativar")){
			d= request.getRequestDispatcher("/ConsultarLivro.jsp");  			
		}

		}else{
			if(operacao.equals("salvar")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/CadastrarLivro.jsp");  	
			}
			
			if(operacao.equals("alterar")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/AlterarClienteResposta.jsp");  	
			}
			
			if(operacao.equals("consultar")){
				d= request.getRequestDispatcher("ConsultarLivro.jsp");  	
			}
			
			if(operacao.equals("inativar")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConfirmarInativarLivro.jsp");  			
			}
			if(operacao.equals("ativar")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConfirmarAtivarLivro.jsp");  			
			}
			if(operacao.equals("AlterarCartao")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConfirmarInativarLivro.jsp");  
			}
		}
		

		d.forward(request,response); 
		
	}
		
}

