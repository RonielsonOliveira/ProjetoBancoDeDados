package br.com.ufc.jdbcteste.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.classes.pojo.Cantor;
import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;

public class CantorDAO {
	// a conexão com o banco de dados
	private Connection connection;

	public CantorDAO() {
		
	}
	
	public ArrayList<Cantor> getListCantor() {
		String sql = "SELECT * FROM Cantor;";
		ArrayList<Cantor> listaCantores = new ArrayList<Cantor>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String nome = rs.getString("nome");
				String estilo = rs.getString("estilo");
				
				
				Cantor cantor = new Cantor(id, nome, estilo);
				
				listaCantores.add(cantor);
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
		return listaCantores;
	}

	public Cantor getCantorById(int id){
		String sql = "SELECT * FROM Cantor WHERE id = ?;";
		
		this.connection = new ConnectionFactory().getConnection();
		try {
	
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			//stmt.execute();
			
			ResultSet rs = stmt.executeQuery();
			rs.next();

			Cantor cantor = new Cantor(id, rs.getString("nome"), rs.getString("estilo"));
			
			stmt.close();
			
			return cantor;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
