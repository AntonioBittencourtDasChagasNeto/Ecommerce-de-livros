package ecl.core;
import java.sql.SQLException;
import java.util.List;
import ecl.dominio.EntidadeDominio;

public interface IDAO {
	
	public void salvar(EntidadeDominio entidade) throws SQLException;
	public void alterar(EntidadeDominio entidade)throws SQLException;
	public void AlterarCartao(EntidadeDominio entidade)throws SQLException;
	public void AlterarEndereco(EntidadeDominio entidade)throws SQLException;
	public void AlterarSenha(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> Login(EntidadeDominio entidade)throws SQLException;
	public void excluir(EntidadeDominio entidade)throws SQLException;
	public void inativar(EntidadeDominio entidade)throws SQLException;
	public void ativar(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> Visualizar(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> VisualizarInativos(EntidadeDominio entidade)throws SQLException;
	void entradaestoque(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> VisualizarCliente(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> Detalhes(EntidadeDominio entidade) throws SQLException;
	public void AdiconarCarrinho(EntidadeDominio entidade) throws SQLException;
	void DiminuirCarrinho(EntidadeDominio entidade) throws SQLException;
	void RemoverCarrinho(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> VisualizarEndereco(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> Frete(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> FormasDePagamento(EntidadeDominio entidade) throws SQLException;
	public void RemoverCupons(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> PegarCartoesCliente(EntidadeDominio entidade) throws SQLException;

}
