
package ecl.controle.web.command.impl;

import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;


public class VisualizarCommand extends AbstractCommand{

	
	public Result execute(EntidadeDominio entidade) {
		
		return fachada.visualizar(entidade);
	}

}
