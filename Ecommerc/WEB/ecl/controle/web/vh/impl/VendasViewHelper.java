package ecl.controle.web.vh.impl;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Estoque;
import ecl.dominio.ItensPedido;
import ecl.dominio.Livro;
import ecl.dominio.Pedidos;
import ecl.dominio.Cliente;
import ecl.dominio.Cupom;
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




public class VendasViewHelper implements IViewHelper {

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Pedidos pedidos = new Pedidos();
		
			String operacao = request.getParameter("operacao");
			System.out.println(" EU SOU ESSE CARA = "+ operacao );
			
		
		if(operacao.equals("VisualizarCliente"))
		{
			

			
		}
		if(operacao.equals("Detalhes"))
		{
			String id = request.getParameter("Id");
			Livro livro = new Livro();
			ItensPedido item = new ItensPedido();
			List<ItensPedido> itens = new ArrayList<ItensPedido>();
			livro.setId(Integer.parseInt(id));
			item.setLivro(livro);
			itens.add(item);
			pedidos.setItens(itens);
			

			
		}
		if(operacao.equals("CalcularFrete"))
		{
			Pedidos p = new Pedidos();
			Endereco endereco = new Endereco();
			Cliente cliente = new Cliente();
			String cep = request.getParameter("Cep");
			Object obj2 = request.getSession().getAttribute("idCliente");
			int id = Integer.class.cast(obj2);
			endereco.setCep(cep);
			cliente.setId(id);
			Object obj = new Object();
			double Total = 0;
			obj = request.getSession().getAttribute("Carrinho");
			if(obj != null)
			{
				p = Pedidos.class.cast(obj);
			}	
			p.setEndereco(endereco);
			p.setCliente(cliente);
			return p;

			
		}
		
		if(operacao.equals("FormaDePagamento"))
		{
			Pedidos p = new Pedidos();
			Cliente cli = new Cliente();
			Object obj = request.getSession().getAttribute("idCliente");
			String id = obj.toString();				
			cli.setId(Integer.parseInt(id));
			System.out.println("ID CLIENTE SALVO = " + cli.getId());
			p.setCliente(cli);
			return p;

			
		}
		if(operacao.equals("AdicionarCarrinho"))
		{
			Object obj = request.getSession().getAttribute("Carrinho");
			Pedidos ped = new Pedidos();
			String id = request.getParameter("Id");
			String titulo = request.getParameter("Titulo");
			Double preco = Double.parseDouble(request.getParameter("Custo"));
			Double margem = Double.parseDouble(request.getParameter("Margem"));
			int estoque = Integer.parseInt(request.getParameter("id_estoque"));
			Dimensoes dimensao = new Dimensoes();
			double Altura = Double.parseDouble(request.getParameter("altura"));
			double Largura = Double.parseDouble(request.getParameter("largura"));
			double Peso = Double.parseDouble(request.getParameter("peso"));
			double profundidade = Double.parseDouble(request.getParameter("profundidade"));
			dimensao.setAltura(Altura);
			dimensao.setLargura(Largura);
			dimensao.setPeso(Peso);
			dimensao.setProfundidade(profundidade);
			Livro livro = new Livro();
			livro.setTitulo(titulo);
			livro.setId(Integer.parseInt(id));
			ItensPedido item = new ItensPedido();
			List<ItensPedido> itens = new ArrayList<ItensPedido>();
			item.setCusto(preco);
			item.setMargem(margem);
			item.setIdEstoque(estoque);
			item.setQuantidade(1);
			double Volume = (Altura * Largura * profundidade) * item.getQuantidade();
			dimensao.setVolume(Volume);
			livro.setDimensoes(dimensao);
			item.setLivro(livro);
			itens.add(item);
			ped.setItens(itens);
			if(obj == null)
			{
				ped.setIndex(0);
				request.getSession().setAttribute("Carrinho", ped);
				System.out.println("Não foi criado o carrinho ainda");
			}
			else
			{
				ped = Pedidos.class.cast(obj);
				itens = ped.getItens();
				itens.add(item);
				ped.setItens(itens);
				ped.setIndex(ped.getItens().size() - 1);
				request.getSession().setAttribute("Carrinho", ped);
				System.out.println("Carrinho já existe");
			}
			
			
			return ped;			

			
		}
		
		if(operacao.equals("AumentarCarrinho"))
		{
			Object obj = request.getSession().getAttribute("Carrinho");
			Pedidos ped = new Pedidos();
			int Index = Integer.parseInt(request.getParameter("Index"));
			Livro livro = new Livro();
			ItensPedido item = new ItensPedido();
			List<ItensPedido> itens = new ArrayList<ItensPedido>();
			ped = Pedidos.class.cast(obj);
			ped.setIndex(Index);
			int quantidade = ped.getItens().get(Index).getQuantidade() + 1;
			ped.getItens().get(Index).setQuantidade(quantidade);
			ped.getItens().get(Index).getLivro().getDimensoes().setVolume((ped.getItens().get(Index).getLivro().getDimensoes().getAltura() * ped.getItens().get(Index).getLivro().getDimensoes().getLargura() * ped.getItens().get(Index).getLivro().getDimensoes().getProfundidade()) * ped.getItens().get(Index).getQuantidade());
			request.getSession().setAttribute("Carrinho", ped);
			System.out.println("Carrinho já existe");
			System.out.println("Tamanho = " + ped.getItens().size());
			System.out.println("Index = " + Index);
			return ped;
			}
		if(operacao.equals("FinalizarCompra"))
		{
			List<Cupom> cupons = new ArrayList<Cupom>();
			List<Cartao> cartoes = new ArrayList<Cartao>();
			Object obj = request.getSession().getAttribute("Carrinho");
			Pedidos ped = new Pedidos();
			double Troco = Double.parseDouble(request.getParameter("Troco"));
			double Desconto = Double.parseDouble(request.getParameter("Desconto"));
			double Total = Double.parseDouble(request.getParameter("Total"));
			String[] cuponsString = request.getParameterValues("cupom");
			String[] cartoesString = request.getParameterValues("cartao");
			for (String c: cuponsString) {           
				Cupom cup = new Cupom();
				cup.setId(Integer.parseInt(c));
				cupons.add(cup);
		    }
			for (String r: cartoesString) {           
				Cartao car = new Cartao();
				car.setId(Integer.parseInt(r));
				cartoes.add(car);
		    }
			ped = Pedidos.class.cast(obj);
			ped.setTroco(Troco);
			ped.setTotal(Total);
			ped.setDesconto(Desconto);
			ped.setCartoes(cartoes);
			ped.setCupons(cupons);
			request.getSession().setAttribute("Carrinho", ped);
			return ped;
			}
		
