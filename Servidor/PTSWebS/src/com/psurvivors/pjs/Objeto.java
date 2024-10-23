package com.psurvivors.pjs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Objeto {

	@Id
	@GeneratedValue
	private int idObjeto;
	
	private int tipoDeObjeto;
	
	private float posicaoX;
	
	private float posicaoY;
	
	private float angle;
	
	private int life;
	
	private int quantidade;
	
	@OneToOne
	@JoinColumn(name="idCena")
	private Cena cena;
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	public int getTipoDeObjeto() {
		return tipoDeObjeto;
	}

	public void setTipoDeObjeto(int tipoDeObjeto) {
		this.tipoDeObjeto = tipoDeObjeto;
	}

	public float getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(float posicaoX) {
		this.posicaoX = posicaoX;
	}

	public float getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(float posicaoY) {
		this.posicaoY = posicaoY;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Cena getCena() {
		return cena;
	}

	public void setCena(Cena cena) {
		this.cena = cena;
	}
	
	public Objeto(int tipoDeObjeto, float posicaoX, float posicaoY, float angle, int life, int quantidade, Cena cena) {
		this.tipoDeObjeto = tipoDeObjeto;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.angle = angle;
		this.life = life;
		this.quantidade = quantidade;
		this.cena = cena;
	}

	public Objeto() {}

}
