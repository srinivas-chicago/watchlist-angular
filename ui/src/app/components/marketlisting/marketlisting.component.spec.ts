import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketlistingComponent } from './marketlisting.component';

describe('MarketlistingComponent', () => {
  let component: MarketlistingComponent;
  let fixture: ComponentFixture<MarketlistingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketlistingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketlistingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
