package ecl.core.impl.dao;
import ecl.dominio.*;
import java.util.ArrayList;

import ecl.core.util.Construtor;
import ecl.core.util.Frete;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VendasDAO extends AbstractJdbcDAO{
	public VendasDAO() {
		super("tb_livro", "id_livro");		
	}
	int IdLivro;
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Pedidos pedido = (Pedidos)entidade;
		
				
				try {
					openConnection();
					connection.setAutoCommit(false);			
					//FALTA COLOCAR EDITORA		
					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO pedido( `Data`, `Total`, `Status`, `idcliente`)");
					sql.append("VALUES (?, ?, ?, ?)");	
					pst = connection.prepareStatement(sql.toString());
					pst.setString(1, "Hoje");
					pst.setDouble(2, pedido.getTotal());
					pst.setInt(3, 1);
					pst.setInt(4, pedido.getCliente().getId());
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
				//SEGUNDA PARTE
				try {
					openConnection();
					connection.setAutoCommit(false);
					StringBuilder sql2 = new StringBuilder();
					 sql2.append("SELECT MAX(idpedido) FROM pedido");
					 pst = null;
					 pst = connection.prepareStatement(sql2.toString());
					 System.out.println(sql2.toString());
					ResultSet rs2 = pst.executeQuery();
					System.out.println("Por favor" +  rs2.getInt("MAX(idlivro)"));
					if(rs2.next()) {
						pedido.setId(rs2.getInt("MAX(idpedido)"));
						System.out.println("Por favor" +  rs2.getInt("MAX(idlivro)"));
					}
					connection.commit();
				} catch (SQLException e) {	
					try {
						connection.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();			
				}
				for(ItensPedido itens : pedido.getItens())	//Arrumar essa parte
				{
					try {
						openConnection();
						connection.setAutoCommit(false);			
						//FALTA COLOCAR EDITORA		
						StringBuilder sql = new StringBuilder();
						sql.append("INSERT INTO itensPedido( `idPedido`, `SubTotal`, `Status`, `idcliente`)");
						sql.append("VALUES (?, ?, ?, ?)");	
						pst = connection.prepareStatement(sql.toString());
						pst.setInt(1, pedido.getId());
						
						double Total = 0.0;
						double margem = (itens.getCusto()*(itens.getMargem()/100));
						double Preco = itens.getCusto() + margem;
						Total = ((Preco)*itens.getQuantidade());
						
						pst.setDouble(2, Total);
						pst.setInt(3, 1);
						pst.setInt(4, pedido.getCliente().getId());
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
				} // For para inserir os itens do pedido
				//TERCEIRA PARTE
				// try {
				//		openConnection();
				//		connection.setAutoCommit(false);			
						//FALTA COLOCAR EDITORA		
				//		for(Categoria categoria : livro.getIdcategoria()) {
				//		pst=null;
				//		StringBuilder sql2 = new StringBuilder();
				//		sql2.append("INSERT INTO livCat(id_Livro, id_categoria)");	
				//		sql2.append("VALUES (?, ?)");	
				//		pst = connection.prepareStatement(sql2.toString());
				//		pst.setInt(1,IdLivro);
				//		pst.setInt(2, categoria.getId());
				//		pst.executeUpdate();
				//		connection.commit();
				//		}
				//	} catch (SQLException e) {
				//		try {
				//			connection.rollback();
				//		} catch (SQLException e1) {
				//			e1.printStackTrace();
				//		}
				//		e.printStackTrace();			
				//	}finally{
				//		try {
				//			connection.close();
				//		} catch (SQLException e) {
				//			e.printStackTrace();
				//		}
				//	}	

	}
	/** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		PreparedStatement pst=null;
		Livro livro = (Livro)entidade;		
		
		try {
			openConnection();
			connection.setAutoCommit(false);				
			StringBuilder sql = new StringBuilder();	
			sql.append("UPDATE livro SET livTitulo=?, livIdAutor=?, livIdEditora=?, livEdicao=?, livAno=?, livISBN=?, livNumeroPaginas=?, livSinopse=?, livAltura=?, livLargura=?, livPeso=?, livProfundidade=?, livIdPrecificacao=?, livCodigoDeBarras=? WHERE idlivro=?");	
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, livro.getTitulo());
			pst.setInt(2, livro.getAutor().getId());
			pst.setInt(3, livro.getEditora().getId());
			pst.setInt(4, livro.getEdicao());
			pst.setInt(5, livro.getAno());
			pst.setString(6, livro.getISBN());
			pst.setInt(7, livro.getNumeroPaginas());
			pst.setString(8, livro.getSinopse());
			pst.setDouble(9, livro.getDimensoes().getAltura());
			pst.setDouble(10, livro.getDimensoes().getLargura());
			pst.setDouble(11, livro.getDimensoes().getPeso());
			pst.setDouble(12, livro.getDimensoes().getProfundidade());
			pst.setInt(13, livro.getPrecificacao().getTipo());
			pst.setString(14, livro.getCodigoBarras());
			pst.setInt(15, livro.getId());
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
		//SEGUNDA PARTE
		try {
			openConnection();
			connection.setAutoCommit(false);			
			//FALTA COLOCAR EDITORA		
			pst=null;
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM livCat WHERE id_livro=?");
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, livro.getId());
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
		//TERCEIRA PARTE
		try {
			openConnection();
			connection.setAutoCommit(false);			
			//FALTA COLOCAR EDITORA		
			for(Categoria categoria : livro.getIdcategoria()) {
			pst=null;
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO livCat(id_Livro, id_categoria) VALUES (?, ?)");		
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1,livro.getId());
			pst.setInt(2, categoria.getId());
			pst.executeUpdate();
			connection.commit();
			}
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
	public void inativar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		PreparedStatement pst=null;
		Livro livro = (Livro)entidade;		
		try {
			openConnection();
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();			
			sql.append("UPDATE livro SET livStatus=?");
			sql.append("WHERE idlivro=?");		
			
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, "INATIVADO");
			pst.setInt(2, livro.getId());
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
		
		//SEGUNDA PARTE
				try {
					openConnection();
					connection.setAutoCommit(false);			
					//FALTA COLOCAR EDITORA		
					StringBuilder sql = new StringBuilder();
					System.out.println(livro.getId());
					System.out.println(livro.getJustificativa());
					System.out.println(livro.getIdCatJustificativa());
					sql.append("INSERT INTO justificativainativar (id_livro , justificativa, id_Inativar) VALUES (?,?,?)");		
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, livro.getId());
					pst.setString(2,livro.getJustificativa());
					pst.setInt(3,livro.getIdCatJustificativa());
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
	/** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		int contador = 1;
		PreparedStatement pst = null;
		Construtor construtor = new Construtor();
		Livro livro = (Livro)entidade;
		List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
		List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
		List<Livro> livr = new ArrayList<Livro>();
		StringBuilder sql = new StringBuilder();
		try {
			openConnection();	
			sql.append("SELECT idlivro,livTitulo,livAno,livEdicao,livISBN,livNumeroPaginas,livSinopse,livAltura,livPeso,livLargura,livProfundidade,livCodigoDeBarras,autor,editora,id_estoque,quantidade,MAX(custo),categoria,margem ");
			sql.append("from livro ");
			sql.append("JOIN autor ON livro.livIdAutor = autor.id_Autor ");
			sql.append("JOIN editora ON livro.livIdEditora = id_editora ");
			sql.append("JOIN livcat ON livro.idlivro = livcat.id_livro  ");
			sql.append("JOIN categoria ON livcat.id_categoria = categoria.id_Categoria ");
			sql.append("JOIN estoque ON livro.idlivro = estoque.id_livro ");
			sql.append("JOIN precificacao ON precificacao.id_precificacao = estoque.id_precificacao ");
			sql.append("WHERE  ");
			//if(livro.getId() != null) {
			//	construtor.Construir("idlivro=?");
			//	System.out.println("Cheguei no id");
			//	Verificar = 1;
			//}
			if(livro.getAutor() != null) {
				if(livro.getAutor().getId() != null)
				{
				construtor.Construir("autor=?");
				System.out.println("Cheguei no autor");
				}
			}
			if(!livro.getCodigoBarras().trim().equals("")) {
				construtor.Construir("livCodigoDeBarras=?");
				System.out.println("Cheguei no codigo de barras e o código é" + livro.getCodigoBarras());
			}
			if(!livro.getTitulo().trim().equals("")) {
				construtor.Construir("livTitulo=?");
				System.out.println("Cheguei no titulo");
			}
			if(livro.getEditora() != null) {
				if(livro.getEditora().getId() != null)
				{
				construtor.Construir("editora=?");
				System.out.println("Cheguei na editora");
				}
				
				
			}

			if(livro.getIdcategoria() != null) 
			{
				for(Categoria cat : livro.getIdcategoria())
				{
						construtor.Construir("categoria=?");			
				}
			}
			sql.append(construtor.getSqlWhere());
			sql.append("group by idlivro,categoria");
		pst = connection.prepareStatement(sql.toString());
		//if(livro.getId() != null) {
		//	pst.setInt(contador, livro.getId());
		//	contador = contador + 1;
		//}
		if(livro.getAutor() != null) {
			pst.setString(contador, livro.getAutor().getNome());
			contador = contador + 1;
		}
		if(livro.getCodigoBarras() != null) {
		if(!livro.getCodigoBarras().trim().equals("")) {
			pst.setString(contador, livro.getCodigoBarras());
			System.out.println("Cheguei no codigo de barras NO PST");
			contador = contador + 1;
		}
		}
		if(livro.getTitulo() != null) {
		if(!livro.getTitulo().trim().equals("")) {
			pst.setString(contador, livro.getTitulo());
			contador = contador + 1;
		}
		}
		if(livro.getEditora() != null) {
			pst.setString(contador, livro.getEditora().getNome());
			contador = contador + 1;
		}
		if(livro.getIdcategoria() != null) 
		{
			for(Categoria cat : livro.getIdcategoria())
			{
				pst.setString(contador, cat.getNome());
				contador = contador + 1;
			}
			
		}
		System.out.println(sql.toString());
		ResultSet rs = pst.executeQuery();
		
		//SEGUNDA PARTE PUXAR CATEGORIAS
		while (rs.next()) {
			
			
		
		//SEXTA PARTE MONTANDO O RETORNO
			ItensPedido item = new ItensPedido();
			Livro liv = new Livro();
			liv.setId(rs.getInt("idlivro"));
			liv.setTitulo(rs.getString("livTitulo"));
			item.setCusto(rs.getDouble("MAX(custo)"));
			item.setMargem(rs.getDouble("margem"));
			item.setQuantidade(rs.getInt("quantidade"));
			item.setIdEstoque(rs.getInt("id_estoque"));
			item.setLivro(liv);
			itens.add(item);
		}

		return itens;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	@Override
	public List<EntidadeDominio> Visualizar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		Livro livro = (Livro)entidade;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		PreparedStatement pst4 = null;
		PreparedStatement pst5 = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM livro WHERE idlivro=?");
		try {
			openConnection();	
	
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, livro.getId());
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
		if(rs.next()) {
			livro.setTitulo(rs.getString("livTitulo"));
			livro.setSinopse(rs.getString("livSinopse"));
			livro.setCodigoBarras(rs.getString("livCodigoDeBarras"));
			Dimensoes dimensao = new Dimensoes();
			double Altura = rs.getDouble("livAltura");
			double Largura = rs.getDouble("livLargura");
			double Peso = rs.getDouble("livPeso");
			double profundidade = rs.getDouble("livProfundidade");
			dimensao.setAltura(Altura);
			dimensao.setLargura(Largura);
			dimensao.setPeso(Peso);
			dimensao.setProfundidade(profundidade);
			livro.setDimensoes(dimensao);
			livro.setISBN(rs.getString("livISBN"));
			livro.setNumeroPaginas(rs.getInt("livNumeroPaginas"));
			livro.setEdicao(rs.getInt("livEdicao"));
			livro.setStatus(rs.getString("livStatus"));
			livro.setAno(rs.getInt("livAno"));
		}
			StringBuilder sql2 = new StringBuilder();
			 sql2.append("SELECT * FROM livcat JOIN categoria ON livcat.id_categoria= categoria.id_Categoria WHERE livcat.id_livro=?");
			 pst2 = connection.prepareStatement(sql2.toString());
			 pst2.setInt(1, livro.getId());
			ResultSet rs2 = pst2.executeQuery();
			List<Categoria> cat = new ArrayList<Categoria>();
			Categoria c = new Categoria();
			if(rs2.next()) {
				c.setNome(rs2.getString("categoria"));
				c.setId(rs2.getInt("id_Categoria"));
				cat.add(c);
			}
			
		//TERCEIRA PARTE PUXAR AUTOR
			StringBuilder sql3 = new StringBuilder();
			 sql3.append("SELECT * FROM livro JOIN autor ON  livro.livIdAutor = autor.id_Autor WHERE livro.idlivro=?");
			 pst3 = connection.prepareStatement(sql3.toString());
			 pst3.setInt(1, rs.getInt("idlivro"));
			ResultSet rs3 = pst3.executeQuery();
			while(rs3.next())
			{
				Autor autor = new Autor();
				autor.setId(rs3.getInt("id_Autor"));
				autor.setNome(rs3.getString("autor"));
				livro.setAutor(autor);
				
			}
		//QUARTA PARTE PUXANDO A EDITORA
			StringBuilder sql4 = new StringBuilder();
			 sql4.append("SELECT * FROM livro JOIN editora ON  livro.livIdEditora = editora.id_editora WHERE livro.idlivro=?");
			 pst4 = connection.prepareStatement(sql4.toString());
			 pst4.setInt(1, rs.getInt("idlivro"));
			 ResultSet rs4 = pst4.executeQuery();
			 if(rs4.next())
			 {
				 Editora edit = new Editora();
				 edit.setId(rs4.getInt("id_editora"));
				 edit.setNome(rs4.getString("editora"));
				 livro.setEditora(edit);
			 }
		//QUINTA PARTE PUXANDO A PRECIFICACAO
			 StringBuilder sql5 = new StringBuilder();
			 sql5.append("SELECT * FROM livro JOIN precificacao ON  livro.livIdPrecificacao = precificacao.id_precificacao WHERE idlivro=?");
			 pst5 = null;
			 pst5 = connection.prepareStatement(sql5.toString());
			 pst5.setInt(1, rs.getInt("idlivro"));
			 ResultSet rs5 = pst5.executeQuery();
			 if(rs5.next())
			 {
				 Precificacao preci = new Precificacao();
				 preci.setClassificacao(rs5.getString("precificacao"));
				 preci.setTipo(rs5.getInt("id_precificacao"));
				 livro.setPrecificacao(preci);
			 }
		//SEXTA PARTE MONTANDO O RETORNO			
				livro.setIdcategoria(cat);
				
				
				
				
				livros.add(livro);
				return livros;
	//java.sql.Date dtCadastroEmLong = rs.getDate("dt_cadastro");
	//Date dtCadastro = new Date(dtCadastroEmLong.getTime());				
	//p.setDtCadastro(dtCadastro);
	//produtos.add(p);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return null;
	}
	@Override
	public List<EntidadeDominio> VisualizarInativos(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM livros WHERE Status=?");
		try {
			openConnection();	
	
		pst = connection.prepareStatement(sql.toString());
		pst.setString(1, "INATIVADO");
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
		//SEGUNDA PARTE PUXAR CATEGORIAS
				while (rs.next()) {
					StringBuilder sql2 = new StringBuilder();
					 sql2.append("SELECT * FROM livCat JOIN Categoria ON  livCat.id_categoria = Categoria.id_categoria WHERE Idlivro=?");
					 pst = null;
					 pst = connection.prepareStatement(sql2.toString());
					 pst.setInt(1, rs.getInt("id_livro"));
					ResultSet rs2 = pst.executeQuery();
					List<Categoria> cat = new ArrayList<Categoria>();
					Categoria c = new Categoria();
					while(rs2.next()) {
						c.setNome(rs2.getString("CategoriaNome"));
						c.setId(rs2.getInt("idCategoria"));
						cat.add(c);
					}
					
				//TERCEIRA PARTE PUXAR AUTOR
					StringBuilder sql3 = new StringBuilder();
					 sql3.append("SELECT * FROM livro JOIN Autor ON  livro.IdAutor = Autor.Id WHERE Idlivro=?");
					 pst = null;
					 pst = connection.prepareStatement(sql3.toString());
					 pst.setInt(1, rs.getInt("id_livro"));
					ResultSet rs3 = pst.executeQuery();
					
				//QUARTA PARTE PUXANDO A EDITORA
					StringBuilder sql4 = new StringBuilder();
					 sql4.append("SELECT * FROM livro JOIN Editora ON  livro.IdEditora = Editora.Id WHERE Idlivro=?");
					 pst = null;
					 pst = connection.prepareStatement(sql4.toString());
					 pst.setInt(1, rs.getInt("id_livro"));
					 ResultSet rs4 = pst.executeQuery();
				//QUINTA PARTE PUXANDO A PRECIFICACAO
					 StringBuilder sql5 = new StringBuilder();
					 sql5.append("SELECT * FROM livro JOIN Precificacao ON  livro.IdPrecificacao = Precificacao.Id WHERE Idlivro=?");
					 pst = null;
					 pst = connection.prepareStatement(sql5.toString());
					 pst.setInt(1, rs.getInt("id_livro"));
					 ResultSet rs5 = pst.executeQuery();	
				//SEXTA PARTE MONTANDO O RETORNO
						Livro liv = new Livro();
						Editora edit = new Editora();
						Autor autor = new Autor();
						Precificacao preci = new Precificacao();
						edit.setId(rs4.getInt("IdEditora"));
						edit.setNome(rs3.getString("AutorNome"));
						liv.setId(rs3.getInt("IdAutor"));
						liv.setEditora(edit);
						liv.setAutor(autor);
						liv.setTitulo(rs.getString("Titulo"));
						liv.setSinopse(rs.getString("Sinopse"));
						liv.dimensoes.setAltura(rs.getDouble("Altura"));
						liv.dimensoes.setLargura(rs.getDouble("Largura"));
						liv.dimensoes.setPeso(rs.getDouble("Peso"));
						liv.dimensoes.setProfundidade(rs.getDouble("Profundidade"));
						liv.setISBN(rs.getString("ISBN"));
						liv.setIdcategoria(cat);
						liv.setNumeroPaginas(rs.getInt("NumeroDePaginas"));
						preci.setClassificacao(rs5.getString("PrecificacaoNome"));
						preci.setTipo(rs5.getInt("IdPrecificacao"));
						liv.setPrecificacao(preci);
						liv.setEdicao(rs.getInt("Edicao"));
						liv.setStatus(rs.getString("Status"));
						int VerificarDuplicatas = 0;
						for(EntidadeDominio teste : livros) // Verificar se o livro que será armazenado no resultado ja está na array
						{
							if(teste.getId() == liv.getId())
							{
								VerificarDuplicatas = 1;
							}
						}
						if(VerificarDuplicatas == 0)
						{
						livros.add(liv);
						}
						
			//java.sql.Date dtCadastroEmLong = rs.getDate("dt_cadastro");
			//Date dtCadastro = new Date(dtCadastroEmLong.getTime());				
			//p.setDtCadastro(dtCadastro);
			//produtos.add(p);
		}
		return livros;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public void ativar(EntidadeDominio entidade) throws SQLException {

	}
	
	@Override
	public void entradaestoque(EntidadeDominio entidade) throws SQLException {
		
	}
	@Override
	public void AlterarCartao(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void AlterarEndereco(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void AlterarSenha(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<EntidadeDominio> Login(EntidadeDominio entidade) throws SQLException {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<EntidadeDominio> VisualizarCliente(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				
				List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
				StringBuilder sql = new StringBuilder();
				try {
					openConnection();	
					sql.append("SELECT idlivro,livTitulo,livAltura,livPeso,livLargura,livProfundidade, MAX(custo),margem,quantidade,id_estoque FROM livro JOIN estoque ");
					sql.append("ON livro.idlivro = estoque.id_livro JOIN precificacao ");
					sql.append("ON livro.livIdPrecificacao = precificacao.id_precificacao ");
					sql.append("WHERE estoque.quantidade > 0 ");
					sql.append("group by idlivro");
				pst = connection.prepareStatement(sql.toString());
				
				ResultSet rs = pst.executeQuery();
				
				//SEGUNDA PARTE PUXAR CATEGORIAS
				while (rs.next()) {
				//SEXTA PARTE MONTANDO O RETORNO
					ItensPedido item = new ItensPedido();
					Livro liv = new Livro();
					liv.setId(rs.getInt("idlivro"));
					liv.setTitulo(rs.getString("livTitulo"));
					item.setCusto(rs.getDouble("MAX(custo)"));
					item.setMargem(rs.getDouble("margem"));
					item.setQuantidade(rs.getInt("quantidade"));
					item.setIdEstoque(rs.getInt("id_estoque"));
					Dimensoes dimensao = new Dimensoes();
					double Altura = rs.getDouble("livAltura");
					double Largura = rs.getDouble("livLargura");
					double Peso = rs.getDouble("livPeso");
					double profundidade = rs.getDouble("livProfundidade");
					double Volume = Altura * Largura * profundidade;
					dimensao.setVolume(Volume);
					dimensao.setAltura(Altura);
					dimensao.setLargura(Largura);
					dimensao.setPeso(Peso);
					dimensao.setProfundidade(profundidade);
					liv.setDimensoes(dimensao);
					item.setLivro(liv);
					itens.add(item);
				}

				return itens;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		
	}
	@Override
	public List<EntidadeDominio> Detalhes(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				Pedidos ite = (Pedidos)entidade;
				List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
				List<Categoria> categorias = new ArrayList<Categoria>();
				
				StringBuilder sql = new StringBuilder();
				try {
					openConnection();	
					sql.append("SELECT idlivro,livTitulo,livAno,livEdicao,livISBN,livNumeroPaginas,livSinopse,livAltura,livPeso,livLargura,livProfundidade,livCodigoDeBarras,autor,editora,id_estoque,quantidade,MAX(custo),categoria,margem ");
					sql.append("from livro ");
					sql.append("JOIN autor ON livro.livIdAutor = autor.id_Autor ");
					sql.append("JOIN editora ON livro.livIdEditora = id_editora ");
					sql.append("JOIN livcat ON livro.idlivro = livcat.id_livro  ");
					sql.append("JOIN categoria ON livcat.id_categoria = categoria.id_Categoria ");
					sql.append("JOIN estoque ON livro.idlivro = estoque.id_livro ");
					sql.append("JOIN precificacao ON precificacao.id_precificacao = estoque.id_precificacao ");
					sql.append("WHERE idlivro=? ");
					sql.append("group by idlivro,categoria");
				pst = connection.prepareStatement(sql.toString());	
				pst.setInt(1, ite.getItens().get(0).getLivro().getId());
				ResultSet rs = pst.executeQuery();
				
				//SEGUNDA PARTE PUXAR CATEGORIAS
				while (rs.next()) {
				//SEXTA PARTE MONTANDO O RETORNO
					ItensPedido item = new ItensPedido();
					Livro liv = new Livro();
					Autor autor = new Autor();
					Editora editora = new Editora();
					Categoria cat = new Categoria();
					liv.setId(rs.getInt("idlivro"));
					liv.setTitulo(rs.getString("livTitulo"));
					autor.setNome(rs.getString("autor"));
					editora.setNome(rs.getString("editora"));
					liv.setAutor(autor);
					liv.setEditora(editora);
					liv.setAno(rs.getInt("livAno"));
					liv.setISBN(rs.getString("livISBN"));
					liv.setSinopse(rs.getString("livSinopse"));
					liv.setCodigoBarras(rs.getString("livCodigoDeBarras"));
					liv.setNumeroPaginas(rs.getInt("livNumeroPaginas"));
					liv.setEdicao(rs.getInt("livEdicao"));
					cat.setNome(rs.getString("categoria"));
					categorias.add(cat);
					liv.setIdcategoria(categorias);
					Dimensoes dimensao = new Dimensoes();
					double Altura = rs.getDouble("livAltura");
					double Largura = rs.getDouble("livLargura");
					double Peso = rs.getDouble("livPeso");
					double profundidade = rs.getDouble("livProfundidade");
					double Volume = Altura * Largura * profundidade;
					dimensao.setVolume(Volume);
					dimensao.setAltura(Altura);
					dimensao.setLargura(Largura);
					dimensao.setPeso(Peso);
					dimensao.setProfundidade(profundidade);
					liv.setDimensoes(dimensao);
					item.setCusto(rs.getDouble("MAX(custo)"));
					item.setMargem(rs.getDouble("margem"));
					item.setQuantidade(rs.getInt("quantidade"));
					item.setIdEstoque(rs.getInt("id_estoque"));
					item.setLivro(liv);
					itens.add(item);
				}
				return itens;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		
	}
	@Override
	public List<EntidadeDominio> FormasDePagamento(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				Pedidos ite = (Pedidos)entidade;
				List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
				List<Cartao> cartoes = new ArrayList<Cartao>();
				List<Cupom> cupons = new ArrayList<Cupom>();
				
				StringBuilder sql = new StringBuilder();
				try {
					openConnection();	
					sql.append("SELECT * FROM ecl_livros.cartao join bandeira ON cartao.idcartao = bandeira.idbandeira where idCliente = ?");
				pst = connection.prepareStatement(sql.toString());	
				pst.setInt(1, ite.getCliente().getId());
				ResultSet rs = pst.executeQuery();
				
				//SEGUNDA PARTE PUXAR CATEGORIAS
				while (rs.next()) {
				//SEXTA PARTE MONTANDO O RETORNO
					Cartao car = new Cartao();
					Bandeira bandeira = new Bandeira();
					car.setId(rs.getInt("idcartao"));
					car.setNomeCartao(rs.getString("nomecartao"));
					car.setNumero(rs.getString("numerocartao"));
					bandeira.setId(rs.getInt("idbandeira"));
					bandeira.setNome(rs.getString("bandeiraNome"));
					car.setCodigoDeSeguranca(rs.getInt("codigoseguranca"));
					car.setBandeira(bandeira);
					

					cartoes.add(car);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
				pst = null;
				sql = new StringBuilder();
				try {
					openConnection();	
					sql.append("SELECT * FROM cupom where idCliente = ?");
				pst = connection.prepareStatement(sql.toString());	
				pst.setInt(1, ite.getCliente().getId());
				ResultSet rs = pst.executeQuery();
				
				//SEGUNDA PARTE PUXAR CATEGORIAS
				while (rs.next()) {
				//SEXTA PARTE MONTANDO O RETORNO
					Cupom cupom = new Cupom();
					Bandeira bandeira = new Bandeira();
					cupom.setId(rs.getInt("idcupom"));
					cupom.setValor(rs.getDouble("valor"));				
					cupom.setTipo("tipo");
					cupons.add(cupom);
				}
				ite.setCartoes(cartoes);
				ite.setCupons(cupons);
				itens.add(ite);
				return itens;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		
	}
	@Override
	public List<EntidadeDominio> PegarCartoesCliente(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				Pedidos ite = (Pedidos)entidade;
				List<EntidadeDominio> itens = new ArrayList<EntidadeDominio>();
				List<Cartao> cartoes = new ArrayList<Cartao>();
				List<Cupom> cupons = new ArrayList<Cupom>();
				
				StringBuilder sql = new StringBuilder();
				try {
					openConnection();	
					sql.append("SELECT * FROM ecl_livros.cartao join bandeira ON cartao.idcartao = bandeira.idbandeira where idCliente = ?");
				pst = connection.prepareStatement(sql.toString());	
				pst.setInt(1, ite.getCliente().getId());
				ResultSet rs = pst.executeQuery();
				
				//SEGUNDA PARTE PUXAR CATEGORIAS
				while (rs.next()) {
				//SEXTA PARTE MONTANDO O RETORNO
					Cartao car = new Cartao();
					Bandeira bandeira = new Bandeira();
					car.setId(rs.getInt("idcartao"));
					car.setNomeCartao(rs.getString("nomecartao"));
					car.setNumero(rs.getString("numerocartao"));
					bandeira.setId(rs.getInt("idbandeira"));
					bandeira.setNome(rs.getString("bandeiraNome"));
					car.setCodigoDeSeguranca(rs.getInt("codigoseguranca"));
					car.setBandeira(bandeira);
					

					cartoes.add(car);
				}
				ite.setCartoes(cartoes);
				itens.add(ite);
				return itens;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
				
			return null;
		
	}
	@Override
	public void AdiconarCarrinho(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				Pedidos ite = (Pedidos)entidade;
				try {
					openConnection();
					connection.setAutoCommit(false);				
					StringBuilder sql = new StringBuilder();	
					sql.append("UPDATE estoque SET quantidade = quantidade - 1  WHERE id_estoque=?");	
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, (ite.getItens().get(ite.getIndex()).getIdEstoque()));
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
	public void RemoverCupons(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				
				Pedidos ite = (Pedidos)entidade;
				for(Cupom c : ite.getCupons())
				{
					PreparedStatement pst = null;
				try {
					openConnection();
					connection.setAutoCommit(false);				
					StringBuilder sql = new StringBuilder();	
					sql.append("DELETE FROM cupom WHERE idcupom=?");	
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, (c.getId()));
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
				}//for
				if(ite.getTroco() != 0)
				{
					PreparedStatement pst = null;
					try {
						openConnection();
						connection.setAutoCommit(false);				
						StringBuilder sql = new StringBuilder();	
						sql.append("INSERT INTO cupom (valor, idCliente, tipo)");	
						sql.append("VALUES (?, ?, ?)");
						pst = connection.prepareStatement(sql.toString());
						pst.setDouble(1, (ite.getTroco()));
						pst.setInt(2, (ite.getCliente().getId()));
						pst.setString(3, "Troca");
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
		
	}
	@Override
	public void DiminuirCarrinho(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				Pedidos ite = (Pedidos)entidade;
				try {
					openConnection();
					connection.setAutoCommit(false);				
					StringBuilder sql = new StringBuilder();	
					sql.append("UPDATE estoque SET quantidade = quantidade + 1  WHERE id_estoque=?");	
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, (ite.getItens().get(ite.getIndex()).getIdEstoque()));
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
	public void RemoverCarrinho(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				PreparedStatement pst = null;
				Pedidos ite = (Pedidos)entidade;
				try {
					openConnection();
					connection.setAutoCommit(false);				
					StringBuilder sql = new StringBuilder();	
					sql.append("UPDATE estoque SET quantidade = quantidade + ?  WHERE id_estoque=?");	
					pst = connection.prepareStatement(sql.toString());
					pst.setInt(1, (ite.getQtde()));
					pst.setInt(2, (ite.getIndex()));
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<EntidadeDominio>Frete(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		System.out.println("Estou dentro da DAO de frete no inicio de tudo");
		PreparedStatement pst=null;
		List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
		Pedidos pedido = (Pedidos)entidade;
		double volumeTotal = 0;
		double Peso = 0;
		double Altura = 0;
		double Comprimento = 16;
		double largura = 16;
		double fret = 0;
		for(ItensPedido i : pedido.getItens())
		{
			
			volumeTotal = volumeTotal + i.getLivro().getDimensoes().getVolume();
			Peso = Peso + i.getLivro().getDimensoes().getPeso();
		}
		double caixa = Math.cbrt(volumeTotal);
		Frete frete = new Frete();
		System.out.println("Volume total = " + volumeTotal);
		System.out.println("Peso = " + Peso);
		System.out.println("Caixa = " + caixa);
		System.out.println("CEP = " + pedido.getEndereco().getCep());
		if (caixa >= 16)
		{
		 fret = frete.CalcularFrete(pedido.getTotal(), Peso, caixa, pedido.getEndereco().getCep());
		}
		else
		{
			if(caixa < 2)
			{
				Altura = 3;
			}else
			{
				Altura = caixa;
			}
			fret = frete.CalcularFrete2(Altura,Peso,Comprimento,largura,pedido.getEndereco().getCep());
		}
		try {
			openConnection();
			connection.setAutoCommit(false);			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM endereco  WHERE IdCliente=?");
			pst = connection.prepareStatement(sql.toString());
			System.out.println("Valor do id = " + pedido.getCliente().getId());
			pst.setInt(1, pedido.getCliente().getId());	
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
					endereco.setFrete(fret);
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
	
	
}

