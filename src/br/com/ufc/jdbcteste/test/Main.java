package br.com.ufc.jdbcteste.test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

import br.com.ufc.classes.pojo.Cantor;
import br.com.ufc.classes.pojo.Musica;
import br.com.ufc.classes.pojo.Usuario;
import br.com.ufc.jdbcteste.dao.*;
import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsuarioDAO userDAO = new UsuarioDAO();
	    int option;
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.ENGLISH);
		boolean end = false;

		while(!end) {
			System.out.println("   ♪♫♩ APP MUSICA ♪♫♩   ");
			System.out.println("| 1 | Cadastrar usuario");
			System.out.println("| 2 | Fazer Login");
			System.out.println("| 0 | Sair");

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option){
			case 1:{
				String nome;
				System.out.println("Digite o nome do usuario:");
				nome = scanner.nextLine();
				System.out.println("Digete a senha do usuario");
				String senha = scanner.nextLine();
				
				if(userDAO.getUserbynameandSenha(nome, senha)==null) {
				Usuario user = new Usuario(nome,senha);
				if(userDAO.addUser(user)) {
					System.out.println("Inserido com sucesso!");
					break;
				}else {
					System.err.println("Erro ao inserir o usuÃ¡rio.");
					
				}
				break;
				}
				else {
					System.out.println("Nome do Usuario ja cadastrado");
					break;
				}
				
			}
			case 2:{
				System.out.println("Digite o nome do usuario:");
				String nome = scanner.nextLine();
				System.out.println("Digete a senha do usuario");
				String senha = scanner.nextLine();
				Usuario user = new Usuario(nome,senha);
				if(userDAO.getUserbynameandSenha(nome, senha)!=null) {
					while(!end) {
						System.out.println("♪♫♩   Menu Usuario  ♪♫♩");
						System.out.println("| 1 | Sugerir Musica");
						System.out.println("| 2 | Listar musicas que voce escuta");
						System.out.println("| 3 | Adicionar novo gosto musical");
						System.out.println("| 4 | Listar seus gostos musicais");
						System.out.println("| 5 | Apagar Conta");
						System.out.println("| 0 | Sair");

						option = scanner.nextInt();
						scanner.nextLine();

						switch (option){ 		
						case 1:{
							if(userDAO.SugerirMusica(nome,senha).size() == 0 ) {
							System.out.println("Voce ainda nao informou um genero que voce gosta");
							System.out.println("Desaja informa um tipo de musica que voce gosta?");
							System.out.println("1-Sim 2-Nao");
							int res;
							res = scanner.nextInt();
							if(res == 1) {
								System.out.println(userDAO.getListGenero());
								System.out.println("Digite o gid do genero que voce gosta");
								int resp = scanner.nextInt();
								userDAO.addGostoMusica(userDAO.getIdbyname(nome),resp);
								System.out.println("Gosto Musical Adicionado");	
							}
							else {
								break;
							}
							
							break;
							}
							else {
								System.out.println(userDAO.SugerirMusica(nome,senha));
								System.out.println("Deseja adicionar uma musica na sua lista de musicas que voce escutou?");
							    System.out.println("1-Sim 2-Nao");
							    int res = scanner.nextInt();
							    if (res == 1) {
							    	System.out.println(userDAO.SugerirMusica(nome,senha));
							    	System.out.println("Digite o mid da musica que voce quer adicionar na sua lista");
							    	int id_musica = scanner.nextInt();
									userDAO.addMusica(userDAO.getIdbyname(nome), id_musica);
									break;
							    	
							    }
							    else {
							    	break;
							    }
								
							}
						}
						case 2:{
							System.out.println(userDAO.MusicasEscuta(nome,senha));
							break;
						}
						
		
					case 3:{
						if ((userDAO.getListGeneroGosta(nome,userDAO.getUserbyname(nome).getSenha()).size() == userDAO.getListGenero().size())){
							System.out.println("Voce ja gosta de todos os generos");
							break;
						}
						else {
						System.out.println(userDAO.getListGeneroNaoGosta(nome,userDAO.getUserbynameandSenha(nome, senha).getSenha()));
						System.out.println("Digite o gid do genero que voce gosta");
						int id_genero = scanner.nextInt();
						userDAO.addGostoMusica(userDAO.getIdbyname(nome), id_genero);
						break;
					}
					}
					case 4:{
						System.out.println(userDAO.getListGeneroGosta(nome, senha));
						break;
					}
					case 5:{
						System.out.println("Tem certeza que quer deletar a sua conta?");
						System.out.println("1-Sim 2-Nao");
						int res = scanner.nextInt();
						if (res == 1) {
						if(userDAO.deleteUser(userDAO.getIdbyname(nome))) {
							System.out.println("Deletado com sucesso!");
						}else {
							System.err.println("Erro ao deletar o usuario.");
						}
						}
						else {
							break;
						}
					
					}
						
						
						case 0:{
							end = true;
							break;
						}
				}
			   
				
			}
			
			
	    
		}
				else {
					System.out.println("Usuario nao encontrado");
				}
	}
			}
		}
	}
}


	

