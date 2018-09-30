import { Component, OnInit } from '@angular/core';
import Chartist from 'chartist';
import { ResponseApi } from '../../../../model/response-api.model';
import { UserService } from '../user.service';

export interface UserProfiles {
  title: string
  attribute: string;
  class: string;
}

export const PROFILES: UserProfiles[] = [
  { title: 'Administrador do sistema', attribute: 'ROLE_ADMIN', class: 'fa fa-circle text-info'},
  { title: 'Cliente', attribute: 'ROLE_CUSTOMER', class: 'fa fa-circle text-danger' },
  { title: 'Técnico de atendimento ao cliente', attribute: 'ROLE_HELP_DESK',  class: 'fa fa-circle text-warning' },
  { title: 'Analista de Marketing', attribute: 'ROLE_MARKETING_ANALYST',  class: 'fa fa-circle text-success' },
  { title: 'Analista comercial', attribute: 'ROLE_COMERCIAL_ANALYST',  class: 'fa fa-circle text-primary' }]

@Component({
  selector: 'app-users-statistics',
  templateUrl: './users-statistics.component.html',
  styleUrls: ['./users-statistics.component.css']
})
export class UsersStatisticsComponent implements OnInit {

  public profileItems: any[];
  public listProfiles = [];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.userService.getTotalByProfile().subscribe((responseApi: ResponseApi) => {
      this.listProfiles = responseApi.data;
      
      // Legenda
      this.profileItems = this.listProfiles.map(profile => {
        return PROFILES.find(userProfile => { 
          return userProfile.attribute == profile.profileName 
        });
      });

      // Series
      new Chartist.Pie('#chartPreferences', {
        labels: this.listProfiles.map(profile => { return profile.users; }),
        series: this.listProfiles.map(profile => { return profile.users; })
      });
    }, err => {

    });
  }
}
