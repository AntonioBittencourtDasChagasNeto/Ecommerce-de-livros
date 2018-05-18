package ecl.controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecl.controle.web.command.ICommand;
import ecl.controle.web.command.impl.AdicionarCarrinho;
import ecl.controle.web.command.impl.AlterarCartaoCommand;
import ecl.controle.web.command.impl.AlterarCommand;
import ecl.controle.web.command.impl.AlterarEnderecoCommand;
import ecl.controle.web.command.impl.AlterarSenhaCommand;
import ecl.controle.web.command.impl.AtivarCommand;
import ecl.controle.web.command.impl.AtivarConsultaCommand;
import ecl.controle.web.command.impl.AumentarCarrinhoCommand;
import ecl.controle.web.command.impl.BuscarCepCommand;
import ecl.controle.web.command.impl.CalcularFreteCommand;
import ecl.controle.web.command.impl.ConsultarCommand;
import ecl.controle.web.command.impl.DetalhesCommand;
import ecl.controle.web.command.impl.DiminuirCarrinhoCommand;
import ecl.controle.web.command.impl.EntradaEstoqueCommand;
import ecl.controle.web.command.impl.FormasDePagamentoCommand;
import ecl.controle.web.command.impl.InativarCommand;
import ecl.controle.web.command.impl.InativarConsultarCommand;
import ecl.controle.web.command.impl.LoginCommand;
import ecl.controle.web.command.impl.RemoverCarrinhoCommand;
import ecl.controle.web.command.impl.SalvarCommand;
import ecl.controle.web.command.impl.VisualizarClienteCommand;
import ecl.controle.web.command.impl.VisualizarCommand;
import ecl.controle.web.command.impl.VisualizarEnderecoCommand;
import ecl.controle.web.vh.IViewHelper;
import ecl.controle.web.vh.impl.LivroViewHelper;
import ecl.controle.web.vh.impl.VendasViewHelper;
import ecl.controle.web.vh.impl.ClienteViewHelper;
import ecl.core.aplicacao.Result;
import ecl.dominio.EntidadeDominio;

/**
 * Servlet implementation class Ecommerce
 */
@WebServlet("/")
public class Ecommerce extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> viewHelpers;
	
	public Ecommerce() {
		String basePath = "/Ecommerc";
		
		/*
		 * Endereço das URL's para a realização das ações no sistema (salve, delete, update) 
		 */
		viewHelpers = new HashMap<>();
		viewHelpers.put(basePath.concat("/book/save"), new LivroViewHelper());
		viewHelpers.put(basePath.concat("/book/inat"), new LivroViewHelper());
		viewHelpers.put("/Ecommerc/", new LivroViewHelper());
		viewHelpers.put("/Ecommerc", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/AlterarEnderecoResposta", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/AlterarCartaoResposta", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/AlterarClienteResposta", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/AlterarSenhaResposta", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/MenuCliente", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/ConsultarLivroCliente", new VendasViewHelper());
		viewHelpers.put("/Ecommerc/FichaTecnica", new VendasViewHelper());		
		viewHelpers.put("/Ecommerc/Carrinho", new VendasViewHelper());	
		viewHelpers.put("/Ecommerc/FreteEndereco", new ClienteViewHelper());
		viewHelpers.put("/Ecommerc/FormaDePagamento", new VendasViewHelper());
		viewHelpers.put("/Ecommerc/FinalizarCompra", new VendasViewHelper());
		/*
		 * Command para executar o método do objeto encapsulado no pacote com.fatec.ecommerce.command, responsável por
		 * consultar(listar) e salvar, através da utilização de um HashMap(tipo de vetor)
		 */
		commands = new HashMap<>();
		commands.put("salvar", new SalvarCommand());
		commands.put("Inativar", new InativarConsultarCommand());
		commands.put("inativar", new InativarCommand());
		commands.put("visualizar", new VisualizarCommand());
		commands.put("alterar" , new AlterarCommand());
		commands.put("consultar", new ConsultarCommand());
		commands.put("Ativar",new AtivarConsultaCommand());
		commands.put("ativar", new AtivarCommand());
		commands.put("EntradaEstoque", new EntradaEstoqueCommand());
		commands.put("Login", new LoginCommand());
		commands.put("AlterarCartao", new AlterarCartaoCommand());
		commands.put("AlterarEndereco", new AlterarEnderecoCommand());
		commands.put("AlterarSenha", new AlterarSenhaCommand());
		commands.put("buscarCEP", new BuscarCepCommand());
		commands.put("buscarCEP2", new BuscarCepCommand());
		commands.put("buscarCEP3", new BuscarCepCommand());
		commands.put("VisualizarCliente", new VisualizarClienteCommand());
		commands.put("Detalhes", new DetalhesCommand());
		commands.put("AdicionarCarrinho", new AdicionarCarrinho());
		commands.put("AumentarCarrinho", new AumentarCarrinhoCommand());
		commands.put("DiminuirCarrinho", new DiminuirCarrinhoCommand());
		commands.put("Remover", new RemoverCarrinhoCommand());
		commands.put("VisualizarEndereco", new VisualizarEnderecoCommand());
		commands.put("CalcularFrete", new CalcularFreteCommand());
		commands.put("FormaDePagamento", new FormasDePagamentoCommand());
   }

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * URL requisitada do usuario
		 */
        String uriRequested = request.getRequestURI();
        System.out.println("Eu cheguei até o service");
        System.out.println(uriRequested);
        /*
		 * Procurando a URL nos viewHelper's
		 */
        String op = request.getParameter("operacao");
        System.out.println("A operacao selecionada é igual a = " + op);
        IViewHelper viewHelperFound  = viewHelpers.get(uriRequested);
        System.out.println("Eu passei pelo view Helper");
        if (viewHelperFound != null) {
        	
        	final String operacao;
        	
        	if (request.getParameter("operacao") != null && !request.getParameter("operacao").isEmpty()) {
        		operacao = request.getParameter("operacao");
        		System.out.println("EU NÃO SEI SE SOU ESSE CARA" + operacao);
        		System.out.println("Eu entrei no if");
        	} else {
        		operacao = "salvar";
        	}
        	System.out.println("Eu passei pelo if");
        	EntidadeDominio entidade = viewHelperFound.getEntidade(request);
        	System.out.println("Eu virei um livro");
    		ICommand command = commands.get(operacao);
    		System.out.println("Eu posso ser salvo");
    		Result resultado = command.execute(entidade);
    		System.out.println("Estou tentando salvar");
        	/*
    		 * Executando o view entrado
    		 */
        	viewHelperFound.setView(resultado, request, response);
        	System.out.println("Eu fui até o fim");
        }
    }
	

}