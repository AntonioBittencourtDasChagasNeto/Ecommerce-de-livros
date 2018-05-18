
package ecl.controle.web.command;

import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;


public interface ICommand {

	public Result execute(EntidadeDominio entidade);
	
}