		if(operacao.equals("DiminuirCarrinho"))
		{
			Object obj = request.getSession().getAttribute("Carrinho");
			Pedidos ped = new Pedidos();
			int Index = Integer.parseInt(request.getParameter("Index"));
			Livro livro = new Livro();
			ItensPedido item = new ItensPedido();
			List<ItensPedido> itens = new ArrayList<ItensPedido>();
			ped = Pedidos.class.cast(obj);
			ped.setIndex(Index);
			int quantidade = ped.getItens().get(Index).getQuantidade() - 1;
			ped.getItens().get(Index).setQuantidade(quantidade);
			ped.getItens().get(Index).getLivro().getDimensoes().setVolume((ped.getItens().get(Index).getLivro().getDimensoes().getAltura() * ped.getItens().get(Index).getLivro().getDimensoes().getLargura() * ped.getItens().get(Index).getLivro().getDimensoes().getProfundidade()) * ped.getItens().get(Index).getQuantidade());
			request.getSession().setAttribute("Carrinho", ped);
			System.out.println("Carrinho já existe");
			System.out.println("Tamanho = " + ped.getItens().size());
			System.out.println("Index = " + Index);
			return ped;
		}
		
		if(operacao.equals("Remover"))
		{
			Object obj = request.getSession().getAttribute("Carrinho");
			Pedidos ped = new Pedidos();
			int Index = Integer.parseInt(request.getParameter("Index"));
			List<ItensPedido> itens = new ArrayList<ItensPedido>();
			ped = Pedidos.class.cast(obj);
			ped.setIndex(Index);
			int quantidade = ped.getItens().get(Index).getQuantidade();
			int idEstoque =  ped.getItens().get(Index).getIdEstoque();
			int contador = 0;
			for(ItensPedido it : ped.getItens())
			{
				if(contador != Index)
				{
					itens.add(it);
				}
				contador++;
			}
			ped.setItens(itens);
			ped.setIndex(idEstoque);
			ped.setQtde(quantidade);
			request.getSession().setAttribute("Carrinho", ped);
			System.out.println("Carrinho já existe");
			System.out.println("Tamanho = " + ped.getItens().size());
			System.out.println("Index = " + Index);
			return ped;
		}
		return pedidos;
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
		
		if(resultado.getMsg() == null && operacao.trim().equals("VisualizarCliente")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  
		}
		
		if(operacao.equals("FormaDePagamento")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/FormaDePagamento.jsp");  	
		}
		
		if(operacao.equals("FinalizarCompra")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  	
		}
		
		if(resultado.getMsg() == null && operacao.trim().equals("CalcularFrete")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/FreteEndereco.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.trim().equals("Detalhes")){
			request.setAttribute("resultado", resultado);
			d= request.getRequestDispatcher("/FichaTecnica.jsp");  
		}
		if(resultado.getMsg() == null && operacao.trim().equals("AdicionarCarrinho")){

			d= request.getRequestDispatcher("/Carrinho.jsp");  
		}
		if(resultado.getMsg() == null && operacao.trim().equals("AumentarCarrinho")){

			d= request.getRequestDispatcher("/Carrinho.jsp");  
		}
		if(resultado.getMsg() == null && operacao.trim().equals("DiminuirCarrinho")){

			d= request.getRequestDispatcher("/Carrinho.jsp");  
		}
		if(resultado.getMsg() == null && operacao.trim().equals("Remover")){

			d= request.getRequestDispatcher("/Carrinho.jsp");  
		}


		}else{
			if(operacao.equals("VisualizarCliente")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  	
			}
			if(operacao.equals("FormaDePagamento")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/FormaDePagamento.jsp");  	
			}
			
			if(operacao.equals("Detalhes")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/FichaTecnica.jsp");  	
			}
			
			if(operacao.equals("FinalizarCompra")){
				request.setAttribute("resultado", resultado);
				if(resultado.getMsg().trim().equals("1"))
				{
				d= request.getRequestDispatcher("/FormaDePagamento.jsp");  	
				}else
				{
					d= request.getRequestDispatcher("/CartoesMultiplos.jsp");	
				}
			}
			if(operacao.equals("AdicionarCarrinho")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  	
			}
			if(operacao.equals("AumentarCarrinho")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  	
			}
			if(operacao.equals("DiminuirCarrinho")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  	
			}
			if(operacao.equals("Remover")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/ConsultarLivroCliente.jsp");  	
			}
			if(operacao.trim().equals("CalcularFrete")){
				request.setAttribute("resultado", resultado);
				d= request.getRequestDispatcher("/FreteEndereco.jsp");  
			}
			
		}
		

		d.forward(request,response); 
		
	}
		
}


