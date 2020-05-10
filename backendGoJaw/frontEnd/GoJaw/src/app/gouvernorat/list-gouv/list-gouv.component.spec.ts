import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListGouvComponent } from './list-gouv.component';

describe('ListGouvComponent', () => {
  let component: ListGouvComponent;
  let fixture: ComponentFixture<ListGouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListGouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListGouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
