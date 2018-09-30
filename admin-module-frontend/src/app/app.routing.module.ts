import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { AdvertisingComponent } from './components/modules/advertising/advertising.component';
import { DashboardComponent } from './components/modules/dashboard/dashboard.component';
import { ReturnandlossComponent } from './components/modules/returnandloss/returnandloss.component';
import { SacComponent } from './components/modules/sac/sac.component';
import { UsersComponent } from './components/modules/users/users.component';
import { AuthGuard } from './components/security/auth.guard';
import { LoginComponent } from './components/security/login/login.component';
import { HomeComponent } from './components/shared/home/home.component';




export const appRoutes = [
    { path: 'xanana', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'admin/login', component: LoginComponent },
    { path: 'users', component: UsersComponent, canActivate: [AuthGuard] },
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'returnandloss', component: ReturnandlossComponent, canActivate: [AuthGuard] },
    { path: 'sac', component: SacComponent, canActivate: [AuthGuard] },
    { path: 'advirtising', component: AdvertisingComponent, canActivate: [AuthGuard] }
]


@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }