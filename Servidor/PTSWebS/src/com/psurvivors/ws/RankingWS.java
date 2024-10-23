package com.psurvivors.ws;

import java.util.List;

import com.psurvivors.daos.RankingGlobalDAO;
import com.psurvivors.pjs.RankingGlobal;

public class RankingWS {
	
	public List<RankingGlobal> getRanking(){
		
		return RankingGlobalDAO.getInstance().find();
		
	}
	
}
