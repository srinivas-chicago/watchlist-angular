package com.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.data.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

}
