package ecl.core.impl.negocio;
import ecl.core.*;
import ecl.dominio.EntidadeDominio;
import ecl.dominio.Cliente;

public class VerificarEndereco implements IStrategy{

	private String msg;
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Cliente){
			Cliente cliente = (Cliente)entidade;
			System.out.println("Eu entrei na regra de negocio");
			System.out.println("Cep" + cliente.getEndereco().getCep());
			System.out.println("Logradouro" + cliente.getEndereco().getLogradouro());
			System.out.println("estado" + cliente.getEndereco().getEstado());
			System.out.println("Bairro" + cliente.getEndereco().getBairro());
			System.out.println("Cidade" + cliente.getEndereco().getCidade());
			System.out.println("Entrega" + cliente.getEndereco().getEntrega());
			System.out.println("Cobranca" + cliente.getEndereco().getCobranca());
			System.out.println("Numero" + cliente.getEndereco().getNumero());
			
			try {
				if(cliente.getEndereco().getCep().trim().equals("") || cliente.getEndereco().getCep() == null)
				{
					this.msg = this.msg + "Campo Endereco não digitado";
					
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg = this.msg + "Campo Nome não digitado";
			}
			try {
				if(cliente.getEndereco().getEstado().trim().equals("") || cliente.getEndereco().getEstado() == null)
				{
					this.msg =  this.msg + "Campo Estado não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg =  this.msg + "Campo Estado não digitado";
			}
			try {
				if(cliente.getEndereco().getBairro().trim().equals("") || cliente.getEndereco().getBairro() == null)
				{
					this.msg =  this.msg + "Campo Bairro não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg =  this.msg + "Campo Bairro não digitado";
			}
			
			try {
				if(cliente.getEndereco().getCidade().trim().equals("") || cliente.getEndereco().getCidade() == null)
				{
					this.msg =  this.msg + "Campo Cidade não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg =  this.msg + "Campo Cidade não digitado";
			}
			try {
				if(cliente.getEndereco().getEntrega().trim().equals("") || cliente.getEndereco().getEntrega() == null)
				{
					this.msg =  this.msg + "Campo Entrega não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg =  this.msg + "Campo Entrega não digitado";
			}
			try {
				if(cliente.getEndereco().getCobranca().trim().equals("") || cliente.getEndereco().getCobranca() == null)
				{
					this.msg =  this.msg + "Campo Cobranca não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg =  this.msg + "Campo Cobranca não digitado";
			}
			try {
				if(cliente.getEndereco().getNumero() != 0)
				{
					this.msg =  this.msg + "Campo Numero não digitado";
				}
				
			}catch(java.lang.NullPointerException e) {
				this.msg =  this.msg + "Campo Numero não digitado";
			}

			
			if(this.msg == null || this.msg == "")
				return null;
			else return this.msg;
		}
	
		
		
		return null;
	}
}
