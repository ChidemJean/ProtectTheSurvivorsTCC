package com.psurvivors.ws;

import com.psurvivors.daos.JogadorDAO;
import com.psurvivors.daos.ObjetoDAO;
import com.psurvivors.pjs.Objeto;
import com.psurvivors.utils.Status;

public class ObjetoWS {

	public int save(String token, int id, int tipoDeObjeto, int posicaoX, int posicaoY, float angle, int life, int quantidade) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return ObjetoDAO.getInstance().update(id, tipoDeObjeto, posicaoX, posicaoY, angle, life, quantidade);
		}
		return Status.SEM_PERMISSAO;
	}

	public int remove(String token, int id) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return ObjetoDAO.getInstance().remove(id);
		}
		return Status.SEM_PERMISSAO;
	}

	public int update(String token, int id, int tipoDeObjeto, int posicaoX, int posicaoY, float angle, int life, int quantidade) {
		if (JogadorDAO.getInstance().isLogged(token)){
			return ObjetoDAO.getInstance().update(id, tipoDeObjeto, posicaoX, posicaoY, angle, life, quantidade);
		}
		return Status.SEM_PERMISSAO;
	}

	public Objeto findById(int id) {
		return ObjetoDAO.getInstance().findById(id);
	}

	
}
