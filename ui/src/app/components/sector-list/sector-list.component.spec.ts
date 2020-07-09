import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SectorListComponent } from './sector-list.component';

describe('SectorListComponent', () => {
  let component: SectorListComponent;
  let fixture: ComponentFixture<SectorListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SectorListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SectorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
