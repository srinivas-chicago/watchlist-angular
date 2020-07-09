import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sector-list',
  templateUrl: './sector-list.component.html',
  styleUrls: ['./sector-list.component.css'],
})
export class SectorListComponent implements OnInit {
  sectors: string[] = [
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
  constructor() {}

  ngOnInit(): void {}
}
