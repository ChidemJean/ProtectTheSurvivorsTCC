package com.psurvivors.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.psurvivors.pjs.RankingGlobal;

public class RankingGlobalDAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto-JPA");
	private static RankingGlobalDAO INSTANCE = new RankingGlobalDAO();
	
	public static RankingGlobalDAO getInstance(){
		return INSTANCE;
	}
	
	public List<RankingGlobal> find(){
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("Select j from RankingGlobal j", RankingGlobal.class);
		List<RankingGlobal> list = query.getResultList();
		List<RankingGlobal> listDetached = new ArrayList<RankingGlobal>();
		for (RankingGlobal rankingGlobal : list) {
			em.detach(rankingGlobal);
			listDetached.add(rankingGlobal);
		}
		em.close();
		return listDetached;
	}
}