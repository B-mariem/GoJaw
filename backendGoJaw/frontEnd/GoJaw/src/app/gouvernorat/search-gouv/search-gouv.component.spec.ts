import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchGouvComponent } from './search-gouv.component';

describe('SearchGouvComponent', () => {
  let component: SearchGouvComponent;
  let fixture: ComponentFixture<SearchGouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchGouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchGouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
