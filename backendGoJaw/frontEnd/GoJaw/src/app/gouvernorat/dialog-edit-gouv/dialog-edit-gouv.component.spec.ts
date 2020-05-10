import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditGouvComponent } from './dialog-edit-gouv.component';

describe('DialogEditGouvComponent', () => {
  let component: DialogEditGouvComponent;
  let fixture: ComponentFixture<DialogEditGouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEditGouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditGouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
