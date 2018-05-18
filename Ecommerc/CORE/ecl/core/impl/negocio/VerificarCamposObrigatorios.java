package ecl.core.impl.negocio;
import ecl.core.*;
import ecl.dominio.Autor;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Livro;
import ecl.dominio.Precificacao;

public class VerificarCamposObrigatorios implements IStrategy{

	private String msg;
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Livro){
			Livro livro = (Livro)entidade;
			this.msg = "";
			System.out.println("Eu entrei na regra de negocio");
			System.out.println("Titulo" + livro.getTitulo());
			System.out.println("Sinopse" + livro.getSinopse());
			System.out.println("Autor" + livro.getAutor().getId());
			System.out.println("Código de barras" + livro.getCodigoBarras());
			System.out.println("Profundidade" + livro.getDimensoes().getProfundidade());
			System.out.println("Ano" + livro.getAno());
			System.out.println("Precificacao" + livro.getPrecificacao().getTipo());
			System.out.println("Edicao" + livro.getEdicao());
			System.out.println("ISBN" + livro.getISBN());
			System.out.println("Paginas" + livro.getNumeroPaginas());
			System.out.println("Editora" + livro.getEditora().getId());
			
			try {
				if(livro.getTitulo().trim().equals("") || livro.getTitulo() == null)
				{
					this.msg = this.msg + "Campo titulo não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				livro.setTitulo("");
				this.msg = this.msg + "Campo titulo não digitado";
			}
			try {
				if(livro.getSinopse().trim().equals("") || livro.getSinopse() == null)
				{
					this.msg =  this.msg + "Campo sinopse não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				livro.setSinopse("");
				this.msg =  this.msg + "Campo sinopse não digitado";
			}
			try {
				if(livro.getAutor().getId() == 0)
				{
					System.out.println("Eu entrei no try do autor e eu sou" + livro.getAutor().getId());
					this.msg =  this.msg + "Campo Autor não selecionado";
				}
				
			}catch(java.lang.NullPointerException e) {
				Autor auto = new Autor();
				auto.setId(1);
				livro.setAutor(auto);
				this.msg =  this.msg + "Campo Autor não selecionado";
			}
			
			try {
				if(livro.getCodigoBarras().trim().equals("") || livro.getCodigoBarras() == null)
				{
					this.msg =  this.msg + "Campo Código de barras não selecionado";
				}
				
			}catch(java.lang.NullPointerException e) {
				livro.setCodigoBarras("");
				this.msg =  this.msg + "campo Código de Barras não digitado";
			}

			 if(livro.getDimensoes().getAltura() == 0){
				 	this.msg = this.msg + " campo Altura não digitado";
				 			  }
			 if(livro.getDimensoes().getLargura() == 0){
				 	this.msg = this.msg + " campo Largura não digitado";
				 			  }
			 if(livro.getDimensoes().getPeso() == 0){
				 	this.msg = this.msg + " campo Peso não digitado";
				 			  }
			 if(livro.getDimensoes().getProfundidade() == 0){
				 	this.msg = this.msg + " campo Profundidade não selecionado";
				 			  }
			 if(livro.getEdicao() == 0){
				 	this.msg = this.msg + " campo Edição não digitado";
				 			  }
				try {
					if(livro.getISBN().trim().equals("") || livro.getISBN() == null)
					{
						this.msg = this.msg + " campo ISBN não digitado";
					}
					
				}catch(java.lang.NullPointerException e) {
					livro.setISBN("");
					this.msg = this.msg + " campo ISBN não digitado";
				}
			 if(livro.getNumeroPaginas() == 0)
			 {
				 this.msg = this.msg + " campo Numero de páginas não digitado";
			 }
			 
			 if(livro.getEdicao() == 0)
			 {
				 this.msg = this.msg + " campo Edição não digitado";
			 }
			 
			 if(livro.getAno() == 0)
			 {
				 this.msg = this.msg + " campo Ano não digitado";
			 }
			 
			 try {
					if(livro.getPrecificacao() == null || livro.getPrecificacao().getTipo() == 0)
					{
						 this.msg = this.msg + " campo Precificação não digitado";
					}
					
				}catch(java.lang.NullPointerException e) {
					Precificacao precificacao = new Precificacao();
					precificacao.setTipo(1);
					livro.setPrecificacao(precificacao);
					 this.msg = this.msg + " campo Precificação não digitado";
				}
			 
			 if(livro.getIdcategoria() == null)
			 {
				 this.msg = this.msg + " campo Categoria  não digitado";
			 }
			if(this.msg == null || this.msg == "")
				return null;
			else return this.msg;
		}
	
		
		
		return null;
	}
}
