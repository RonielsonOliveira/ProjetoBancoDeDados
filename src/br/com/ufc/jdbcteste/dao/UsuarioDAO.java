package br.com.ufc.jdbcteste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.classes.pojo.Genero;
import br.com.ufc.classes.pojo.Musica;
import br.com.ufc.classes.pojo.Usuario;
import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;


public class UsuarioDAO {
	private Connection connection;

	public UsuarioDAO() { }
	
	public boolean addUser(Usuario usuario) {
		String sql = "INSERT INTO usuario(nomeusuario,senha) VALUES (?,?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean addGenero(Usuario usuario) {
		String sql = "INSERT INTO usuario(nomeusuario) VALUES (?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean addMusica(Integer usuario_id,Integer musica_id) {
		String sql = "INSERT INTO Usuario_Escuta_Musica(usuario_id,musica_id) VALUES(?,?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,usuario_id);
			stmt.setInt(2,musica_id);
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
	public boolean addGostoMusica(Integer id_Usuario,Integer id_genero) {
		String sql ="INSERT INTO Usuario_Gosta_Genero(id_Usuario,Id_genero) VALUES(?,?) ";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id_Usuario);
			stmt.setInt(2, id_genero);
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
	
	public ArrayList<Usuario> getListUser() {
		String sql = "SELECT * FROM usuario;";
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idusuario");
				String nome = rs.getString("nomeusuario");
				
				Usuario user = new Usuario(id, nome);
				
				listaUsuarios.add(user);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaUsuarios;
	}
	public ArrayList<Genero> getListGenero() {
		String sql = "Select g.gid,g.gdescricao From genero g order by gid;";
		ArrayList<Genero> listagenero = new ArrayList<Genero>();
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("gid");
				String descricao = rs.getString("gdescricao");
				Genero genero = new Genero(id, descricao);
				
				listagenero.add(genero);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listagenero;
	}
	public ArrayList<Genero> getListGeneroNaoGosta(String nomeUsuario,String senha ) {
		String sql = "Select g.gid,g.gdescricao From genero g except (Select g.gid,g.gdescricao from Usuario_Gosta_Genero ugg, Genero g ,Usuario u WHERE ugg.id_usuario = u.idusuario and ugg.id_genero = g.gid and u.nomeusuario = ? and u.senha = ?)order by gid;";
		ArrayList<Genero> listageneroNaoGosta = new ArrayList<Genero>();
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeUsuario);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("gid");
				String descricao = rs.getString("gdescricao");
				Genero genero = new Genero(id, descricao);
				
				listageneroNaoGosta.add(genero);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listageneroNaoGosta;
	}
	public ArrayList<Genero> getListGeneroGosta(String nomeUsuario,String senha ) {
		String sql =  "Select g.gid ,g.gdescricao from genero g ,usuario_gosta_genero ugg ,usuario u where ugg.id_usuario = u.idusuario and ugg.id_genero = g.gid and u.nomeusuario = ? and u.senha = ? order by gid;";
		ArrayList<Genero> listageneroGosta = new ArrayList<Genero>();
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeUsuario);
			stmt.setString(2,senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("gid");
				String descricao = rs.getString("gdescricao");
				Genero genero = new Genero(id, descricao);
				
				listageneroGosta.add(genero);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listageneroGosta;
	}
	public ArrayList<Musica> getListMusic(){
		String sql = "SELECT * FROM Musica;";
		ArrayList<Musica> listaMusicas = new ArrayList<Musica>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("mid");
				String nome = rs.getString("nome");
			
				Musica musica = new Musica(nome,id);
				
				listaMusicas.add(musica);
			}
			stmt.close();
	}catch (SQLException e) {
		System.err.println(e.getMessage());
	} finally {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return listaMusicas;
}
	public ArrayList<Musica> SugerirMusica(String nomeusuario,String senha){
	String sql = "((Select Distinct M.Mid,M.nome,G.gdescricao from Musica M,Usuario_gosta_genero Ugg,Genero g, Usuario U where M.gid = G.gid and M.gid = Ugg.id_genero and Ugg.id_usuario = U.idusuario  and U.nomeusuario = ? and U.senha = ?) except (Select Distinct M.mid,M.nome,G.gdescricao from Musica M,genero g, Usuario_Escuta_Musica UEM,Usuario U where  G.gid = M.gid and M.Mid = UEM.musica_id and U.idusuario= UEM.usuario_id and    U.nomeusuario = ? and U.senha = ?)order by mid);";
	ArrayList<Musica> listaMusicas = new ArrayList<Musica>();
	this.connection = new ConnectionFactory().getConnection();
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, nomeusuario);
		stmt.setString(2, senha);
		stmt.setString(3, nomeusuario);
		stmt.setString(4, senha);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("mid");
			String gdescricao = rs.getString("gdescricao");
		    String  nome = rs.getString("nome");
			Musica musica = new Musica(nome,id,gdescricao);
			
			listaMusicas.add(musica);
		}
		stmt.close();
}catch (SQLException e) {
	System.err.println(e.getMessage());
} finally {
	try {
		this.connection.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
return listaMusicas;
}
	public ArrayList<Musica> MusicasEscuta(String nomeusuario,String senha){
		String sql = " Select Distinct M.mid,M.nome,G.gdescricao from Musica M,Genero g, Usuario_Escuta_Musica UEM,Usuario U where g.gid = m.gid and  M.Mid = UEM.musica_id and U.idusuario= UEM.usuario_id and   U.nomeusuario = ? and U.senha = ? order by mid;";
		ArrayList<Musica> listaMusicasEscuta = new ArrayList<Musica>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeusuario);
			stmt.setString(2,senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("mid");
			    String  nome = rs.getString("nome");
			    String gdescricao = rs.getString("gdescricao");
				Musica musica = new Musica(nome,id,gdescricao);
				
				listaMusicasEscuta.add(musica);
			}
			stmt.close();
	}catch (SQLException e) {
		System.err.println(e.getMessage());
	} finally {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return listaMusicasEscuta;
	}
	public boolean addEscutaMusica(Usuario usuario,Musica musica) {
		String sql = "INSERT INTO Usuario_Escuta_Musica(usuario_id,musica_id) VALUES (?,?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getId());
			stmt.setInt(2, musica.getMid());
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
		
	
	public boolean deleteUser(int id) {
		String sql = "DELETE FROM usuario WHERE idusuario = ?";
		
		this.connection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public Usuario getUserbyname(String nome) {
		String sql = "Select * From usuario u where ? = u.nomeusuario;";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Usuario usuario = new Usuario(rs.getString("nomeusuario"));
			stmt.close();
			return usuario;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
		return null;
	}
	public Usuario getUserbynameandSenha(String nome,String senha) {
		String sql = "Select * From usuario u where ? = u.nomeusuario and ? = u.senha;";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Usuario usuario = new Usuario(rs.getString("nomeusuario"),rs.getString("senha"));
			stmt.close();
			return usuario;
			
		}catch (SQLException e) {
			
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
		return null;
	}
	
	public int getIdbyname(String nome) {
		String sql = "Select u.idusuario From usuario u where ? = u.nomeusuario;";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Integer id = (rs.getInt("idusuario"));
			stmt.close();
			return id;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
		return 0;
	}
	
	public Usuario getUserById(int id){
		String sql = "SELECT * FROM usuario WHERE id = ?;";

		try {
	
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			
			ResultSet rs = stmt.executeQuery();
			rs.next();

			Usuario usuario = new Usuario(id, rs.getString("nome"));
			
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Usuario nao encontrado");
		return null;
	}
	
	
}
