package ecl.core.impl.controle;
import ecl.core.aplicacao.Result;
import ecl.core.*;
import ecl.dominio.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ecl.core.IDAO;
import ecl.core.impl.dao.LivroDAO;
import ecl.core.impl.dao.VendasDAO;
import ecl.core.impl.dao.ClienteDAO;
import ecl.core.impl.negocio.VerificarCamposObrigatorios;
import ecl.core.util.Frete;
import ecl.core.util.buscaCEP;
import ecl.core.impl.negocio.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Fachada implements IFachada {

	/** 
	 * Mapa de DAOS, será indexado pelo nome da entidade 
	 * O valor é uma instância do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade;
	 * O valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Result resultado;
	
	
	public Fachada(){
		/* Intânciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intânciando o Map de Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		/* Criando instâncias dos DAOs a serem utilizados*/
		// cliDAO = new ClienteDAO();
		LivroDAO livroDAO = new LivroDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		VendasDAO vendasDAO = new VendasDAO();
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */		
		daos.put(Livro.class.getName(),livroDAO);
		daos.put(Cliente.class.getName(),clienteDAO);
		daos.put(Pedidos.class.getName(),vendasDAO);
		
		
		/* Criando instâncias de regras de negócio a serem utilizados*/		
		
		VerificarCamposObrigatorios vrCamposObrigatorios = new VerificarCamposObrigatorios();
		VerificarConsulta vrConsulta = new VerificarConsulta();
		JustificativasParaAtivarInativar vrJustificativa = new JustificativasParaAtivarInativar();
		VerificarCliente vrCliente = new VerificarCliente();
		VerificarQualRetornoFinalizarCompra vrRetorno = new VerificarQualRetornoFinalizarCompra();
		//ValidadorDadosObrigatoriosFornecedor vrDadosObrigatoriosFornecedor = new ValidadorDadosObrigatoriosFornecedor();
		//ValidadorCnpj vCnpj = new ValidadorCnpj();
		//ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
		//ValidadorCpf vCpf = new ValidadorCpf();
		//ValidadorQtdProduto vQtd = new ValidadorQtdProduto();
		
		/* Criando uma lista para conter as regras de negócio de fornencedor
		 * quando a operação for salvar
		 */
		List<IStrategy> rnsSalvarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsInativarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsAtivarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsVerificarRetornoCompra = new ArrayList<IStrategy>();
		//rnsSalvarLivro.add(vrCamposObrigatorios);
		//List<IStrategy> rnsSalvarFornecedor = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor*/
		rnsSalvarLivro.add(vrCamposObrigatorios);
		rnsAlterarLivro.add(vrCamposObrigatorios);
		rnsInativarLivro.add(vrJustificativa);
		rnsConsultarLivro.add(vrConsulta);
		rnsAtivarLivro.add(vrJustificativa);
		rnsAlterarCliente.add(vrCliente);
		//rnsSalvarFornecedor.add(vrDadosObrigatoriosFornecedor);
		//rnsSalvarFornecedor.add(vCnpj);
		//rnsSalvarFornecedor.add(cDtCadastro);
		rnsVerificarRetornoCompra.add(vrRetorno);
		/* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
		 * por operação  do fornecedor
		 */
		Map<String, List<IStrategy>> rnsLivro = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsVenda = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista criada na linha 70)
		 */
		rnsLivro.put("salvar", rnsSalvarLivro);
		rnsLivro.put("alterar", rnsAlterarLivro);
		rnsLivro.put("consultar", rnsConsultarLivro);
		rnsLivro.put("inativar", rnsInativarLivro);
		rnsLivro.put("ativar", rnsAtivarLivro);
		rnsCliente.put("alterar", rnsAlterarCliente);
		//rnsFornecedor.put("SALVAR", rnsSalvarFornecedor);	
		rnsVenda.put("FinalizarCompra", rnsVerificarRetornoCompra);
		/* Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações no mapa geral indexado 
		 * pelo nome da entidade
		 */
		rns.put(Livro.class.getName(), rnsLivro);
		rns.put(Cliente.class.getName(), rnsCliente);
		//rns.put(Fornecedor.class.getName(), rnsFornecedor);
		
		/* Criando uma lista para conter as regras de negócio de cliente
		 * quando a operação for salvar
		 */
		//List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do cliente */
		//rnsSalvarCliente.add(cDtCadastro);		
		//rnsSalvarCliente.add(vCpf);
		
		/* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
		 * por operação do cliente
		 */
		//Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do cliente (lista criada na linha 93)
		 */
		//rnsCliente.put("SALVAR", rnsSalvarCliente);		
		/* Adiciona o mapa(criado na linha 101) com as regras indexadas pelas operações no mapa geral indexado 
		 * pelo nome da entidade. Observe que este mapa (rns) é o mesmo utilizado na linha 88.
		 */
		//rns.put(Cliente.class.getName(), rnsCliente);
		
		/* Criando uma lista para conter as regras de negócio de produto
		 * quando a operação for salvar
		 */
		//List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do produto */
		//rnsSalvarProduto.add(cDtCadastro);		
		//rnsSalvarProduto.add(vQtd);
		
		/* Criando uma lista para conter as regras de negócio de produto
		 * quando a operação for alterar
		 */
		//List<IStrategy> rnsAlterarProduto = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação alterar do produto */
		//rnsAlterarProduto.add(vQtd);
		
		/* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
		 * por operação do produto
		 */
		//Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do produto (lista criada na linha 114)
		 */
		//rnsProduto.put("SALVAR", rnsSalvarProduto);
		/*
		 * Adiciona a listra de regras na operação alterar no mapa do produto (lista criada na linha 122)
		 */
		//rnsProduto.put("ALTERAR", rnsAlterarProduto);
		
		/* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
		 * pelo nome da entidade. Observe que este mapa (rns) é o mesmo utilizado na linha 88.
		 */
		//rns.put(Produto.class.getName(), rnsProduto);
		
	}
	
	
	@Override
	public Result salvar(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "salvar");
		
		System.out.println("Parei nas regras de negocio?;" + msg);
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
				System.out.println("Problema no sql?" + msg);
			}
		}else{
			resultado.setMsg(msg);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
					
			
		}
		
		return resultado;
	}

	@Override
	public Result alterar(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "alterar");
		System.out.println("Sou a mensagem e minha mensagem é:" + msg);
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}
	public Result inativar(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "inativar");
		System.out.println("Eu estou vazio?: " + msg);
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.inativar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setMsg(null);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível inativar!");
				
			}
		}else{
			resultado.setMsg(msg);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);		
			
		}
		
		return resultado;

	}

	public Result excluir(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}

	public Result consultar(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "consultar");
		System.out.println("Mensagem em consultar igual a:" + msg);
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");
				
			}
		}else{
			resultado.setMsg(msg);
			
		}
		
		return resultado;

	}
	
	public Result visualizar(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "VISUALIZAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.Visualizar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");
				
			}
		}else{
			resultado.setMsg(msg);
			
		}
		
		return resultado;

	}
	public Result visualizarInativados(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "VISUALIZAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				
				resultado.setEntidades(dao.VisualizarInativos(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível visualizar!");
				
			}
		}else{
			resultado.setMsg(msg);
			
		}
		
		return resultado;
	}

	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}			
			
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}


	public Result ativar(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ativar");
		System.out.println("Eu msg estou vazio?: " + msg);
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.ativar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setMsg(null);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível ativar!");
				
			}
		}else{
			resultado.setMsg(msg);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);		
			
		}
		
		return resultado;
	}


	@Override
	public Result inativarConsulta(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		entidades.add(entidade);
		resultado.setEntidades(entidades);
		return resultado;
	}
	
	@Override
	public Result ativarConsulta(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
		entidades.add(entidade);
		resultado.setEntidades(entidades);
		return resultado;
	}

	@Override
	public Result entradaestoque(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "INATIVAR");
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.entradaestoque(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível inativar!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}
	@Override
	public Result AlterarCartao(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.AlterarCartao(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível inativar!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}
	@Override
	public Result AlterarEndereco(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.AlterarEndereco(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível alterar Endereco!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}
	@Override
	public Result AlterarSenha(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.AlterarSenha(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível alterar senha!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}
	@Override
	public Result Login(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.Login(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Logar!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}


	@Override
	public Result BuscarCEP(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
				resultado = new Result();
				Cliente cliente = (Cliente)entidade;
				Endereco endereco = new Endereco();
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				//ENDEREÇO
				try{

			        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+ cliente.getEndereco().getCep())
			                  .timeout(120000)
			                  .get();
			        Elements urlPesquisa = doc.select("span[itemprop=streetAddress]");
			        for (Element urlEndereco : urlPesquisa) {
			                 endereco.setLogradouro(urlEndereco.text());
			        }

			        } catch (SocketTimeoutException e) {

			        } catch (HttpStatusException w) {

			        } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 	
				 	//Bairro
			        try{

			        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+cliente.getEndereco().getCep())
			                  .timeout(120000)
			                  .get();
			        Elements urlPesquisa = doc.select("td:gt(1)");
			        for (Element urlBairro : urlPesquisa) {
			        	 endereco.setBairro(urlBairro.text());
			        	 break;
			        }

			        } catch (SocketTimeoutException e) {

			        } catch (HttpStatusException w) {

			        } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
			        
			        //Cidade
			        try{

			        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+cliente.getEndereco().getCep())
			                  .timeout(120000)
			                  .get();
			        Elements urlPesquisa = doc.select("span[itemprop=addressLocality]");
			        for (Element urlCidade : urlPesquisa) {
			        	 endereco.setCidade(urlCidade.text());
			        }

			        } catch (SocketTimeoutException e) {

			        } catch (HttpStatusException w) {

			        } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
			        
			        //***************************************************
			        try{

			        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+cliente.getEndereco().getCep())
			                  .timeout(120000)
			                  .get();
			        Elements urlPesquisa = doc.select("span[itemprop=addressRegion]");
			        for (Element urlUF : urlPesquisa) {
			        	endereco.setEstado(urlUF.text());
			        }

			        } catch (SocketTimeoutException e) {

			        } catch (HttpStatusException w) {

			        } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        endereco.setId(cliente.getEndereco().getId());
			        endereco.setCep(cliente.getEndereco().getCep());
					cliente.setEndereco(endereco);
					entidades.add(cliente);
					resultado.setEntidades(entidades);
				return resultado;
				
	}


	@Override
	public Result VisualizarClientes(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.VisualizarCliente(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Ver Livros!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}


	@Override
	public Result Detalhes(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.Detalhes(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Ver Livros!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	@Override
	public Result FormasDePagamento(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		System.out.println("nmClasse escolhida igual a = " + nmClasse);
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.FormasDePagamento(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Ver Livros!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	@Override
	public Result FinalizarCompra(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		System.out.println("nmClasse escolhida igual a = " + nmClasse);
		String msg = executarRegras(entidade, "FinalizarCompra");
		if(msg == null || msg.trim().equals("")){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.RemoverCarrinho(entidade);
				resultado.setEntidades(dao.VisualizarCliente(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Ver Livros!");
				
			}
		}else if(msg.trim().equals("1"))
		{
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.FormasDePagamento(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Ver Livros!");
				
			}
					
			
		}else if(msg.trim().equals("2"))
		{
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.PegarCartoesCliente(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Ver Livros!");
				
			}
			resultado.setMsg(msg);
		}
		
		return resultado;
	}
	
	@Override
	public Result AdicionarCarrinho(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.AdiconarCarrinho(entidade);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Adicionar ao Carrinho!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	@Override
	public Result AumentarCarrinho(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.AdiconarCarrinho(entidade);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Adicionar ao Carrinho!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	@Override
	public Result DiminuirCarrinho(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.DiminuirCarrinho(entidade);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Diminuir Carrinho!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	
	@Override
	public Result RemoverCarrinho(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.RemoverCarrinho(entidade);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Diminuir Carrinho!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	@Override
	public Result VisualizarEndereco(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.VisualizarEndereco(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível Diminuir Carrinho!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}
	
	@Override
	public Result CalcularFrete(EntidadeDominio entidade) {
		resultado = new Result();
		String nmClasse = entidade.getClass().getName();	
		System.out.println("Estou dentro de calcular frete e minha entidade é = " + nmClasse);
		String msg = null;
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				
				resultado.setEntidades(dao.Frete(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não calcular o frete!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}

}
