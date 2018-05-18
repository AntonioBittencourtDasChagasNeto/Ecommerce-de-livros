package ecl.core.impl.negocio;
import ecl.core.*;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Livro;

public class VerificarConsulta implements IStrategy{

	String msg;
	@Override
	public String processar(EntidadeDominio entidade) {
		System.out.println("Eu cheguei na regra de negocio");
		msg = "";
		if(entidade instanceof Livro){
			Livro livro = (Livro)entidade;
			int Verificar = 0;
			if(livro.getTitulo().trim().equals("") || livro.getTitulo() == null)
			{
				 System.out.println("O titulo � nulo");
			}else {Verificar = 1;}
			 if(livro.getAutor() == null){
				 System.out.println("O livro � nulo");
			}else 
			{
				Verificar = 1;
			}
			 if(livro.getCodigoBarras().trim().equals("") || livro.getCodigoBarras() == null){
				 System.out.println("O codigo de barras � nulo");
			}else 
			{
				Verificar = 1;
				System.out.println("Eu digitei o c�digo de barras");
			}
			 if(livro.getEditora() == null){
				 System.out.println("Editora � nula");
			}else {Verificar = 1;}	 
			 if(livro.getIdcategoria().isEmpty())
			 {
				 System.out.println("Categoria � nula");
			 }else 
			 {
				 Verificar = 1;
			 }
			 if(Verificar == 0)
			 {
				 System.out.println("O valor de verificar �: " + Verificar);
				 msg = "Nenhum campo foi selecionado";
			 }
			if(msg == null || msg == "")
				return null;
			else return msg;
		}
	
		
		
		return null;
	}
}
