package com.psurvivors.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.psurvivors.pjs.Jogo;
import com.psurvivors.pjs.Cena;
import com.psurvivors.utils.Status;

public class CenaDAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto-JPA");

	private static CenaDAO INSTANCE = new CenaDAO();
	
	public static CenaDAO getInstance(){
		return INSTANCE;
	}
	
	private CenaDAO(){}
	
	public int save(int id, String tipo, int pontuacao, boolean isStateSaved) {
		EntityManager em = emf.createEntityManager();
		Jogo jogo = em.find(Jogo.class, id);
		
		if (jogo != null){
			
			Cena cena = new Cena();
			cena.setPontuacaoCena(pontuacao);
			cena.setTipoDeCena(tipo);
			cena.setStateSaved(isStateSaved);
			cena.setJogo(jogo);
			
			try {
				em.getTransaction().begin();
				em.persist(cena);
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
		Cena cena = em.find(Cena.class, id);
		
		if (cena != null){
		
			try {
				em.getTransaction().begin();
				em.remove(cena);
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

	public int update(int id, String tipo, int pontuacao, boolean isStateSaved) {
		EntityManager em = emf.createEntityManager();
		Cena cena = em.find(Cena.class, id);

		if (cena != null) {
		
			try {
				em.getTransaction().begin();
				em.detach(cena);
				cena.setPontuacaoCena(pontuacao);
				cena.setTipoDeCena(tipo);
				cena.setStateSaved(isStateSaved);
				em.merge(cena);
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

	public Cena findById(int id) {
		EntityManager em = emf.createEntityManager();

		Cena cena = em.find(Cena.class, id);
		em.detach(cena); 
		return cena;
	}

	public List<Cena> findAll() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select C from Cena C", Cena.class);
		List<Cena> cenas = query.getResultList();
		List<Cena> cenasDetached = new ArrayList<Cena>();
		for (Cena cena : cenas) {
			em.detach(cena);
			cena.setObjetos(null);
			cena.setJogo(null);
			cenasDetached.add(cena);
		}
		return cenasDetached;
	}
	
}
