package com.psurvivors.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.psurvivors.pjs.Conquista;
import com.psurvivors.pjs.Jogador;
import com.psurvivors.pjs.Jogo;
import com.psurvivors.utils.Status;

public class ConquistaDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto-JPA");

	private static ConquistaDAO INSTANCE = new ConquistaDAO();
	
	public static ConquistaDAO getInstance(){
		return INSTANCE;
	}
	
	private ConquistaDAO(){}
	
	public int save(int id, String descricao) {
		EntityManager em = emf.createEntityManager();
		Jogo jogo = em.find(Jogo.class, id);
		
		if (jogo != null){
			Jogador jogador = jogo.getJogador();
	
			Conquista conquista = new Conquista();
			conquista.setDescricao(descricao);
			conquista.setJogador(jogador);
			conquista.setJogo(jogo);
	
			try {
				em.getTransaction().begin();
				em.persist(conquista);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();
				return Status.ERRO_INTERNO;
			} finally {
				em.close();
			}
			return Status.EXECUTADO_COM_SUCESSO;
		} else {
			return Status.NAO_ENCONTRADO;
		}
	}

	public int remove(int id) {
		EntityManager em = emf.createEntityManager();
		Conquista conquista = em.find(Conquista.class, id);
		
		if (conquista != null){
		
			try{
				em.getTransaction().begin();
				em.remove(conquista);
				em.getTransaction().commit();
			}catch(Exception e){
				em.getTransaction().rollback();
				e.printStackTrace();
				return Status.ERRO_INTERNO;
			}finally {
				em.close();
			}
			return Status.EXECUTADO_COM_SUCESSO;
		} else {
			return Status.NAO_ENCONTRADO;
		}
	}
	
	public Conquista findConquista(int id){
		EntityManager em = emf.createEntityManager();
		Conquista conquista = em.find(Conquista.class, id);
		em.detach(conquista);
		conquista.setJogador(null);
		conquista.setJogo(null);
		return conquista;
	}
}


















