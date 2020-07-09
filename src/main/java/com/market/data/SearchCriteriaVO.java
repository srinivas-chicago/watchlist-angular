package com.market.data;

public class SearchCriteriaVO {
	private String sector;
	private Double marketcapmin;
	private Double marketcapmax;
	private Integer ipoyearmin;
	private Integer ipoyearmax;
	private Double lastsalemin;
	private Double lastsalemax;
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Double getMarketcapmin() {
		return marketcapmin;
	}
	public void setMarketcapmin(Double marketcapmin) {
		this.marketcapmin = marketcapmin;
	}
	public Double getMarketcapmax() {
		return marketcapmax;
	}
	public void setMarketcapmax(Double marketcapmax) {
		this.marketcapmax = marketcapmax;
	}
	public Integer getIpoyearmin() {
		return ipoyearmin;
	}
	public void setIpoyearmin(Integer ipoyearmin) {
		this.ipoyearmin = ipoyearmin;
	}
	public Integer getIpoyearmax() {
		return ipoyearmax;
	}
	public void setIpoyearmax(Integer ipoyearmax) {
		this.ipoyearmax = ipoyearmax;
	}
	public Double getLastsalemin() {
		return lastsalemin;
	}
	public void setLastsalemin(Double lastsalemin) {
		this.lastsalemin = lastsalemin;
	}
	public Double getLastsalemax() {
		return lastsalemax;
	}
	public void setLastsalemax(Double lastsalemax) {
		this.lastsalemax = lastsalemax;
	}

}
