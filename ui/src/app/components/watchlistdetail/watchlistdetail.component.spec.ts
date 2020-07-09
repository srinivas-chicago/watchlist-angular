import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WatchlistdetailComponent } from './watchlistdetail.component';

describe('WatchlistdetailComponent', () => {
  let component: WatchlistdetailComponent;
  let fixture: ComponentFixture<WatchlistdetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WatchlistdetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WatchlistdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
