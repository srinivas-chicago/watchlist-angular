package com.market.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.market.data.MarketListings;
import com.market.repositories.MarketListingsRepository;
import com.market.repositories.MarketListingsSpecification;
import com.market.repositories.SearchCriteria;

@Controller
public class HomeController
{
    @Autowired MarketListingsRepository marketRepo;
 
    @RequestMapping("/")
    public String home(Model model)
    {
    	List<String> sectorSearchOptions = new ArrayList();
    	List<String> sectors = marketRepo.findDistinctSector();
    	sectorSearchOptions.add("All");
    	sectorSearchOptions.addAll(sectors);
    	
        model.addAttribute("sectors", sectors);
        model.addAttribute("sectorSearchOptions", sectorSearchOptions);
        return "index";
    }
    
    @PostMapping(value = "/search",
            params = {"sector","marketcapmin","marketcapmax","ipoyearmin","ipoyearmax","lastsalemin","lastsalemax"})
    public String search(@RequestParam("sector") String sector,
            @RequestParam("marketcapmin") Double marketcapmin,
            @RequestParam("marketcapmax") Double marketcapmax,
            @RequestParam("ipoyearmin") Integer ipoyearmin,
            @RequestParam("ipoyearmax") Integer ipoyearmax,
            @RequestParam("lastsalemin") Double lastsalemin,
            @RequestParam("lastsalemax") Double lastsalemax,
            Model model)
    {
    	List<String> sectorSearchOptions = new ArrayList();
    	List<String> sectors = marketRepo.findDistinctSector();
    	sectorSearchOptions.add("All");
    	sectorSearchOptions.addAll(sectors);
    	
        model.addAttribute("sectors", sectors);
        model.addAttribute("sectorSearchOptions", sectorSearchOptions);
       
        MarketListingsSpecification spec1;
        
        if (sector.equalsIgnoreCase("all")){
        	spec1 = new MarketListingsSpecification(new SearchCriteria("sector", ":", ""));
        } else {
        	spec1 = new MarketListingsSpecification(new SearchCriteria("sector", ":", sector));
        }
        
        Specification<MarketListings> newSpec = Specification.where(spec1);
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
        
        Page<MarketListings> sectorPage = marketRepo.findAll(newSpec, pageable);
        model.addAttribute("sectorList", sectorPage.getContent());
        return "index";
    }
    
    @RequestMapping("/sector/{sector}/{page}")
    public ModelAndView sectorData(@PathVariable(name = "sector") String sector, @PathVariable(name = "page") int pageNo)
    {
    	ModelAndView modelAndView = new ModelAndView("sectorwise-pagedata");
        PageRequest pageable = PageRequest.of(pageNo - 1, 25, Sort.by("marketcap").descending().and(Sort.by("lastsale").ascending()));
        Page<MarketListings> sectorPage = marketRepo.findBySector(sector, pageable);
        int totalPages = sectorPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        
        List<String> industries = marketRepo.findDistinctIndustryBySector(sector);
        modelAndView.addObject("activeSectorList", true);
        modelAndView.addObject("sectorList", sectorPage.getContent());
        modelAndView.addObject("currentSector", sector);
        modelAndView.addObject("industryList", industries);
        return modelAndView;       
    }
    
    @RequestMapping("/industry/{industry}/{page}")
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
    }
}
