import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DesNavbarComponent } from './des-navbar.component';

describe('DesNavbarComponent', () => {
  let component: DesNavbarComponent;
  let fixture: ComponentFixture<DesNavbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DesNavbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DesNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
