import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavGouvComponent } from './nav-gouv.component';

describe('NavGouvComponent', () => {
  let component: NavGouvComponent;
  let fixture: ComponentFixture<NavGouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavGouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavGouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
