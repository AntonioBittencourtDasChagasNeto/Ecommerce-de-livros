package ecl.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;

import ecl.core.IStrategy;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Livro;

public class VerificarCategorias implements IStrategy {
	
	String msg;
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Livro){
			Livro livro = (Livro)entidade;
		String textojunto = livro.getCategoriaJunta(); 
		String[] textoSeparado = textojunto.split(";");
		List<String> categorias = new ArrayList<String>();
		for (int i = 0; i < textoSeparado.length; i++){
		categorias.add(textoSeparado[i]);
		}
		livro.setCategoria(categorias);
		return msg;
	}
		return null;
	
}
}
