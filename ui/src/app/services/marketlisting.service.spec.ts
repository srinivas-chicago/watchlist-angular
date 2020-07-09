import { TestBed } from '@angular/core/testing';

import { MarketlistingService } from './marketlisting.service';

describe('MarketlistingService', () => {
  let service: MarketlistingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarketlistingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
