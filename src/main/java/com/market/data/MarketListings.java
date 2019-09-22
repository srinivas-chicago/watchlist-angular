package com.market.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marketlistings")
public class MarketListings {
	@Id 
    private String symbol;
    private String name;
    private Double lastsale;
    private Double marketcap;
    private Integer ipoyear;
    private String sector;
    private String industry;	
	
    public MarketListings () {    	
    }
    	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLastsale() {
		return lastsale;
	}
	public void setLastsale(Double lastSale) {
		this.lastsale = lastSale;
	}
	public Double getMarketcap() {
		return marketcap;
	}
	public void setMarketcap(Double marketCap) {
		this.marketcap = marketCap;
	}
	public Integer getIpoyear() {
		return ipoyear;
	}
	public void setIpoyear(Integer ipoYear) {
		this.ipoyear = ipoYear;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
    
    
    
}
