import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoringReportComponent } from './monitoring-report.component';

describe('MonitoringReportComponent', () => {
  let component: MonitoringReportComponent;
  let fixture: ComponentFixture<MonitoringReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonitoringReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitoringReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
