package com.psurvivors.ws;

import java.util.List;

import com.psurvivors.daos.CenaDAO;
import com.psurvivors.daos.JogadorDAO;
import com.psurvivors.pjs.Cena;
import com.psurvivors.utils.Status;

public class CenaWS {
	
	public int save(String token, int id, String tipo, int pontuacao, boolean isStateSaved) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return CenaDAO.getInstance().save(id, tipo, pontuacao, isStateSaved);
		}
		return Status.SEM_PERMISSAO;
	}

	public int remove(String token, int id) {
		if (JogadorDAO.getInstance().isLogged(token)){
			int idJ = JogadorDAO.getInstance().findByToken(token).getIdJogador();
			int idCena = CenaDAO.getInstance().findById(id).getJogo().getJogador().getIdJogador();
			if (idJ == idCena){
				return CenaDAO.getInstance().remove(id);
			}
		}
		return Status.SEM_PERMISSAO;
	}

	public int update(String token, int id, String tipo, int pontuacao, boolean isStateSaved) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return CenaDAO.getInstance().update(id, tipo, pontuacao, isStateSaved);
		}
		
		return Status.SEM_PERMISSAO;
	}

	public Cena findById(int id) {
		return CenaDAO.getInstance().findById(id);
	}

	public List<Cena> findAll() {
		return CenaDAO.getInstance().findAll();
	}
	
}
