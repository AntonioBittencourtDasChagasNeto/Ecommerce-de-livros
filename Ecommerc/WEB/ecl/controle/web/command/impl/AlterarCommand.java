
package ecl.controle.web.command.impl;

import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;


public class AlterarCommand extends AbstractCommand{

	
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.alterar(entidade);
	}

}
