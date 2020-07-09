import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MarketListingResponse } from './marketlisting';
import { SearchCriteria } from './searchcriteria';

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  private baseUrl = 'http://localhost:8080/marketlistings/search';
  constructor(private http: HttpClient) {}

  searchMktListings(
    searchCriteria: SearchCriteria
  ): Observable<MarketListingResponse> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    let options = { headers: headers };
    return this.http.post<MarketListingResponse>(
      this.baseUrl,
      searchCriteria,
      options
    );
  }
}
