import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from "@angular/core";
import { FormsModule } from '@angular/forms';
import { NewUserComponent } from './new-user/new-user.component';
import { UserService } from './user.service';
import { UsersListComponent } from './users-list/users-list.component';
import { UsersStatisticsComponent } from './users-statistics/users-statistics.component';
import { UsersComponent } from './users.component';
import { UsersRoutingModule } from './users.routing.module';


@NgModule({
    declarations: [
        UsersComponent,
        NewUserComponent,
        UsersListComponent,
        UsersStatisticsComponent
    ],
    imports: [
        CommonModule,
        HttpClientModule,
        UsersRoutingModule,
        FormsModule
    ],
    providers: [UserService],
    bootstrap: []
})
export class UsersModule { }