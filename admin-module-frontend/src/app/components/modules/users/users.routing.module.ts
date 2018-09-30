import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from '../../security/auth.guard';
import { NewUserComponent } from './new-user/new-user.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UsersComponent } from "./users.component";

const usersRoutes: Routes = [
    {
        path: 'users', component: UsersComponent,  canActivate: [AuthGuard],  children: [
            { path: 'new', component: NewUserComponent,  canActivate: [AuthGuard] },
            { path: 'list', component: UsersListComponent, canActivate: [AuthGuard]}
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(usersRoutes)],
    exports: [RouterModule]
})
export class UsersRoutingModule { }