import { TestBed } from '@angular/core/testing';

import { CategorieServiceService } from './categorie-service.service';

describe('CategorieServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CategorieServiceService = TestBed.get(CategorieServiceService);
    expect(service).toBeTruthy();
  });
});
