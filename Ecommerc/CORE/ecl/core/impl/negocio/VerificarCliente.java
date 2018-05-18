package ecl.core.impl.negocio;
import ecl.core.*;
import ecl.dominio.Autor;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Cliente;
import ecl.dominio.Precificacao;

public class VerificarCliente implements IStrategy{

	private String msg;
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Cliente){
			Cliente cliente = (Cliente)entidade;
			this.msg = "";
			System.out.println("Eu entrei na regra de negocio");
			System.out.println("Nome" + cliente.getNome());
			System.out.println("Email" + cliente.getEmail());
			System.out.println("Telefone" + cliente.getTelefone());
			System.out.println("DDD" + cliente.getDDD());			
			try {
				if(cliente.getNome().trim().equals("") || cliente.getNome() == null)
				{
					this.msg = this.msg + "Campo Nome não digitado";
					cliente.setNome("");
				}
				
			}catch(java.lang.NullPointerException e) {
				cliente.setNome("");
				this.msg = this.msg + "Campo Nome não digitado";
			}
			try {
				if(cliente.getEmail().trim().equals("") || cliente.getEmail() == null)
				{
					this.msg =  this.msg + "Campo Email não digitado";
					cliente.setEmail("");
				}
				
			}catch(java.lang.NullPointerException e) {
				cliente.setEmail("");
				this.msg =  this.msg + "Campo Email não digitado";
			}
			try {
				if(cliente.getTelefone().trim().equals("") || cliente.getTelefone() == null)
				{
					cliente.setTelefone("");
					this.msg =  this.msg + "Campo Telefone não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				cliente.setTelefone("");
				this.msg =  this.msg + "Campo Telefone não digitado";
			}
			
			try {
				if(cliente.getDDD().trim().equals("") || cliente.getDDD() == null)
				{
					cliente.setDDD("");
					this.msg =  this.msg + "Campo DDD não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				cliente.setDDD("");
				this.msg =  this.msg + "Campo DDD não digitado";
			}

			
			if(this.msg == null || this.msg == "")
				return null;
			else return this.msg;
		}
	
		
		
		return null;
	}
}
