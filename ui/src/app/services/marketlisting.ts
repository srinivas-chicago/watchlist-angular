export interface MarketListingResponse {
  content: MarketListing[];
  totalElements: number;
}

export interface MarketListing {
  symbol: string;
  name: string;
  lastsale: number;
  marketcap: number;
  ipoyear: number;
  sector: string;
  industry: string;
}
