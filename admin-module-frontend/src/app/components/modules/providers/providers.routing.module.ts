import { NewProvidersComponent } from './new-providers/new-providers.component';
import { ProvidersDetailComponent } from "./providers-detail/providers-detail.component";
import { ProvidersComponent } from "./providers.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { ProvidersListComponent } from './providers-list/providers-list.component';
import { AuthGuard } from '../../security/auth.guard';

const providersRoutes: Routes = [
    {
        path: 'providers', component: ProvidersComponent,  canActivate: [AuthGuard],  children: [
            { path: 'new', component: NewProvidersComponent,  canActivate: [AuthGuard] },
            { path: 'list', component: ProvidersListComponent, canActivate: [AuthGuard]},
            { path: ':id', component: ProvidersDetailComponent,  canActivate: [AuthGuard] }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(providersRoutes)],
    exports: [RouterModule]
})
export class ProvidersRoutingModule { }