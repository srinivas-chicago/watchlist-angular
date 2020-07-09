package com.market.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.market.data.MarketListings;
import com.market.data.MarketListingsDto;
import com.market.data.SearchCriteriaVO;
import com.market.repositories.MarketListingsRepository;
import com.market.repositories.MarketListingsSpecification;
import com.market.repositories.SearchCriteria;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/marketlistings")
public class HomeController
{
    @Autowired MarketListingsRepository marketRepo;
 
    /*@RequestMapping("/")
    public String home(Model model)
    {
    	List<String> sectorSearchOptions = new ArrayList();
    	List<String> sectors = marketRepo.findDistinctSector();
    	sectorSearchOptions.add("All");
    	sectorSearchOptions.addAll(sectors);
    	
        model.addAttribute("sectors", sectors);
        model.addAttribute("sectorSearchOptions", sectorSearchOptions);
        return "index";
    }*/
    
    @PostMapping(value = "/search")
    public Page<MarketListingsDto> search(@RequestBody SearchCriteriaVO searchOptions)
    {
    	
        MarketListingsSpecification spec1;
        
        if (searchOptions.getSector().equalsIgnoreCase("all")){
        	spec1 = new MarketListingsSpecification(new SearchCriteria("sector", ":", ""));
        } else {
        	spec1 = new MarketListingsSpecification(new SearchCriteria("sector", ":", searchOptions.getSector()));
        }
        
        Specification<MarketListings> newSpec = Specification.where(spec1);
        
        Double marketcapmin = searchOptions.getMarketcapmin();
        Double marketcapmax = searchOptions.getMarketcapmax();
        Double lastsalemin = searchOptions.getLastsalemin();
        Double lastsalemax = searchOptions.getLastsalemax();
        Integer ipoyearmin = searchOptions.getIpoyearmin();
        Integer ipoyearmax = searchOptions.getIpoyearmax();
        
        if (marketcapmin != null && marketcapmin != 0) {
        	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("marketcap", ">", marketcapmin)));
        }
        if (marketcapmax != null && marketcapmax != 0) {
        	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("marketcap", "<", marketcapmax)));
        }
        if (ipoyearmin != null && ipoyearmin != 0) {
        	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("ipoyear", ">", ipoyearmin)));
        }
        if (ipoyearmax != null && ipoyearmax != 0) {
        	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("ipoyear", "<", ipoyearmax)));
        }
        if (lastsalemin != null && lastsalemin != 0) {
        	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("lastsale", ">", lastsalemin)));
        }
        if (lastsalemax != null && lastsalemax != 0) {
        	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("lastsale", "<", lastsalemax)));
        }
        PageRequest pageable = PageRequest.of(0, 50, Sort.by("marketcap").descending().and(Sort.by("lastsale").ascending()));
        
        Page<MarketListings> searchPage = marketRepo.findAll(newSpec, pageable);
        
        List<MarketListingsDto> searchData = searchPage
                .stream()
                .map(MarketListingsDto::new)
                .collect(toList());
        
        return new PageImpl<>(searchData, pageable, searchPage.getTotalElements());
        
    }
    
    @RequestMapping("/sectors/{sector}")
    public Page<MarketListingsDto> sectorData(@PathVariable(name = "sector") String sector, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size)
    {    	
    	PageRequest pageRequest = PageRequest.of(page, size, Sort.by("marketcap").descending().and(Sort.by("lastsale").ascending()));    	
        
        Page<MarketListings> sectorPage = marketRepo.findBySector(sector, pageRequest);
        
        List<MarketListingsDto> sectorData = sectorPage
          .stream()
          .map(MarketListingsDto::new)
          .collect(toList());

        return new PageImpl<>(sectorData, pageRequest, sectorPage.getTotalElements());        
        
    }
    
    /*@RequestMapping("/industry/{industry}/{page}")
    public ModelAndView industryData(@PathVariable(name = "industry") String industry, @PathVariable(name = "page") int pageNo)
    {
    	ModelAndView modelAndView = new ModelAndView("industrywise-pagedata");
        PageRequest pageable = PageRequest.of(pageNo - 1, 50, Sort.by("marketcap").descending().and(Sort.by("lastsale").ascending()));
        Page<MarketListings> industryPage = marketRepo.findByIndustry(industry, pageable);
        System.out.println(industryPage.getContent().size());
        int totalPages = industryPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        
        //List<String> industries = marketRepo.findDistinctIndustryBySector(sector);
       
        modelAndView.addObject("industryList", industryPage.getContent());
        modelAndView.addObject("currentIndustry", industry);
        //modelAndView.addObject("industryList", industries);
        return modelAndView;       
    }
    
    @RequestMapping("/lastsale/{sector}/{minprice}/{maxprice}/{pageNo}")
    public ModelAndView sectorLastSaleData(@PathVariable(name = "sector") String sector, @PathVariable(name = "minprice") int minPrice, @PathVariable(name = "maxprice") int maxPrice, @PathVariable(name = "pageNo") int pageNo)
    {
    	ModelAndView modelAndView = new ModelAndView("lastsale-pagedata");
                
        MarketListingsSpecification spec1;
        
        spec1 = new MarketListingsSpecification(new SearchCriteria("sector", ":", sector));        
        
        Specification<MarketListings> newSpec = Specification.where(spec1);
        
        newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("lastsale", ">", minPrice)));
        
       	newSpec = newSpec.and(new MarketListingsSpecification(new SearchCriteria("lastsale", "<", maxPrice)));
        
        PageRequest pageable = PageRequest.of(pageNo - 1, 30, Sort.by("marketcap").descending().and(Sort.by("lastsale").ascending()));
        
        Page<MarketListings> lastSalePage = marketRepo.findAll(newSpec, pageable);
        
        
        int totalPages = lastSalePage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }        
        
        modelAndView.addObject("sectorList", lastSalePage.getContent());
        modelAndView.addObject("currentSector", sector);
        modelAndView.addObject("currentMinPrice", minPrice);
        modelAndView.addObject("currentMaxPrice", maxPrice);
        
        return modelAndView;       
    }*/
}
