package ecl.dominio;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Livro extends EntidadeDominio{
	private String Titulo;
	private Autor Autor;
	private String Sinopse;
	private String ISBN;
	private String CodigoBarras;
	private String CategoriaJunta;
	private String Status;
	private String Justificativa;
	private Estoque estoque;
	private Editora Editora;
	private int Ano;
	private int IdEditora;
	private int IdCategoria;
	private int IdAutor;
	private int IdCatJustificativa;
	private Date DataDeModificacao;
	private int Edicao;
	private int NumeroPaginas;
	public Dimensoes dimensoes;
	public Precificacao precificacao;
    private List<String> categoria = new ArrayList<String>();
    private List<Categoria> Idcategoria = new ArrayList<Categoria>();
    private boolean flagAtivado;
	
	public String getTitulo() {
		return Titulo;
	}
	
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	
	
	public String getSinopse() {
		return Sinopse;
	}
	
	public void setSinopse(String sinopse) {
		Sinopse = sinopse;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public String getCodigoBarras() {
		return CodigoBarras;
	}
	
	public void setCodigoBarras(String codigoBarras) {
		CodigoBarras = codigoBarras;
	}
	
	public int getEdicao() {
		return Edicao;
	}
	
	public void setEdicao(int edicao) {
		Edicao = edicao;
	}
	
	public int getNumeroPaginas() {
		return NumeroPaginas;
	}
	
	public void setNumeroPaginas(int numeroPaginas) {
		NumeroPaginas = numeroPaginas;
	}
	
	public Dimensoes getDimensoes() {
		return dimensoes;
	}
	
	public void setDimensoes(Dimensoes dimensoes) {
		this.dimensoes = dimensoes;
	}

	public Precificacao getPrecificacao() {
		return precificacao;
	}

	public void setPrecificacao(Precificacao precificacao) {
		this.precificacao = precificacao;
	}
	public List<String> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<String> categoria) {
		this.categoria = categoria;
	}

	public String getCategoriaJunta() {
		return CategoriaJunta;
	}

	public void setCategoriaJunta(String categoriaJunta) {
		CategoriaJunta = categoriaJunta;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getJustificativa() {
		return Justificativa;
	}

	public void setJustificativa(String justificativa) {
		Justificativa = justificativa;
	}

	public int getIdCatJustificativa() {
		return IdCatJustificativa;
	}

	public void setIdCatJustificativa(int idCatJustificativa) {
		IdCatJustificativa = idCatJustificativa;
	}

	public Date getDataDeModificacao() {
		return DataDeModificacao;
	}

	public void setDataDeModificacao(Date dataDeModificacao) {
		DataDeModificacao = dataDeModificacao;
	}


	public int getIdCategoria() {
		return IdCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		IdCategoria = idCategoria;
	}

	public int getIdAutor() {
		return IdAutor;
	}

	public void setIdAutor(int idAutor) {
		IdAutor = idAutor;
	}

	public int getIdEditora() {
		return IdEditora;
	}

	public void setIdEditora(int idEditora) {
		IdEditora = idEditora;
	}

	public int getAno() {
		return Ano;
	}

	public void setAno(int ano) {
		Ano = ano;
	}

	public List<Categoria> getIdcategoria() {
		return Idcategoria;
	}

	public void setIdcategoria(List<Categoria> idcategoria) {
		Idcategoria = idcategoria;
	}

	public Autor getAutor() {
		return Autor;
	}

	public void setAutor(Autor autor) {
		Autor = autor;
	}

	public Editora getEditora() {
		return Editora;
	}

	public void setEditora(Editora editora) {
		Editora = editora;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public boolean getFlagAtivado() {
		return flagAtivado;
	}

	public void setFlagAtivado(boolean flagAtivado) {
		this.flagAtivado = flagAtivado;
	}
	


}
