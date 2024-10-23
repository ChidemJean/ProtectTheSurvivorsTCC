package com.psurvivors.ws;

import com.psurvivors.daos.ConquistaDAO;
import com.psurvivors.daos.JogadorDAO;
import com.psurvivors.pjs.Conquista;
import com.psurvivors.utils.Status;

public class ConquistaWS {
	
	public int save(String token, int id, String descricao) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return ConquistaDAO.getInstance().save(id, descricao);
		}
		return Status.SEM_PERMISSAO;
	}

	public int remove(String token, int id) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return ConquistaDAO.getInstance().remove(id);
		}
		return Status.SEM_PERMISSAO;
	}

	public Conquista findById(int id) {
		return ConquistaDAO.getInstance().findConquista(id);
	}
	
}
