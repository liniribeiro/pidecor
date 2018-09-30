import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewProvidersComponent } from './new-providers.component';

describe('NewProvidersComponent', () => {
  let component: NewProvidersComponent;
  let fixture: ComponentFixture<NewProvidersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewProvidersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewProvidersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
