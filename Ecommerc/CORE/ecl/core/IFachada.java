package ecl.core;

import java.io.IOException;

import ecl.core.aplicacao.Result;
import ecl.dominio.EntidadeDominio;

public interface IFachada {
	public Result salvar(EntidadeDominio entidade);
	public Result alterar(EntidadeDominio entidade);
	public Result excluir(EntidadeDominio entidade);
	public Result consultar(EntidadeDominio entidade);
	public Result visualizar(EntidadeDominio entidade);
	public Result visualizarInativados(EntidadeDominio entidade);
	public Result inativar(EntidadeDominio entidade);
	public Result ativar(EntidadeDominio entidade);
	public Result inativarConsulta(EntidadeDominio entidade);
	public Result ativarConsulta(EntidadeDominio entidade);
	public Result entradaestoque(EntidadeDominio entidade);
	public Result AlterarCartao(EntidadeDominio entidade);
	public Result AlterarEndereco(EntidadeDominio entidade);
	public Result AlterarSenha(EntidadeDominio entidade);
	public Result Login(EntidadeDominio entidade);
	public Result BuscarCEP(EntidadeDominio entidade);
	public Result VisualizarClientes(EntidadeDominio entidade);
	public Result Detalhes(EntidadeDominio entidade);
	public Result AdicionarCarrinho(EntidadeDominio entidade);
	public Result AumentarCarrinho(EntidadeDominio entidade);
	public Result DiminuirCarrinho(EntidadeDominio entidade);
	public Result RemoverCarrinho(EntidadeDominio entidade);
	public Result VisualizarEndereco(EntidadeDominio entidade);
	public Result CalcularFrete(EntidadeDominio entidade);
	public Result FormasDePagamento(EntidadeDominio entidade);
	public Result FinalizarCompra(EntidadeDominio entidade);

}
