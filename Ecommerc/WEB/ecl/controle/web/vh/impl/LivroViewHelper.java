package ecl.controle.web.vh.impl;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Estoque;
import ecl.dominio.Livro;
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
import ecl.dominio.Autor;
import ecl.dominio.Categoria;
import ecl.dominio.Dimensoes;
import ecl.dominio.Editora;




public class LivroViewHelper implements IViewHelper {

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Livro livro = new Livro();
			Categoria categorias = new Categoria();
			Autor autor = new Autor();
			Editora edit = new Editora();
			String operacao = request.getParameter("operacao");
			System.out.println(operacao + " EU SOU ESSE CARA");
		if((operacao.trim().equals("salvar")) || (operacao.trim().equals("alterar")))
		{
		List<Categoria> IdCategoria = new ArrayList<Categoria>();
		String[] Categoria = request.getParameterValues("Categoria");
		String Titulo = request.getParameter("Titulo");
		String Ano = request.getParameter("Ano");
		System.out.println("O ano é igual a:" + Ano);
		String Autor = request.getParameter("Autor");
		String id = request.getParameter("Id");
		String Sinopse = request.getParameter("Sinopse");
		String ISBN = request.getParameter("ISBN");
		String CodigoDeBarras = request.getParameter("CodigoDeBarras");
		String Precificacao = request.getParameter("Precificacao");
		//Lembrete Converter dados não String
		String Edicao = request.getParameter("Edicao");
		String Editora = request.getParameter("Editora");
		String NumeroPaginas = request.getParameter("NumeroPaginas");
		String Altura = request.getParameter("Altura");
		String Largura = request.getParameter("Largura");
		String Peso = request.getParameter("Peso");
		String Profundidade = request.getParameter("Profundidade");
		Dimensoes dimensoes = new Dimensoes();
		if(id != null && !id.trim().equals("")){
			livro.setId(Integer.parseInt(id));
		}
		
		if(Autor != null && !Autor.trim().equals("")){
			autor.setId(Integer.parseInt(Autor));
			livro.setAutor(autor);
			System.out.println("Eu sou o autor: " + Autor);
		}

		if(Categoria != null){
			for(int i = 0; i < Categoria.length; i++) {
				System.out.println(Categoria[i]);
				categorias.setId(Integer.parseInt(Categoria[i]));
				IdCategoria.add(categorias);
			}
			livro.setIdcategoria(IdCategoria);
		}
		
		if(Ano != null && !Ano.trim().equals("")){
			livro.setAno(Integer.parseInt(Ano));
			
		}
		
		if(Titulo != null && !Titulo.trim().equals("")){
			livro.setTitulo(Titulo);
		}
		
		if(Editora != null && !Editora.trim().equals("")){
			edit.setId(Integer.parseInt(Editora));
			livro.setEditora(edit);
		}
		
		if(Edicao != null && !Edicao.trim().equals("")){
			livro.setEdicao(Integer.parseInt(Edicao));
		}
		
		if(ISBN != null && !ISBN.trim().equals("")){
			livro.setISBN(ISBN);
		}
		
		if(NumeroPaginas != null && !NumeroPaginas.trim().equals("")){
			livro.setNumeroPaginas(Integer.parseInt(NumeroPaginas));
		}
		
		if(Altura != null && !Altura.trim().equals("")){
			dimensoes.setAltura(Double.parseDouble(Altura));
		}
		
		if(Largura != null && !Largura.trim().equals("")){
			dimensoes.setLargura(Double.parseDouble(Largura));
		}
		
		if(Peso != null && !Peso.trim().equals("")){
			dimensoes.setPeso(Double.parseDouble(Peso));
		}
		if(Profundidade != null && !Profundidade.trim().equals("")){
			dimensoes.setProfundidade(Double.parseDouble(Profundidade));
		}
		
		if(Sinopse != null && !Sinopse.trim().equals("")) {
			livro.setSinopse(Sinopse);
		}
		
		livro.setDimensoes(dimensoes);
		
		if(Precificacao != null && !Precificacao.trim().equals("")) {
			Precificacao prec = new Precificacao();
			prec.setTipo(Integer.parseInt(Precificacao));
			livro.setPrecificacao(prec);
		}
		
		if(CodigoDeBarras != null && !CodigoDeBarras.trim().equals("")) {
			livro.setCodigoBarras(CodigoDeBarras);
		}
		
		}// if_salvar
		
