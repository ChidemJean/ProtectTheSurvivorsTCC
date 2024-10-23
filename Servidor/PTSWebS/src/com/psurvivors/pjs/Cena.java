package com.psurvivors.pjs;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cena {

	@Id
	@GeneratedValue
	private int idCena;
	
	private String tipoDeCena;
	
	private int pontuacaoCena;
	
	private boolean isStateSaved;
	
	@OneToOne
	@JoinColumn(name="idCapitulo")
	private Jogo jogo;
	
	@OneToMany(mappedBy="cena")
	private List<Objeto> objetos;

	public int getIdCena() {
		return idCena;
	}

	public void setIdCena(int idCena) {
		this.idCena = idCena;
	}

	public String getTipoDeCena() {
		return tipoDeCena;
	}

	public void setTipoDeCena(String tipoDeCena) {
		this.tipoDeCena = tipoDeCena;
	}

	public int getPontuacaoCena() {
		return pontuacaoCena;
	}

	public void setPontuacaoCena(int pontuacaoCena) {
		this.pontuacaoCena = pontuacaoCena;
	}

	public boolean isStateSaved() {
		return isStateSaved;
	}

	public void setStateSaved(boolean isStateSaved) {
		this.isStateSaved = isStateSaved;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public Cena(String tipoDeCena, int pontuacaoCena, boolean isStateSaved, Jogo jogo) {
		this.tipoDeCena = tipoDeCena;
		this.pontuacaoCena = pontuacaoCena;
		this.isStateSaved = isStateSaved;
		this.jogo = jogo;
	}

	public Cena() {}
	
}
