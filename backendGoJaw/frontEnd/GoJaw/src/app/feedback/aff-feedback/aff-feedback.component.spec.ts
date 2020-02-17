import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffFeedbackComponent } from './aff-feedback.component';

describe('AffFeedbackComponent', () => {
  let component: AffFeedbackComponent;
  let fixture: ComponentFixture<AffFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
