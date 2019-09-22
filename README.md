marketlistings is the name of the table

MarketListings is the Entity Object

marketdata.csv can be used to import data to whichever db you use. This csv file is retrieved from Nasdaq and 
some modifications done before using it. ipoyear 0 changed to 1900. In industry data forward slash changed to -.
(otherwise it will fail while fetching data). Some unwanted characters replaced in Symbols. Changed Market cap to remove $ 
symbol and Billion market cap value multipled by 1000 to represent everything in Millions.

HomeController is the entry point

Starting screen shows distinct sectors and a search screen

MarketListingsSpecification - used for forming queries

MarketListingsRepository - extends PagingAndSortingRepository and JPASpecificationExecutor

search method - 
takes sector, marketcapmin, marketcapmax, ipoyearmin, ipoyearmax, lastsalemin, lastsalemaxx

Based on the input parameters SearchQuery gets formed using the MarketListingsSpecification

Based on the pagesize and requested page records are retrieved and sent back in the Model

There are other methods to retrieve sector wise data by lastsale price and industry wise

