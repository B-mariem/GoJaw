import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogDesComponent } from './dialog-des.component';

describe('DialogDesComponent', () => {
  let component: DialogDesComponent;
  let fixture: ComponentFixture<DialogDesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogDesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogDesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
