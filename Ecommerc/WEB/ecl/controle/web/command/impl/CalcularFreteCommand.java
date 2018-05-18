package ecl.controle.web.command.impl;

import java.io.IOException;

import ecl.core.aplicacao.Result;
import ecl.dominio.EntidadeDominio;

public class CalcularFreteCommand extends AbstractCommand{

	@Override
	public Result execute(EntidadeDominio entidade) {

			return fachada.CalcularFrete(entidade);

	}

}
