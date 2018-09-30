import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersStatisticsComponent } from './users-statistics.component';

describe('UsersStatisticsComponent', () => {
  let component: UsersStatisticsComponent;
  let fixture: ComponentFixture<UsersStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
