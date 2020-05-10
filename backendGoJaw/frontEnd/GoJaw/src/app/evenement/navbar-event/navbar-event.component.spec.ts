import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarEventComponent } from './navbar-event.component';

describe('NavbarEventComponent', () => {
  let component: NavbarEventComponent;
  let fixture: ComponentFixture<NavbarEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
