import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Watchlist, WatchlistItem } from './watchlist';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WatchlistService {
  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  getAllWatchlists(): Observable<Watchlist[]> {
    return this.http
      .get<Watchlist[]>(this.baseUrl + '/portfolios/')
      .pipe(catchError(this.handleError));
  }

  createWatchlist(watchlist: Watchlist): Observable<Watchlist> {
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    let options = { headers: headers };
    return this.http
      .post<Watchlist>(this.baseUrl + '/portfolios/', watchlist, options)
      .pipe(catchError(this.handleError));
  }

  getWatchlistItems(routePath: string): Observable<WatchlistItem[]> {
    routePath = routePath.replace('watchlists', 'portfolios');
    return this.http
      .get<WatchlistItem[]>(this.baseUrl + routePath)
      .pipe(catchError(this.handleError));
  }

  createWatchlistItem(
    watchlistItem: WatchlistItem,
    routePath: string
  ): Observable<WatchlistItem> {
    routePath = routePath.replace('watchlists', 'portfolios');
    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    let options = { headers: headers };
    return this.http
      .post<WatchlistItem>(this.baseUrl + routePath, watchlistItem, options)
      .pipe(catchError(this.handleError));
  }

  private extractData(res: Response): any {
    let body = res;
    return body || {};
  }

  private handleError(error: Response | any) {
    //console.error(error.message || error);
    return throwError(error.status);
  }
}
