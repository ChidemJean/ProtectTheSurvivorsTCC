package com.psurvivors.pjs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Conquista {

	@Id
	@GeneratedValue
	private int idConquista;
	
	private String descricao;
		
	@OneToOne
	@JoinColumn(name="idJogador")
	private Jogador jogador;
	
	@OneToOne
	@JoinColumn(name="idJogo")
	private Jogo jogo;
	
	public int getIdConquista() {
		return idConquista;
	}

	public void setIdConquista(int idConquista) {
		this.idConquista = idConquista;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public Conquista(String descricao, Jogador jogador, Jogo jogo) {
		this.descricao = descricao;
		this.jogador = jogador;
		this.jogo = jogo;
	}

	public Conquista() {}
	
}
