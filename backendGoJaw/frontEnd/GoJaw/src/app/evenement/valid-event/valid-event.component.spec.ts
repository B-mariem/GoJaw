import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidEventComponent } from './valid-event.component';

describe('ValidEventComponent', () => {
  let component: ValidEventComponent;
  let fixture: ComponentFixture<ValidEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
