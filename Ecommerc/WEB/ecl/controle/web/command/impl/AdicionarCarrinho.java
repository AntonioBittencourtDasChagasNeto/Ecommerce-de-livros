package ecl.controle.web.command.impl;

import ecl.core.aplicacao.Result;
import ecl.dominio.EntidadeDominio;

public class AdicionarCarrinho extends AbstractCommand{

	
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.AdicionarCarrinho(entidade);
	}

}