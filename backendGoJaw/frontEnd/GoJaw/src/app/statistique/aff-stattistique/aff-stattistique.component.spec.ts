import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffStattistiqueComponent } from './aff-stattistique.component';

describe('AffStattistiqueComponent', () => {
  let component: AffStattistiqueComponent;
  let fixture: ComponentFixture<AffStattistiqueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffStattistiqueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffStattistiqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
