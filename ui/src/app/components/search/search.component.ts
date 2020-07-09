import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SearchService } from 'src/app/services/search.service';
import { MarketListing } from 'src/app/services/marketlisting';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  searchFormGroup: FormGroup;
  mktListings: MarketListing[] = [];
  sectors: string[] = [
    'All',
    'Technology',
    'Basic Industries',
    'Capital Goods',
    'Consumer Non-Durables',
    'Health Care',
    'Consumer Services',
    'Finance',
    'Public Utilities',
    'Energy',
    'Miscellaneous',
    'Transportation',
    'Consumer Durables',
    'Not Available',
  ];
  constructor(
    private formBuilder: FormBuilder,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.formBuilder.group({
      sector: [''],
      marketcapmin: [''],
      marketcapmax: [''],
      ipoyearmin: [''],
      ipoyearmax: [''],
      lastsalemin: [''],
      lastsalemax: [''],
    });
  }

  onSubmit() {
    this.searchService
      .searchMktListings(this.searchFormGroup.value)
      .subscribe((data) => {
        this.mktListings = data['content'];
      });
  }
}
