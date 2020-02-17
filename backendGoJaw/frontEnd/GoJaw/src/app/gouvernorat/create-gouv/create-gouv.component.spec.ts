import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateGouvComponent } from './create-gouv.component';

describe('CreateGouvComponent', () => {
  let component: CreateGouvComponent;
  let fixture: ComponentFixture<CreateGouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateGouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
