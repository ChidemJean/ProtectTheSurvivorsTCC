package com.psurvivors.pjs;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Jogador {

	@Id
	@GeneratedValue
	private int idJogador;
	
	private String nome;
	
	private String tipoDeJogador;

	private String email;
	
	private String senha;
	
	private int moeda;
	
	private boolean isLogged = false;
	
	private String token;

	@OneToMany(mappedBy = "jogador")
	private List<Jogo> jogos;
	
	@OneToMany(mappedBy="jogador")
	private List<Conquista> conquistas;
	
	@OneToMany(mappedBy="jogador")
	private List<Habilidade> habilidades;
	
	public int getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(int idJogador) {
		this.idJogador = idJogador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoDeJogador() {
		return tipoDeJogador;
	}

	public void setTipoDeJogador(String tipoDeJogador) {
		this.tipoDeJogador = tipoDeJogador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
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
	
	public int getMoeda() {
		return moeda;
	}

	public void setMoeda(int moeda) {
		this.moeda = moeda;
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Jogador(String nome, String tipoDeJogador, String email, String senha) {
		this.nome = nome;
		this.tipoDeJogador = tipoDeJogador;
		this.email = email;
		this.senha = senha;
	}

	public Jogador() {}
	
}
