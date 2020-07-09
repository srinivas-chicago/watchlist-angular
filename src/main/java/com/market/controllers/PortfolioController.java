package com.market.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.data.Portfolio;
import com.market.data.PortfolioItem;
import com.market.service.PortfolioService;

@RestController
public class PortfolioController {
	
	@Autowired
	PortfolioService portfolioService;
	
	@GetMapping(path = "/portfolios")
	public ResponseEntity<List<Portfolio>> getAllPortfolios() {		
		List<Portfolio> portfolios = portfolioService.getAllPortfolios();		
		return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);		
	}
	
	@PostMapping(path = "/portfolios")
	public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portFolio) {
		Portfolio saved = portfolioService.createPortfolio(portFolio);
		return new ResponseEntity<Portfolio>(saved, HttpStatus.OK);		
	}
	
	@GetMapping(path = "/portfolios/{portfolioId}/items")
    public List<PortfolioItem> getPortfolioItems(@PathVariable (value = "portfolioId") Integer portfolioId){
		return portfolioService.getPortfolioItems(portfolioId);
	}
	
	@PostMapping(path = "/portfolios/{portfolioId}/items")
	public ResponseEntity<PortfolioItem> createPortfolioItem(@PathVariable (value = "portfolioId") Integer portfolioId, @RequestBody PortfolioItem portFolioItem) {
		PortfolioItem pItem = portfolioService.createPorfolioItem(portFolioItem, portfolioId);
		return new ResponseEntity<PortfolioItem>(pItem, HttpStatus.OK);		
	}
	

}
