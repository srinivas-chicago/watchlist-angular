package com.market.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.market.data.MarketListings;

public interface MarketListingsRepository extends PagingAndSortingRepository<MarketListings, String>, JpaSpecificationExecutor<MarketListings>{

	@Query("SELECT DISTINCT a.sector FROM MarketListings a")
	List<String> findDistinctSector();
	
	@Query("SELECT DISTINCT a.industry FROM MarketListings a where sector = ?1")
	List<String> findDistinctIndustryBySector(String sector);
	
	Page<MarketListings> findBySector(String sector, Pageable pageable);
	
	Page<MarketListings> findByIndustry(String industry, Pageable pageable);
}
