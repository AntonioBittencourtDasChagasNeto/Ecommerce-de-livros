
package ecl.controle.web.command.impl;

import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;


public class SalvarCommand extends AbstractCommand{

	@Override
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.salvar(entidade);
	}

}
