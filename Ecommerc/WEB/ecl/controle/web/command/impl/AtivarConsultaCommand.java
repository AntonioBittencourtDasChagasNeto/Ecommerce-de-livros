package ecl.controle.web.command.impl;
import ecl.dominio.EntidadeDominio;
import ecl.core.aplicacao.Result;

public class AtivarConsultaCommand extends AbstractCommand{

	@Override
	public Result execute(EntidadeDominio entidade) {
		return fachada.inativarConsulta(entidade);
	}

}
