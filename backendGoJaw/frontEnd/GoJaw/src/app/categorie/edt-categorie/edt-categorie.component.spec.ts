import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EdtCategorieComponent } from './edt-categorie.component';

describe('EdtCategorieComponent', () => {
  let component: EdtCategorieComponent;
  let fixture: ComponentFixture<EdtCategorieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EdtCategorieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EdtCategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
