import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { WatchlistService } from 'src/app/services/watchlist.service';
import { Watchlist } from 'src/app/services/watchlist';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css'],
})
export class WatchlistComponent implements OnInit {
  allWatchlists: Watchlist[] = [];
  processValidation = false;
  statusCode: number;

  watchListForm = new FormGroup({
    name: new FormControl('', Validators.required),
  });
  constructor(private watchlistService: WatchlistService) {}

  ngOnInit(): void {
    this.getAllWatchlists();
  }

  getAllWatchlists() {
    this.watchlistService.getAllWatchlists().subscribe(
      (data) => (this.allWatchlists = data),
      (errorCode) => (this.statusCode = errorCode)
    );
  }
  onSubmit() {
    this.processValidation = true;
    if (this.watchListForm.invalid) {
      return; //Validation failed, exit from method.
    }
    //Form is valid, now perform create or update
    this.statusCode = null;

    let watchList = {} as Watchlist;
    watchList.name = this.watchListForm.get('name').value.trim();
    this.watchlistService.createWatchlist(watchList).subscribe(
      (data) => {
        this.allWatchlists.push(data);
        this.watchListForm.reset();
        this.processValidation = false;
      },
      (errorCode) => (this.statusCode = errorCode)
    );
  }
}
