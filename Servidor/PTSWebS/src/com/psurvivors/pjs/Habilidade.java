package com.psurvivors.pjs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Habilidade {

	@Id
	@GeneratedValue
	private int idHabilidade;
	
	private String nome;
	
	private String descricao;
	
	private boolean isConstant;
	
	private float tempRest;
	
	@OneToOne
	@JoinColumn(name="idJogador")
	private Jogador jogador;
	
	@OneToOne
	@JoinColumn(name="idJogo")
	private Jogo jogo;

	public int getIdHabilidade() {
		return idHabilidade;
	}

	public void setIdHabilidade(int idHabilidade) {
		this.idHabilidade = idHabilidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isConstant() {
		return isConstant;
	}

	public void setConstant(boolean isConstant) {
		this.isConstant = isConstant;
	}

	public float getTempRest() {
		return tempRest;
	}

	public void setTempRest(float tempRest) {
		this.tempRest = tempRest;
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

	public Habilidade(String nome, String descricao, boolean isConstant,
			float tempRest, Jogador jogador, Jogo jogo) {
		this.nome = nome;
		this.descricao = descricao;
		this.isConstant = isConstant;
		this.tempRest = tempRest;
		this.jogador = jogador;
		this.jogo = jogo;
	}

	public Habilidade() {}
	
}
