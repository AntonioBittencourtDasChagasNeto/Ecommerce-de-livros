package ecl.dominio;

public class Igual {
	private Integer Id;
	private String nome;
	private boolean flgAtivado;
	public Integer getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean getFlgAtivado() {
		return flgAtivado;
	}
	public void setFlgAtivado(boolean flgAtivado) {
		this.flgAtivado = flgAtivado;
	}
}
