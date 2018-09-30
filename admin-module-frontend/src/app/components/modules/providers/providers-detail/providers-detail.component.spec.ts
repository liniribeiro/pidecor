import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvidersDetailComponent } from './providers-detail.component';

describe('ProvidersDetailComponent', () => {
  let component: ProvidersDetailComponent;
  let fixture: ComponentFixture<ProvidersDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvidersDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvidersDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
