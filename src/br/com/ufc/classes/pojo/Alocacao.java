package br.com.ufc.classes.pojo;

public class Alocacao {
	private Usuario usuario;
	private Cantor cantor;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Cantor getCantor() {
		return cantor;
	}
	public void setLivro(Cantor cantor) {
		this.cantor = cantor;
	}
	public Alocacao(Usuario usuario, Cantor cantor) {
		super();
		this.usuario = usuario;
		this.cantor = cantor;
	}
}
