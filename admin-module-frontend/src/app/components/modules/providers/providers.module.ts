import { RouterModule } from '@angular/router';
import { ProvidersService } from './providers.service';
import { ProvidersComponent } from './providers.component';
import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { NewProvidersComponent } from './new-providers/new-providers.component';
import { ProvidersDetailComponent } from './providers-detail/providers-detail.component';
import { ProvidersListComponent } from './providers-list/providers-list.component';
import { ProvidersRoutingModule } from './providers.routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
    declarations: [
        ProvidersComponent,
        NewProvidersComponent,
        ProvidersDetailComponent,
        ProvidersListComponent
    ],
    imports: [
        CommonModule,
        HttpClientModule,
        ProvidersRoutingModule,
        FormsModule
    ],
    providers: [ProvidersService],
    bootstrap: []
})
export class ProvidersModule { }