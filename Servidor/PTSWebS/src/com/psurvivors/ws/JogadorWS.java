package com.psurvivors.ws;

import java.util.List;

import com.psurvivors.utils.GenerateToken;
import com.psurvivors.daos.JogadorDAO;
import com.psurvivors.pjs.Conquista;
import com.psurvivors.pjs.Habilidade;
import com.psurvivors.pjs.Jogador;
import com.psurvivors.pjs.Jogo;
import com.psurvivors.utils.Status;

public class JogadorWS {

	
	public int logar(String nome, String senha){
		
		Jogador jogador = JogadorDAO.getInstance().findByName(nome);
		
		System.out.println("");
		System.out.println("nome: " + nome + " senha: " + senha);
		
		if (jogador != null && jogador.getSenha() != null){
			System.out.println("Senha banco: " + jogador.getSenha() + " senha: " + (jogador.getSenha().split("!")[1]));
			System.out.println("");
			
			if ((jogador.getSenha().split("!")[1]).equals(senha)){
				String salt = GenerateToken.randomSALT();
				String token = GenerateToken.generateToken(jogador.getNome(), salt);
				return JogadorDAO.getInstance().saveToken(jogador, token);
			} else {
				return Status.SENHA_INCORRETA;
			}
		} else {
			return Status.NAO_ENCONTRADO;
		}
	}
	
	public String getToken(String nome){
		
		Jogador jogador = JogadorDAO.getInstance().findByName(nome);
		
		if (jogador != null && jogador.isLogged()){
			return jogador.getToken();
		}
		return null;
		
	}
	
	public String getSalt(String nome){
		String salt = null;
		
		Jogador jogador = JogadorDAO.getInstance().findByName(nome);
		
		if (jogador != null && jogador.getSenha() != null){
			salt = jogador.getSenha().split("!")[0];
		}
		System.out.println("SALT: " + salt);
		return salt;
	}
	
	public int save(String nome, String email, String senha, String tipo){
		return JogadorDAO.getInstance().save(nome, email, senha, tipo);
	}
	
	public int remove(String token, String nome){
		if (JogadorDAO.getInstance().isLogged(token)){
			return JogadorDAO.getInstance().remove(nome);
		}
		return Status.SEM_PERMISSAO;
	}
	
	public int update(String token, String nome, String email, String senha, String tipo, int moeda){
		if (JogadorDAO.getInstance().isLogged(token)){
			return JogadorDAO.getInstance().update(nome, email, senha, tipo, moeda);
		} else {
			int statusLogin = this.logar(nome, senha);
			if (statusLogin == Status.EXECUTADO_COM_SUCESSO){
				return JogadorDAO.getInstance().update(nome, email, senha, tipo, moeda);
			}
			return statusLogin;
		}
	}
	
	public Jogador findById(int id){
		return JogadorDAO.getInstance().findById(id);
	}
	
	public Jogador findByName(String name){
		return JogadorDAO.getInstance().findByName(name);
	}
	
	public List<Jogador> findAll(){
		return JogadorDAO.getInstance().findAll();
	}
	
	public List<Jogo> findAllJogos(String name){
		return JogadorDAO.getInstance().findAllJogos(name);
	}
	
	public List<Habilidade> findAllHabilidades(String name){
		return JogadorDAO.getInstance().findAllHabilidades(name);
	}
	
	public List<Conquista> findAllConquistas(String name){
		return JogadorDAO.getInstance().findAllConquistas(name);
	}
	
}