		if(operacao.equals("consultar"))
		{
			List<Categoria> IdCategoria = new ArrayList<Categoria>();
			Autor autors = new Autor();
			Editora edits = new Editora();
			String[] Categoria = request.getParameterValues("Categoria");
			String Titulo = request.getParameter("Titulo");
			String Autor = request.getParameter("Autor");
			String Editora = request.getParameter("Editora");
			String CodigoDeBarras = request.getParameter("CodigoDeBarras");
			
			if(Autor != null && !Autor.trim().equals("")){
				autors.setId(Integer.parseInt(Autor));
				livro.setAutor(autors);
			}

			if(CodigoDeBarras != null && !CodigoDeBarras.trim().equals("")) {
				livro.setCodigoBarras(CodigoDeBarras);
			}else {livro.setCodigoBarras("");}
			
			if(Categoria != null){
				for(int i = 0; i < Categoria.length; i++) {
					System.out.println(Categoria[i]);
					categorias.setId(Integer.parseInt(Categoria[i]));
					IdCategoria.add(categorias);
				}
				livro.setIdcategoria(IdCategoria);
			}
			
			
			if(Titulo != null && !Titulo.trim().equals("")){
				livro.setTitulo(Titulo);
			}else {livro.setTitulo("");}
			
			if(Editora != null && !Editora.trim().equals("")){
				edits.setId(Integer.parseInt(Editora));
				livro.setEditora(edits);
			}
			
			
		}
		
		if(operacao.equals("Inativar") || operacao.equals("Ativar"))
		{
			String id = request.getParameter("Id");			
			if(id != null && !id.trim().equals("")){
				livro.setId(Integer.parseInt(id));
				System.out.println(livro.getId());
			}
			String Titulo = request.getParameter("Titulo");
			
			if(Titulo != null && !Titulo.trim().equals("")){
				livro.setTitulo(Titulo);
			}
			
		}
		
		if(operacao.equals("visualizar"))
		{
			String id = request.getParameter("Id");			
			if(id != null && !id.trim().equals("")){
				livro.setId(Integer.parseInt(id));
				System.out.println(livro.getId());
			}			
		}
		
		if(operacao.equals("inativar") || operacao.equals("ativar"))
		{
			String id = request.getParameter("Id");			
			if(id != null && !id.trim().equals("")){
				livro.setId(Integer.parseInt(id));
			}
			
			String Titulo = request.getParameter("Titulo");
			
			if(Titulo != null && !Titulo.trim().equals("")){
				livro.setTitulo(Titulo);
			}
			String Justificativa = request.getParameter("Jusfiticativa");
			
			if(Justificativa != null && !Justificativa.trim().equals("")){
				livro.setJustificativa(Justificativa);
			}
			
			String CategoriaJustificativa = request.getParameter("CategoriaJustificativa");			
			if(id != null && !id.trim().equals("CategoriaJustificativa")){
				livro.setIdCatJustificativa(Integer.parseInt(CategoriaJustificativa));
			}
			
			
		}
		
		if(operacao.equals("EntradaEstoque"))
		{
			String id = request.getParameter("Id");	
			int qtd = 0;
			if(id != null && !id.trim().equals("")){
				livro.setId(Integer.parseInt(id));
				System.out.println(livro.getId());
			}	
			String Quantidade = request.getParameter("QuantidadeLivros");
			if(Quantidade != null && !Quantidade.trim().equals(""))
			{
				qtd = Integer.parseInt(Quantidade);
			}
			
			String Custo = request.getParameter("Custo");
			Double cts = Double.parseDouble(Custo);
			
			Estoque estoque = new Estoque();
			estoque.setCusto(cts);
			estoque.setQuantidade(qtd);
			livro.setEstoque(estoque);
			
			Precificacao preci= new Precificacao();
			String idPrecificacao = request.getParameter("Precificacao");
			int idpreci = Integer.parseInt(idPrecificacao);
			preci.setTipo(idpreci);
			livro.setPrecificacao(preci);
		
			
		}
		return livro;
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
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		System.out.println("MENSAGEM FINAL :" + resultado.getMsg());
		System.out.println("OPERACAO FINAL :" + operacao);
		if(resultado.getMsg() == null) {
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
			d= request.getRequestDispatcher("/ConsultarLivro.jsp");  			
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
				d= request.getRequestDispatcher("ConsultarLivro.jsp");  	
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
		}
		

		d.forward(request,response); 
		
	}
		
}

