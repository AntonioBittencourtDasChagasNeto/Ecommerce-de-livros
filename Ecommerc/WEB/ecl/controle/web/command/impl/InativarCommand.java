
package ecl.controle.web.command.impl;

import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;


public class InativarCommand extends AbstractCommand{

	
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.inativar(entidade);
	}

}
