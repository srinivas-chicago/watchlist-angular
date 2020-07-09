package com.market.service;

import java.util.List;

import com.market.data.Portfolio;
import com.market.data.PortfolioItem;

public interface PortfolioService {

	 List<Portfolio> getAllPortfolios();
	 List<PortfolioItem> getPortfolioItems(int id);
	 Portfolio createPortfolio(Portfolio pfolio);
	 PortfolioItem createPorfolioItem(PortfolioItem pItem, int pfolioId);
     
}
