package ecl.core.impl.dao;
import ecl.dominio.*;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class ClienteDAO extends AbstractJdbcDAO{
	public ClienteDAO() {
		super("tb_cliente", "id_cliente");		
	}
	int IdLivro;
	public void salvar(EntidadeDominio entidade) {

	}
	/** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	public void alterar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Cliente cliente = (Cliente)entidade;
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cliente SET Nome=?, CPF=?, DDD=?, Telefone=?, email=? WHERE idcliente=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getDDD());
			pst.setString(4, cliente.getTelefone());
			pst.setString(5, cliente.getEmail());
			pst.setInt(6, cliente.getId());
			
			pst.executeUpdate();
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}

	public void AlterarCartao(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Cliente cliente = (Cliente)entidade;
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cartao SET numerocartao=?, nomecartao=?, idBandeira=?, codigoseguranca=?,preferencial=? WHERE idcartao=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getCartao().getNumero());
			pst.setString(2, cliente.getCartao().getNomeCartao());
			pst.setInt(3, cliente.getCartao().getBandeira().getId());
			pst.setInt(4, cliente.getCartao().getCodigoDeSeguranca());
			pst.setString(5, cliente.getCartao().getPreferencial());
			pst.setInt(6, cliente.getCartao().getId());
			pst.executeUpdate();
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void AlterarEndereco(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Cliente cliente = (Cliente)entidade;
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE endereco SET Logradouro=?, Numero=?, CEP=?, Bairro=?, Cidade=?, Estado=? WHERE idendereco=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getEndereco().getLogradouro());
			pst.setInt(2, cliente.getEndereco().getNumero());
			pst.setString(3, cliente.getEndereco().getCep());
			pst.setString(4, cliente.getEndereco().getBairro());
			pst.setString(5, cliente.getEndereco().getCidade());
			pst.setString(6, cliente.getEndereco().getEstado());
			pst.setInt(7, cliente.getEndereco().getId());
			pst.executeUpdate();
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}
	public void AlterarSenha(EntidadeDominio entidade) {

		PreparedStatement pst=null;
		Cliente cliente = (Cliente)entidade;
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cliente SET senha=? WHERE idcliente=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getSenha());
			pst.setInt(2, cliente.getId());
			pst.executeUpdate();
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}
	@Override
	public List<EntidadeDominio> VisualizarEndereco(EntidadeDominio entidade) {

		PreparedStatement pst=null;
		List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
		Cliente cliente = (Cliente)entidade;
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM endereco  WHERE IdCliente=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, cliente.getId());	
			ResultSet rs = pst.executeQuery();
			if(rs == null)
			{
				//DADOS INVALIDOS
			}
			else
			{
				while(rs.next())
				{
					Endereco endereco = new Endereco();
					endereco.setId(rs.getInt("idendereco"));
					endereco.setBairro(rs.getString("Bairro"));
					endereco.setCep(rs.getString("CEP"));
					endereco.setCidade(rs.getString("Cidade"));
					endereco.setLogradouro(rs.getString("Logradouro"));
					endereco.setEstado(rs.getString("Estado"));
					endereco.setNumero(rs.getInt("Numero"));
					endereco.setPais(rs.getString("Pais"));
					endereco.setEntrega(rs.getString("Entrega"));
					endereco.setCobranca(rs.getString("Cobranca"));

					enderecos.add(endereco);
				}
			}
			connection.commit();
			return enderecos;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return enderecos;	
		
	}
	public  List<EntidadeDominio> Login(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		Cliente cliente = (Cliente)entidade;
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM cliente WHERE email=? AND senha=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getEmail());
			pst.setString(2, cliente.getSenha());		
			ResultSet rs = pst.executeQuery();
			if(rs == null)
			{
				//DADOS INVALIDOS
			}
			else
			{
				while(rs.next())
				{
				cliente.setId(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("Nome"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setEmail(rs.getString("email"));
				cliente.setDDD(rs.getString("DDD"));
				clientes.add(cliente);
				}
			}
			connection.commit();
			return clientes;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;	
		
	}
	@Override
	public void inativar(EntidadeDominio entidade) {
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		
		return null;
	}
	
	@Override
	public List<EntidadeDominio> Visualizar(EntidadeDominio entidade) {
	return null;
	}
	
	@Override
	public List<EntidadeDominio> VisualizarInativos(EntidadeDominio entidade) {

	return null;
	}

	@Override
	public void ativar(EntidadeDominio entidade) throws SQLException {
	
	}
	
	@Override
	public void entradaestoque(EntidadeDominio entidade) throws SQLException {
	
	}
	@Override
	public List<EntidadeDominio> VisualizarCliente(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EntidadeDominio> Detalhes(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void AdiconarCarrinho(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	}
	@Override
	public void DiminuirCarrinho(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void RemoverCarrinho(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<EntidadeDominio> Frete(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EntidadeDominio> FormasDePagamento(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void RemoverCupons(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<EntidadeDominio> PegarCartoesCliente(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}

