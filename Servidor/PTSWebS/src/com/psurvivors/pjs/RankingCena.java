package com.psurvivors.pjs;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RankingCena implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(optional=false)
	@JoinColumn(name="idJogador")
	private Jogador jogador;
	
	private int posicaoRankingCena;
	
	@OneToOne
	@JoinColumn(name="idCena")
	private Cena cena;

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public int getPosicaoRankingCena() {
		return posicaoRankingCena;
	}

	public void setPosicaoRankingCena(int posicaoRankingCena) {
		this.posicaoRankingCena = posicaoRankingCena;
	}

	public Cena getCena() {
		return cena;
	}

	public void setCena(Cena cena) {
		this.cena = cena;
	}

	public RankingCena(Jogador jogador, int posicaoRankingCena, Cena cena, int pontuacao) {
		this.jogador = jogador;
		this.posicaoRankingCena = posicaoRankingCena;
		this.cena = cena;
	}

	public RankingCena() {}
	
}
