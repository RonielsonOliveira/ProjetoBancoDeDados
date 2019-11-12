package br.com.ufc.classes.pojo;

public class Genero {
   private int Gid;
   private String Gdescricao;
public int getGid() {
	return Gid;
}
public void setGid(int gid) {
	Gid = gid;
}
public String getGdescricao() {
	return Gdescricao;
}
public void setGdescricao(String gdescricao) {
	Gdescricao = gdescricao;
}
@Override
public String toString() {
	return "Genero [Gid=" + Gid + ", Gdescricao=" + Gdescricao + "]";
}
public Genero(String gdescricao) {
	super();
	Gdescricao = gdescricao;
}
public Genero(int gid, String gdescricao) {
	super();
	Gid = gid;
	Gdescricao = gdescricao;
}
   
}
