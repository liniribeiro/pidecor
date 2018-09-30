
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from "@angular/core";
import { FormsModule } from '@angular/forms';
import { MonitoringReportComponent } from './monitoring-report.component';
import { MonitoringReportRoutingModule } from './monitoring-report.routing.module';
import { MonitoringReportService } from './monitoring-report.service';


@NgModule({
    declarations: [
        MonitoringReportComponent
    ],
    imports: [
        CommonModule,
        HttpClientModule,
        MonitoringReportRoutingModule,
        FormsModule
    ],
    providers: [MonitoringReportService,
    ],
    bootstrap: []
})
export class MonitoringReportModule { }