package ecl.core.impl.negocio;

import ecl.core.IStrategy;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Livro;

public class JustificativasParaAtivarInativar implements IStrategy{
	String msg;
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Livro){
			Livro livro = (Livro)entidade;
			msg = "";
			System.out.println("Eu cheguei na regra de negocio");
			System.out.println("EU JUSTIFIQUEI" + livro.getJustificativa());
			try {
				if(livro.getJustificativa().trim().equals("") || livro.getJustificativa() == null)
				{
					msg = "Não foi justificado";
				}
				
			}catch(java.lang.NullPointerException e) {
				livro.setJustificativa("");
				msg = "Não foi justificado";
			}
			if(livro.getIdCatJustificativa() == 0)
			{
				msg = "Categoria de justificativa não selecionada";
			}
			
			if(msg == null || msg == "")
				return null;
			else return msg;
		}
	
		
		
		return null;
	}
}
