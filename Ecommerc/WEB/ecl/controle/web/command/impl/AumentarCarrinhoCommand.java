package ecl.controle.web.command.impl;

import ecl.core.aplicacao.Result;
import ecl.dominio.EntidadeDominio;

public class AumentarCarrinhoCommand extends AbstractCommand{

	
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.AumentarCarrinho(entidade);
	}

}