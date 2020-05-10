import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDesComponent } from './search-des.component';

describe('SearchDesComponent', () => {
  let component: SearchDesComponent;
  let fixture: ComponentFixture<SearchDesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
