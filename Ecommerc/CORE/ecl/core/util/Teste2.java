package ecl.core.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ecl.core.impl.dao.VendasDAO;
import ecl.dominio.Categoria;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.ItensPedido;
import ecl.dominio.Livro;

public class Teste2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendasDAO vendas = new VendasDAO();
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		List<ItensPedido> itens = new ArrayList<ItensPedido>();
		ItensPedido entidade = new ItensPedido();
		Livro livro = new Livro();
		livro.setId(7);
		entidade.setLivro(livro);
		try {
			entidades = vendas.Detalhes(entidade);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(EntidadeDominio ent : entidades)
		{
			ItensPedido item = (ItensPedido)ent;
			itens.add(item);
		}
		for(ItensPedido ver : itens)
		{
			Double margem = (ver.getCusto()*(ver.getMargem()/100));
			Double Preco = ver.getCusto() + margem;
			System.out.println("Id do livro = " + ver.getLivro().getId());
			System.out.println("Titulo = " + ver.getLivro().getTitulo());
			System.out.println("Custo = " + ver.getCusto() );
			System.out.println("Margem = " + ver.getMargem() + "%");
			System.out.println("Id estoque = " + ver.getIdEstoque());
			System.out.println("Quantidade = " + ver.getQuantidade());
			System.out.println("Preço = " + Preco);
			for(Categoria cat : ver.getLivro().getIdcategoria())
			{
				System.out.println("Categoria Nome = " + cat.getNome());
			}
			System.out.println("//////////////////////////////////////////////////////////");

		}
		
	}

}
