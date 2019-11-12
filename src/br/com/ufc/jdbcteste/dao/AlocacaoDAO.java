package br.com.ufc.jdbcteste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.classes.pojo.Alocacao;
import br.com.ufc.classes.pojo.Cantor;
import br.com.ufc.classes.pojo.Usuario;
import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;

public class AlocacaoDAO {
	// a conexão com o banco de dados
	private Connection connection;

	public AlocacaoDAO() {
		
	}

	public boolean alocar(Cantor cantor, Usuario user) {
		String sql = "INSERT INTO alocacao(id_usuario, id_livro) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, user.getId());
			stmt.setInt(2, cantor.getId());

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
	
	public ArrayList<Alocacao> getListAlocacao() {
		String sql = "SELECT * FROM alocacao;";
		ArrayList<Alocacao> listaAlocacoes = new ArrayList<Alocacao>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int idUsuario = Integer.parseInt(rs.getString("id_usuario"));
				UsuarioDAO userDAO = new UsuarioDAO();
				Usuario user = userDAO.getUserById(idUsuario);
				
				int idCantor = Integer.parseInt(rs.getString("id_cantor"));
				CantorDAO CantorDAO = new CantorDAO();
				Cantor cantor = CantorDAO.getCantorById(idCantor);
				
				Alocacao aloc = new Alocacao(user, cantor);
				
				listaAlocacoes.add(aloc);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaAlocacoes;
	}
}