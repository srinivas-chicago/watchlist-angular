import { Component, OnInit } from '@angular/core';
import { Watchlist, WatchlistItem } from 'src/app/services/watchlist';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { WatchlistService } from 'src/app/services/watchlist.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-watchlistdetail',
  templateUrl: './watchlistdetail.component.html',
  styleUrls: ['./watchlistdetail.component.css'],
})
export class WatchlistdetailComponent implements OnInit {
  watchlistItems: WatchlistItem[] = [];
  processValidation = false;
  statusCode: number;
  watchList: string;
  watchListItemForm = new FormGroup({
    symbol: new FormControl('', Validators.required),
    note: new FormControl(''),
  });

  constructor(
    private watchlistService: WatchlistService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.watchList = params['name'];
      //console.log(userId);
    });

    this.getWatchlistItems();
  }

  getWatchlistItems() {
    this.watchlistService.getWatchlistItems(this.router.url).subscribe(
      (data) => (this.watchlistItems = data),
      (errorCode) => (this.statusCode = errorCode)
    );
  }

  onSubmit() {
    this.processValidation = true;
    if (this.watchListItemForm.invalid) {
      return; //Validation failed, exit from method.
    }
    //Form is valid, now perform create or update
    this.statusCode = null;

    let watchListItem = {} as WatchlistItem;
    watchListItem.symbol = this.watchListItemForm.get('symbol').value.trim();
    watchListItem.note = this.watchListItemForm.get('note').value.trim();

    this.watchlistService
      .createWatchlistItem(watchListItem, this.router.url)
      .subscribe(
        (data) => {
          this.watchlistItems.push(data);
          this.watchListItemForm.reset();
          this.processValidation = false;
        },
        (errorCode) => (this.statusCode = errorCode)
      );
  }
}
