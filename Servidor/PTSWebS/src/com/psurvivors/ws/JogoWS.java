package com.psurvivors.ws;

import java.util.List;

import com.psurvivors.daos.JogadorDAO;
import com.psurvivors.daos.JogoDAO;
import com.psurvivors.pjs.Cena;
import com.psurvivors.pjs.Conquista;
import com.psurvivors.pjs.Habilidade;
import com.psurvivors.pjs.Jogo;
import com.psurvivors.utils.Status;

public class JogoWS {

	public int save(String token, String nome, int xp, int pontuacao, int level, String tipoJogo){
		if (JogadorDAO.getInstance().isLogged(token)){
			return JogoDAO.getInstance().save(nome, xp, pontuacao, level, tipoJogo);
		}
		return Status.SEM_PERMISSAO;
	}
	
	public int update(String token, int id, int xp, int pontuacao, int level){
		if (JogadorDAO.getInstance().isLogged(token)){
			return JogoDAO.getInstance().update(id, xp, pontuacao, level);
		}
		return Status.SEM_PERMISSAO;
	}
	
	public int remove(String token, int id){
		if (JogadorDAO.getInstance().isLogged(token)){
			return JogoDAO.getInstance().remove(id);
		}
		return Status.SEM_PERMISSAO;
	}
	
	public Jogo findById(int id){
		return JogoDAO.getInstance().findById(id);
	}
	
	public List<Conquista> findAllConquistas(int id){
		return JogoDAO.getInstance().findAllConquistas(id);
	}
	
	public List<Cena> findAllCenas(int id){
		return JogoDAO.getInstance().findAllCenas(id);
	}
	
	public List<Habilidade> findAllHabilidades(int id){
		return JogoDAO.getInstance().findAllHabilidades(id);
	}
	
}
