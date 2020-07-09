package com.market.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.data.Portfolio;
import com.market.data.PortfolioItem;
import com.market.repositories.PortfolioRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	PortfolioRepository portfolioRepository;

	@Override
	public List<Portfolio> getAllPortfolios() {
		return portfolioRepository.findAll();		
	}

	@Override
	public List<PortfolioItem> getPortfolioItems(int id) {
		List<PortfolioItem> pfolioItems = null;
		Portfolio pfolio = portfolioRepository.findById(id).orElse(null);
		if (pfolio != null) {
			pfolioItems = pfolio.getPortfolioItems();
		}
		
		return pfolioItems;
	}

	@Override
	public Portfolio createPortfolio(Portfolio pfolio) {
		return portfolioRepository.save(pfolio);		
	}

	@Override
	public PortfolioItem createPorfolioItem(PortfolioItem portfolioItem, int pfolioId) {
		// TODO Auto-generated method stub
		Portfolio pfolio = portfolioRepository.findById(pfolioId).orElse(null);
		if (pfolio != null) {
			pfolio.addPortfolioItem(portfolioItem);
			pfolio = portfolioRepository.save(pfolio);
			
			return pfolio.getPortfolioItems().stream()
			  .filter(pfolioItem -> portfolioItem.getSymbol().equals(pfolioItem.getSymbol()))
			  .findFirst().orElse(null);
		}
		return null;
	}

}
