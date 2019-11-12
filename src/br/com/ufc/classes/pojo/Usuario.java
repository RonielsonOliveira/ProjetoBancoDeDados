package br.com.ufc.classes.pojo;

public class Usuario {
private int id;
private String Nome;
private String senha;



public Usuario(int id, String nome, String senha) {
	super();
	this.id = id;
	Nome = nome;
	this.senha = senha;
}
public Usuario(String nome, String senha) {
	super();
	Nome = nome;
	this.senha = senha;
}
public Usuario(String nome) {
	super();
	Nome = nome;
}
public Usuario(int id, String nome) {
	super();
	this.id = id;
	Nome = nome;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
@Override
public String toString() {
	return "Usuario [id=" + id + ", Nome=" + Nome + ", senha=" + senha + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return Nome;
}
public void setNome(String nome) {
	Nome = nome;
}



}
