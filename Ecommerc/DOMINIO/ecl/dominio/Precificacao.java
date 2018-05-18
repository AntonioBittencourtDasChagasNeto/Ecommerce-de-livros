package ecl.dominio;

public class Precificacao {
	   private String classificacao;
	   private int Tipo;
	   private double Ouro = 25;
	   private double Prata = 20;
	   private double Bronze = 15;
	public double getOuro() {
		return Ouro;
	}
	public void setOuro(double ouro) {
		Ouro = ouro;
	}
	public double getBronze() {
		return Bronze;
	}
	public void setBronze(double bronze) {
		Bronze = bronze;
	}
	public double getPrata() {
		return Prata;
	}
	public void setPrata(double prata) {
		Prata = prata;
	}
	    
	public String getTipo(int i) {
		switch(i){
		case 1:
			return "Ouro";
		case 2:
			return "Prata";
		case 3:
			return "Bronze";
		}
		return null;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public int getTipo() {
		return Tipo;
	}
	public void setTipo(int tipo) {
		Tipo = tipo;
	}

}
