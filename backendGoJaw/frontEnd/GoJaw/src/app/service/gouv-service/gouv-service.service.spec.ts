import { TestBed } from '@angular/core/testing';

import { GouvServiceService } from './gouv-service.service';

describe('GouvServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GouvServiceService = TestBed.get(GouvServiceService);
    expect(service).toBeTruthy();
  });
});
