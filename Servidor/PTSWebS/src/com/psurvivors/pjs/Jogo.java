package com.psurvivors.pjs;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Jogo {

	@Id
	@GeneratedValue
	private int idJogo;
	
	private int pontuacaoTotal;
	
	private String tipoDeJogo;
	
	@OneToOne
	@JoinColumn(name = "idJogador")
	private Jogador jogador;
	
	private int level;
	
	private int xp;
	
	@OneToMany(mappedBy = "jogo")
	private List<Cena> cenas;
	
	@OneToMany(mappedBy="jogo")
	private List<Conquista> conquistas;
	
	@OneToMany(mappedBy="jogo")
	private List<Habilidade> habilidades;
	
	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}

	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}

	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = pontuacaoTotal;
	}

	public String getTipoDeJogo() {
		return tipoDeJogo;
	}

	public void setTipoDeJogo(String tipoDeJogo) {
		this.tipoDeJogo = tipoDeJogo;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public List<Cena> getCenas() {
		return cenas;
	}

	public void setCenas(List<Cena> cenas) {
		this.cenas = cenas;
	}

	public List<Conquista> getConquistas() {
		return conquistas;
	}

	public void setConquistas(List<Conquista> conquistas) {
		this.conquistas = conquistas;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}
	
	public Jogo(String tipoDeJogo, Jogador jogador) {
		this.pontuacaoTotal = 0;
		this.tipoDeJogo = tipoDeJogo;
		this.jogador = jogador;
		this.level = 1;
		this.xp = 0;
	}

	public Jogo() {}
	
}
