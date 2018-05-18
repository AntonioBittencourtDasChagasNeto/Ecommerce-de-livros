package ecl.controle.web.command.impl;

import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;


public class FinalizarCompraCommand extends AbstractCommand{

	
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.FinalizarCompra(entidade);
	}

}
