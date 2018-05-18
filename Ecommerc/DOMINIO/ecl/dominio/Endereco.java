package ecl.dominio;

public class Endereco extends EntidadeDominio{

	private String logradouro;
	private int numero;
	private double frete;
	private String cep;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String Pais;
	private String Entrega;
	private String Cobranca;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero2) {
		this.numero = numero2;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complento) {
		this.complemento = complento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return Pais;
	}
	public void setPais(String pais) {
		Pais = pais;
	}
	public String getEntrega() {
		return Entrega;
	}
	public void setEntrega(String entrega) {
		Entrega = entrega;
	}
	public String getCobranca() {
		return Cobranca;
	}
	public void setCobranca(String cobranca) {
		Cobranca = cobranca;
	}
	public double getFrete() {
		return frete;
	}
	public void setFrete(double frete) {
		this.frete = frete;
	}
	
	
	
}
