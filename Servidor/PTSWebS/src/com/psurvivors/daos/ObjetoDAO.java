package com.psurvivors.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.psurvivors.pjs.Cena;
import com.psurvivors.pjs.Jogo;
import com.psurvivors.pjs.Objeto;
import com.psurvivors.utils.Status;

public class ObjetoDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto-JPA");
	
	private static ObjetoDAO INSTANCE = new ObjetoDAO();
	
	public static ObjetoDAO getInstance(){
		return INSTANCE;
	}
	
	private ObjetoDAO(){}

	public int save(int id, int tipoDeObjeto, int posicaoX, int posicaoY, float angle, int life, int quantidade) {
		EntityManager em = emf.createEntityManager();
		Cena cena = em.find(Cena.class, id);
		
		if (cena != null){

			Objeto objeto = new Objeto();
			objeto.setTipoDeObjeto(tipoDeObjeto);
			objeto.setPosicaoX(posicaoX);
			objeto.setPosicaoY(posicaoY);
			objeto.setAngle(angle);
			objeto.setLife(life);
			objeto.setQuantidade(quantidade);
			objeto.setCena(cena);
			
			try {
				em.getTransaction().begin();
				em.persist(objeto);
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
		Objeto objeto = em.find(Objeto.class, id);
		
		if (objeto != null){
		
			try {
				em.getTransaction().begin();
				em.remove(objeto);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				return Status.ERRO_INTERNO;
			} finally {
				em.close();
			}
			return Status.EXECUTADO_COM_SUCESSO;
			
		} else {
			return Status.NAO_ENCONTRADO;
		}
	}

	public int update(int id, int tipoDeObjeto, int posicaoX, int posicaoY, float angle, int life, int quantidade) {
		EntityManager em = emf.createEntityManager();
		Objeto objeto = em.find(Objeto.class, id);

		if (objeto != null){
		
			try {
				em.getTransaction().begin();
				em.detach(objeto);
				
				objeto.setTipoDeObjeto(tipoDeObjeto);
				objeto.setPosicaoX(posicaoX);
				objeto.setPosicaoY(posicaoY);
				objeto.setAngle(angle);
				objeto.setLife(life);
				objeto.setQuantidade(quantidade);
				
				em.merge(objeto);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				return Status.ERRO_INTERNO;
			} finally {
				em.close();
			}
			return Status.EXECUTADO_COM_SUCESSO;
		} else {
			return Status.NAO_ENCONTRADO;
		}
	}

	public Objeto findById(int id) {
		EntityManager em = emf.createEntityManager();

		Objeto objeto = em.find(Objeto.class, id);
		em.detach(objeto); 
		return objeto;
	}

	public List<Objeto> findAll() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select o from Objeto o", Objeto.class);
		List<Objeto> objetos = query.getResultList();
		List<Objeto> objetosDetached = new ArrayList<Objeto>();
		for (Objeto objeto : objetos) {
			em.detach(objeto);
			objeto.setCena(null);
			objetosDetached.add(objeto);
		}
		return objetosDetached;
	}
	
	
}
