marketlistings is the name of the table

MarketListings is the Entity Object

marketdata.csv can be used to import data to whichever db you use. This csv file is retrieved from Nasdaq and 
some modifications done before using it. ipoyear 0 changed to 1900. In industry data forward slash changed to -.
(otherwise it will fail while fetching data). Some unwanted characters replaced in Symbols. Changed Market cap to remove $ 
symbol and Billion market cap value multipled by 1000 to represent everything in Millions.

HomeController is the entry point

Starting screen shows distinct sectors and the nav bar has options to explore, create a watchlist and few useful links under resources

MarketListingsSpecification - used for forming queries

MarketListingsRepository - extends PagingAndSortingRepository and JPASpecificationExecutor

search method - 
takes sector, marketcapmin, marketcapmax, ipoyearmin, ipoyearmax, lastsalemin, lastsalemaxx

Based on the input parameters SearchQuery gets formed using the MarketListingsSpecification

Based on the pagesize and requested page records are retrieved and sent back in the Model

PortfolioController has the functionality for creating Portfolios/Watchlists and adding listings to the Watchlist.

Angular code is under ui folder. The component names and service names are self explanatory

