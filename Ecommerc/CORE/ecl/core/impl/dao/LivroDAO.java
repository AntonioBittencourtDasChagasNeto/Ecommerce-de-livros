package ecl.core.impl.dao;
import ecl.dominio.*;
import java.util.ArrayList;

import ecl.core.util.Construtor;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LivroDAO extends AbstractJdbcDAO{
	public LivroDAO() {
		super("tb_livro", "id_livro");		
	}
	int IdLivro;
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Livro livro = (Livro)entidade;
		
				
				try {
					openConnection();
					connection.setAutoCommit(false);			
					//FALTA COLOCAR EDITORA		
					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO livro(`livTitulo`, `livIdAutor`, `livIdEditora`, `livEdicao`, `livAno`, `livISBN`, `livNumeroPaginas`, `livSinopse`, `livAltura`, `livLargura`, `livPeso`, `livProfundidade`, `livIdPrecificacao`, `livCodigoDeBarras`, `livStatus`)");
					sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
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
					pst.setString(15,"ATIVADO");
					//pst.setTimestamp(3, time);
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
				//try {
				//	openConnection();
				//	connection.setAutoCommit(false);
				//	StringBuilder sql2 = new StringBuilder();
				//	 sql2.append("SELECT MAX(idlivro) FROM livro");
				//	 pst = null;
				//	 pst = connection.prepareStatement(sql2.toString());
				//	 System.out.println(sql2.toString());
				//	ResultSet rs2 = pst.executeQuery();
				//	System.out.println("Por favor" +  rs2.getInt("MAX(idlivro)"));
				//	if(rs2.next()) {
				//		livro.setId(rs2.getInt("MAX(idlivro)"));
				//		IdLivro = rs2.getInt("MAX(idlivro)");
				//		System.out.println("Por favor" +  rs2.getInt("MAX(idlivro)"));
				//	}
				//	connection.commit();
				//} catch (SQLException e) {	
				//	try {
				//		connection.rollback();
				//	} catch (SQLException e1) {
				//		e1.printStackTrace();
				//	}
				//	e.printStackTrace();			
				//}
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
		int Verificar = 0;
		int Verificar2 = 0;
		Livro liv = new Livro();
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
			sql.append("group by idlivro,categoria");
			//if(livro.getId() != null) {
			//	construtor.Construir("idlivro=?");
			//	System.out.println("Cheguei no id");
			//	Verificar = 1;
			//}
			if(livro.getAutor() != null) {
				if(livro.getAutor().getId() != null)
				{
				construtor.Construir("livIdAutor=?");
				System.out.println("Cheguei no autor");
				Verificar =1;
				}
			}
			if(!livro.getCodigoBarras().trim().equals("")) {
				construtor.Construir("livCodigoDeBarras=?");
				System.out.println("Cheguei no codigo de barras e o código é" + livro.getCodigoBarras());
				Verificar =1;
			}
			if(!livro.getTitulo().trim().equals("")) {
				construtor.Construir("livTitulo=?");
				System.out.println("Cheguei no titulo");
				Verificar =1;
			}
			if(livro.getEditora() != null) {
				if(livro.getEditora().getId() != null)
				{
				construtor.Construir("livIdEditora=?");
				System.out.println("Cheguei na editora");
				Verificar =1;
				}
				
				
			}

			if(livro.getIdcategoria() != null) 
			{
				for(Categoria cat : livro.getIdcategoria())
				{
						construtor.Construir("livcat.id_categoria=?");			
				}
			}
			sql.append(construtor.getSqlWhere());
		pst = connection.prepareStatement(sql.toString());
		//if(livro.getId() != null) {
		//	pst.setInt(contador, livro.getId());
		//	contador = contador + 1;
		//}
		if(livro.getAutor() != null) {
			pst.setInt(contador, livro.getAutor().getId());
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
			pst.setInt(contador, livro.getEditora().getId());
			contador = contador + 1;
		}
		if(livro.getIdcategoria() != null) 
		{
			for(Categoria cat : livro.getIdcategoria())
			{
				pst.setInt(contador, cat.getId());
				contador = contador + 1;
			}
			
		}
		System.out.println(sql.toString());
		ResultSet rs = pst.executeQuery();
		
		//SEGUNDA PARTE PUXAR CATEGORIAS
		while (rs.next()) {
			
			
		
		//SEXTA PARTE MONTANDO O RETORNO
			liv.setId(rs.getInt("idlivro"));
			liv.setTitulo(rs.getString("livTitulo"));
			liv.setSinopse(rs.getString("livSinopse"));
			liv.setCodigoBarras(rs.getString("livCodigoDeBarras"));
			Dimensoes dimensao = new Dimensoes();
			double Altura = rs.getDouble("livAltura");
			double Largura = rs.getDouble("livLargura");
			double Peso = rs.getDouble("livPeso");
			double profundidade = rs.getDouble("livProfundidade");
			dimensao.setAltura(Altura);
			dimensao.setLargura(Largura);
			dimensao.setPeso(Peso);
			dimensao.setProfundidade(profundidade);
			liv.setDimensoes(dimensao);
			liv.setISBN(rs.getString("livISBN"));
			liv.setNumeroPaginas(rs.getInt("livNumeroPaginas"));
			liv.setEdicao(rs.getInt("livEdicao"));
			liv.setStatus(rs.getString("livStatus"));
				livros.add(liv);
				livr.add(liv);
		}
		for(int i = 0; i < livr.size();i++)
		{
			for(int j = 0; j < livr.size();j++)
			{
				if(livr.get(j).getId() == livr.get(i).getId() && i!=j)
				{
					livr.get(j).getCategoria().add("");
				}
			}
		}
		return livros;
		
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
					pst.setString(1, "ATIVADO");
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
							sql.append("INSERT INTO justificativaativar (id_livro , justificativa, id_Ativar) VALUES (?,?,?)");		
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
	
	@Override
	public void entradaestoque(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement pst=null;
		Livro livro = (Livro)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO estoque (id_livro, quantidade,id_precificacao, custo) VALUES (?,?,?,?)");
					
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, livro.getId());
			pst.setInt(2, livro.getEstoque().getQuantidade());
			pst.setInt(3, livro.getPrecificacao().getTipo());
			pst.setDouble(4, livro.getEstoque().getCusto());
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
		return null;
		// TODO Auto-generated method stub
		
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
	public List<EntidadeDominio> VisualizarEndereco(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
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

