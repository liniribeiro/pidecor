import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from '../../security/auth.guard';
import { MonitoringReportComponent } from './monitoring-report.component';

const providersRoutes: Routes = [
    {
        path: 'monitoring-report', component: MonitoringReportComponent,  canActivate: [AuthGuard],  children: [
        
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(providersRoutes)],
    exports: [RouterModule]
})
export class MonitoringReportRoutingModule { }