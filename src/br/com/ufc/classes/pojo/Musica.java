package br.com.ufc.classes.pojo;

public class Musica {
private String Nome;
private Integer mid;
private String Gdescricao;

public Musica(String nome) {
	super();
	Nome = nome;
}


public Musica(String nome, Integer mid, String gdescricao) {
	super();
	Nome = nome;
	this.mid = mid;
	Gdescricao = gdescricao;
}


public Musica(String nome, String gdescricao) {
	super();
	Nome = nome;
	Gdescricao = gdescricao;
}











@Override
public String toString() {
	return "Musica [Nome=" + Nome + ", mid=" + mid + ", Gdescricao=" + Gdescricao + "]";
}











public String getGdescricao() {
	return Gdescricao;
}











public Musica(String nome, Integer mid) {
	super();
	Nome = nome;
	this.mid = mid;
}


public void setGdescricao(String gdescricao) {
	Gdescricao = gdescricao;
}











public String getNome() {
	return Nome;
}

public void setNome(String nome) {
	Nome = nome;
}

public Integer getMid() {
	return mid;
}

public void setMid(Integer mid) {
	this.mid = mid;
}
}
