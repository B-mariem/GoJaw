import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDesComponent } from './create-des.component';

describe('CreateDesComponent', () => {
  let component: CreateDesComponent;
  let fixture: ComponentFixture<CreateDesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateDesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
