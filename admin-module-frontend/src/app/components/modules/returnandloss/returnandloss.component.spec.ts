import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnandlossComponent } from './returnandloss.component';

describe('ReturnandlossComponent', () => {
  let component: ReturnandlossComponent;
  let fixture: ComponentFixture<ReturnandlossComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReturnandlossComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReturnandlossComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
