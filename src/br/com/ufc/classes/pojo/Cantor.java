package br.com.ufc.classes.pojo;

public class Cantor {
	private int id;
	private String nomeGenero;
	private String Nome;
   public Cantor(int id, String nome,String nomeGenero) {
		super();
		this.id = id;
		this.Nome = nome;
		this.nomeGenero = nomeGenero;
		
	}

public Cantor(String nome,String nomeGenero) {
		super();
		this.Nome = nome;
		this.nomeGenero = nomeGenero;
	}





public String getNome() {
	return Nome;
}

public void setNome(String nome) {
	Nome = nome;
}

@Override
public String toString() {
	return "Cantor [Nome=" + Nome + "]";
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getGenero() {
	return nomeGenero;
}

public void setGenero(String estilo) {
	this.nomeGenero = estilo;
}
   
}
