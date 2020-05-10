import { TestBed } from '@angular/core/testing';

import { DestinationServiceService } from './destination-service.service';

describe('DestinationServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DestinationServiceService = TestBed.get(DestinationServiceService);
    expect(service).toBeTruthy();
  });
});
