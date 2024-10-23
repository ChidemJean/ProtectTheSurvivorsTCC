package com.psurvivors.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.psurvivors.pjs.Conquista;
import com.psurvivors.pjs.Habilidade;
import com.psurvivors.pjs.Jogador;
import com.psurvivors.pjs.Jogo;
import com.psurvivors.utils.Status;

public class JogadorDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto-JPA");
	private static JogadorDAO INSTANCE = new JogadorDAO();
	
	public static JogadorDAO getInstance(){
		return INSTANCE;
	}
	
	private JogadorDAO(){}
	
	public int save(String nome, String email, String senha, String tipo) {
		Jogador jogador = new Jogador();
		jogador.setNome(nome);
		jogador.setEmail(email);
		jogador.setSenha(senha);
		jogador.setTipoDeJogador(tipo);

		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		List<Jogador> jogadores = query.getResultList();
		
		if (jogadores == null || jogadores.size() == 0) {
			try {
				em.getTransaction().begin();
				em.persist(jogador);
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
			return Status.JA_EXISTE;
		}
	}

	public int remove(String nome) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		Jogador jogador = (Jogador) query.getSingleResult();
		if (jogador != null){
			try {
				em.getTransaction().begin();
				em.remove(jogador);
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

	public int update(String nome, String email, String senha, String tipo, int moeda) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		Jogador jogador = (Jogador) query.getSingleResult();
		
		if (jogador != null){
			try {
				em.getTransaction().begin();
				em.detach(jogador);
	
				jogador.setMoeda(moeda);
				jogador.setNome(nome);
				jogador.setEmail(email);
				jogador.setSenha(senha);
				jogador.setTipoDeJogador(tipo);
	
				em.merge(jogador);
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
	
	public int saveToken(Jogador jogador, String token){
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.detach(jogador);

			jogador.setToken(token);
			jogador.setLogged(true);

			em.merge(jogador);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			return Status.ERRO_INTERNO;
		} finally {
			em.close();
		}
		return Status.EXECUTADO_COM_SUCESSO;
	}
	
	public boolean isLogged(String token){
		
		EntityManager em = emf.createEntityManager();
		Jogador jogador = null;
		
		try {
			Query query = em.createQuery("Select j from Jogador j where j.token = :token", Jogador.class);
			query.setParameter("token", token);
			jogador = (Jogador) query.getSingleResult();
			
			if (jogador.isLogged()){
				return true;
			}
			
		} catch (NoResultException e) {
			return false;
		} finally {
			em.close();
		}
		return false;
	}
	
	public Jogador findByToken(String token){
		
		EntityManager em = emf.createEntityManager();
		Jogador jogador = null;
		
		try {
			Query query = em.createQuery("Select j from Jogador j where j.token = :token", Jogador.class);
			query.setParameter("token", token);
			jogador = (Jogador) query.getSingleResult();
			em.detach(jogador);
			
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return jogador;
	}

	public Jogador findById(int id) {
		EntityManager em = emf.createEntityManager();

		Jogador jog = em.find(Jogador.class, id);
		em.detach(jog);
		jog.setConquistas(null);
		jog.setJogos(null);
		jog.setHabilidades(null);
		return jog;

	}

	public Jogador findByName(String name) {
		Jogador jogador = null;
		Jogador jogadorDetached = null;
		EntityManager em = emf.createEntityManager();

		try {
			Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
			query.setParameter("nome", name);
			jogador = (Jogador) query.getSingleResult();
			em.detach(jogador);
			jogador.setJogos(null);
			jogador.setConquistas(null);
			jogador.setHabilidades(null);
			jogadorDetached = jogador;
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
		return jogadorDetached;
	}

	public List<Jogador> findAll() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select j from Jogador j", Jogador.class);
		List<Jogador> jogadores = query.getResultList();
		List<Jogador> jogadoresDetached = new ArrayList<Jogador>();
		for (Jogador jogador : jogadores) {
			em.detach(jogador);
			jogador.setConquistas(null);
			jogador.setJogos(null);
			jogador.setHabilidades(null);
			jogadoresDetached.add(jogador);
		}
		return jogadoresDetached;

	}

	public List<Jogo> findAllJogos(String nome) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		Jogador jogador = (Jogador) query.getSingleResult();

		List<Jogo> jogos = jogador.getJogos();
		List<Jogo> jogosDetached = new ArrayList<Jogo>();
		for (Jogo jogo : jogos) {
			em.detach(jogo);
			jogo.setCenas(null);
			jogo.setHabilidades(null);
			jogo.setConquistas(null);
			jogo.setJogador(null);
			jogosDetached.add(jogo);
		}

		return jogosDetached;
	}

	public List<Conquista> findAllConquistas(String nome) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		Jogador jogador = (Jogador) query.getSingleResult();
		
		List<Conquista> conquistas = jogador.getConquistas();
		List<Conquista> conquistasDetached = new ArrayList<Conquista>();
		for (Conquista conquista : conquistas) {
			em.detach(conquista);
			conquista.setJogador(null);
			conquista.setJogo(null);
			conquistasDetached.add(conquista);
		}

		return conquistasDetached;
	}

	public List<Habilidade> findAllHabilidades(String nome) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select j from Jogador j where j.nome = :nome", Jogador.class);
		query.setParameter("nome", nome);
		Jogador jogador = (Jogador) query.getSingleResult();
		
		List<Habilidade> habilidades = jogador.getHabilidades();
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
