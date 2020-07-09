import { Component, OnInit } from '@angular/core';
import { MarketlistingService } from 'src/app/services/marketlisting.service';
import { MarketListing } from 'src/app/services/marketlisting';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-marketlisting',
  templateUrl: './marketlisting.component.html',
  styleUrls: ['./marketlisting.component.css'],
})
export class MarketlistingComponent implements OnInit {
  totalElements: number = 0;
  mktListings: MarketListing[] = [];
  loading: boolean;
  currentSector: string;

  constructor(
    private mktLstngService: MarketlistingService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getMarketListings({ page: '0', size: '10' });
  }

  private getMarketListings(request) {
    this.loading = true;
    this.currentSector = this.route.snapshot.paramMap.get('id');
    this.mktLstngService
      .listMarketListings(request, this.currentSector)
      .subscribe(
        (data) => {
          this.mktListings = data['content'];
          this.totalElements = data['totalElements'];
          this.loading = false;
        },
        (error) => {
          this.loading = false;
        }
      );
  }

  nextPage(event: PageEvent) {
    const request = {};
    request['page'] = event.pageIndex.toString();
    request['size'] = event.pageSize.toString();
    this.getMarketListings(request);
  }
}
