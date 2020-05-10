import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogDeleteGouvComponent } from './dialog-delete-gouv.component';

describe('DialogDeleteGouvComponent', () => {
  let component: DialogDeleteGouvComponent;
  let fixture: ComponentFixture<DialogDeleteGouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogDeleteGouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogDeleteGouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
