package ecl.dominio;

public class Cartao extends EntidadeDominio {
	private String Numero;
	private String NomeCartao;
	private Bandeira bandeira;
	private int CodigoDeSeguranca;
	private String Preferencial;
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	public int getCodigoDeSeguranca() {
		return CodigoDeSeguranca;
	}
	public void setCodigoDeSeguranca(int codigoDeSeguranca) {
		CodigoDeSeguranca = codigoDeSeguranca;
	}
	public String getNomeCartao() {
		return NomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		NomeCartao = nomeCartao;
	}
	public String getPreferencial() {
		return Preferencial;
	}
	public void setPreferencial(String preferencial) {
		Preferencial = preferencial;
	}
	
}
