import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { SectorListComponent } from './components/sector-list/sector-list.component';
import { SearchComponent } from './components/search/search.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { HttpClientModule } from '@angular/common/http';
import { MarketlistingComponent } from './components/marketlisting/marketlisting.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ResourcesComponent } from './components/resources/resources.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { WatchlistdetailComponent } from './components/watchlistdetail/watchlistdetail.component';

@NgModule({
  declarations: [
    AppComponent,
    SectorListComponent,
    SearchComponent,
    MarketlistingComponent,
    ResourcesComponent,
    WatchlistComponent,
    WatchlistdetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
