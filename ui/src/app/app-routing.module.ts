import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SectorListComponent } from './components/sector-list/sector-list.component';
import { SearchComponent } from './components/search/search.component';
import { MarketlistingComponent } from './components/marketlisting/marketlisting.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { WatchlistdetailComponent } from './components/watchlistdetail/watchlistdetail.component';

const routes: Routes = [
  { path: '', redirectTo: 'sectors', pathMatch: 'full' },
  { path: 'sectors', component: SectorListComponent },
  { path: 'sectors/:id', component: MarketlistingComponent },
  { path: 'search', component: SearchComponent },
  { path: 'watchlists', component: WatchlistComponent },
  { path: 'watchlists/:id/items', component: WatchlistdetailComponent },
  { path: 'resources', component: ResourcesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
