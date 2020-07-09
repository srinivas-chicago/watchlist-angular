import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MarketlistingService {
  private baseUrl = 'http://localhost:8080/marketlistings/sectors/';
  constructor(private http: HttpClient) {}

  listMarketListings(request, theSector: string) {
    const endpoint = this.baseUrl + theSector;
    const params = request;
    return this.http.get(endpoint, { params });
  }
}
