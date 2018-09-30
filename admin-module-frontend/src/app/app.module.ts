import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { StorageServiceModule } from 'angular-webstorage-service';
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { HttpClientModule, HTTP_INTERCEPTORS } from '../../node_modules/@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { AdvertisingComponent } from './components/modules/advertising/advertising.component';
import { DashboardComponent } from './components/modules/dashboard/dashboard.component';
import { DashboardService } from './components/modules/dashboard/dashboard.service';
import { MonitoringReportModule } from './components/modules/monitoring-report/monitoring-report.module';
import { ProvidersModule } from './components/modules/providers/providers.module';
import { ReturnandlossComponent } from './components/modules/returnandloss/returnandloss.component';
import { SacComponent } from './components/modules/sac/sac.component';
import { UsersModule } from './components/modules/users/users.module';
import { AuthGuard } from './components/security/auth.guard';
import { AuthInterceptor } from './components/security/auth.interceptor';
import { LoginService } from './components/security/login.service';
import { LoginComponent } from './components/security/login/login.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { HomeComponent } from './components/shared/home/home.component';
import { MenuComponent } from './components/shared/menu/menu.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    DashboardComponent,
    AdvertisingComponent,
    SacComponent,
    ReturnandlossComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    StorageServiceModule,
    ToastModule.forRoot(),
    MonitoringReportModule,
    ProvidersModule,
    UsersModule,
    AppRoutingModule
  ],
  providers: [AuthGuard, LoginService, DashboardService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }