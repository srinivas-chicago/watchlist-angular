package com.market.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "portfolio")
public class Portfolio {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="PORTFOLIO_ID")
    private int id;
        
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE")
    private java.util.Date createdDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="portfolio",cascade = CascadeType.ALL)
    private List<PortfolioItem> portfolioItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	@JsonIgnore
	public List<PortfolioItem> getPortfolioItems() {
		return portfolioItems;
	}

	public void setPortfolioItems(List<PortfolioItem> portfolioItems) {
		this.portfolioItems = portfolioItems;
	}
	
	public void addPortfolioItem(PortfolioItem portfolioItem) {
		
		if (portfolioItems == null) {
			portfolioItems = new ArrayList<>();
		}
		
		PortfolioItem found = portfolioItems.stream()
				  .filter(pfolioItem -> portfolioItem.getSymbol().equals(pfolioItem.getSymbol()))
				  .findAny()
				  .orElse(null);
		
		if (found == null) {		
			portfolioItems.add(portfolioItem);
			portfolioItem.setPortfolio(this);
		}
	}
    
    

}
