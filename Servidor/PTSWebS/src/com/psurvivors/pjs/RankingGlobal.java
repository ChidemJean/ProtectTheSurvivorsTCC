package com.psurvivors.pjs;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class RankingGlobal implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String tipoDeJogo;
	
	@Id
	private String nome;
	
	@Transient
	private int posicaoRankingGlobal;
	
	private int pontuacaoTotal;

	public String getTipoDeJogo() {
		return tipoDeJogo;
	}

	public void setTipoDeJogo(String tipoDeJogo) {
		this.tipoDeJogo = tipoDeJogo;
	}

	public String getNomeJogador() {
		return nome;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nome = nomeJogador;
	}

	public int getPosicaoRankingGlobal() {
		return posicaoRankingGlobal;
	}

	public void setPosicaoRankingGlobal(int posicaoRankingGlobal) {
		this.posicaoRankingGlobal = posicaoRankingGlobal;
	}

	public int getPontuacao() {
		return pontuacaoTotal;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacaoTotal = pontuacao;
	}

	public RankingGlobal() {}
	
}
