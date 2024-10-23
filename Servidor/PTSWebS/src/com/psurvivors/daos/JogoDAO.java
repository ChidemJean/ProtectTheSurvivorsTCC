package com.psurvivors.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.psurvivors.pjs.Cena;
import com.psurvivors.pjs.Conquista;
import com.psurvivors.pjs.Habilidade;
import com.psurvivors.pjs.Jogador;
import com.psurvivors.pjs.Jogo;
import com.psurvivors.utils.Status;

public class JogoDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto-JPA");
	private static JogoDAO INSTANCE = new JogoDAO();
	
	public static JogoDAO getInstance(){
		return INSTANCE;
	}
	
	private JogoDAO(){}
	
	public int save(String nome, int xp, int pontuacao, int level, String tipoJogo) {
		Jogo jogo = new Jogo();

		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		Jogador jogador = (Jogador) query.getSingleResult();
		int idJogo;
		
		if (jogador != null){
			jogo.setJogador(jogador);
			jogo.setXp(xp);
			jogo.setPontuacaoTotal(pontuacao);
			jogo.setLevel(level);
			jogo.setTipoDeJogo(tipoJogo);
			 
			try {
				em.getTransaction().begin();
				em.persist(jogo);
				idJogo = jogo.getIdJogo();
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();
				return Status.ERRO_INTERNO;
			} finally {
				em.close();
			}
			return idJogo;
		} else {
			return Status.NAO_ENCONTRADO;
		}
	}

	public int remove(int id) {
		EntityManager em = emf.createEntityManager();
		Jogo jogo = em.find(Jogo.class, id);
		
		if (jogo != null){
			try {
				em.getTransaction().begin();
				em.remove(jogo);
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

	public int update(int id, int xp, int pontuacao, int level) {
		EntityManager em = emf.createEntityManager();
		Jogo jogo = em.find(Jogo.class, id);

		if (jogo != null){
			try {
				em.getTransaction().begin();
				em.detach(jogo);
				
				jogo.setXp(xp);
				jogo.setPontuacaoTotal(pontuacao);
				jogo.setLevel(level);
	
				em.merge(jogo);
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

	public Jogo findById(int id) {
		EntityManager em = emf.createEntityManager();

		Jogo jogo = em.find(Jogo.class, id);
		em.detach(jogo);
		jogo.setConquistas(null);
		jogo.setCenas(null);
		jogo.setHabilidades(null);
		return jogo;

	}
	
	public List<Cena> findAllCenas(int id) {
		EntityManager em = emf.createEntityManager();

		Jogo jogo = em.find(Jogo.class, id);
		List<Cena> cenas = jogo.getCenas();
		List<Cena> cenasDetached = new ArrayList<Cena>();
		for (Cena cena : cenas) {
			em.detach(cena);
			cena.setJogo(null);
			cena.setObjetos(null);
			cenasDetached.add(cena);
		}
		return cenasDetached;
	}

	public List<Conquista> findAllConquistas(int id) {
		EntityManager em = emf.createEntityManager();

		Jogo jogo = em.find(Jogo.class, id);
		List<Conquista> conquistas = jogo.getConquistas();
		List<Conquista> conquistasDetached = new ArrayList<Conquista>();
		for (Conquista conquista : conquistas) {
			em.detach(conquista);
			conquista.setJogador(null);
			conquista.setJogo(null);
			conquistasDetached.add(conquista);
		}
		return conquistasDetached;
	}

	public List<Habilidade> findAllHabilidades(int id) {
		EntityManager em = emf.createEntityManager();

		Jogo jogo = em.find(Jogo.class, id);
		List<Habilidade> habilidades = jogo.getHabilidades();
		List<Habilidade> habilidadesDetached = new ArrayList<Habilidade>();
		for (Habilidade habilidade : habilidades) {
			em.detach(habilidade);
			habilidade.setJogador(null);
			habilidade.setJogo(null);
			habilidadesDetached.add(habilidade);
		}
		return habilidadesDetached;
	}
}