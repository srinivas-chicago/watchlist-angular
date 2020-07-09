package com.market.data;

public class MarketListingsDto {
	private String symbol;
    private String name;
    private Double lastsale;
    private Double marketcap;
    private Integer ipoyear;
    private String sector;
    private String industry;
    
	public MarketListingsDto(MarketListings mktListing) {
		super();
		this.symbol = mktListing.getSymbol();
		this.name = mktListing.getName();
		this.lastsale = mktListing.getLastsale();
		this.marketcap = mktListing.getMarketcap();
		this.ipoyear = mktListing.getIpoyear();
		this.sector = mktListing.getSector();
		this.industry = mktListing.getIndustry();
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public Double getLastsale() {
		return lastsale;
	}

	public Double getMarketcap() {
		return marketcap;
	}

	public Integer getIpoyear() {
		return ipoyear;
	}

	public String getSector() {
		return sector;
	}

	public String getIndustry() {
		return industry;
	}  
    
}
