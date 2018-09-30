import { Component, OnInit } from '@angular/core';


export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: 'dashboard', title: 'Informações Gerênciais', icon: 'ti-pulse', class: 'pi-pink' },
  { path: 'users', title: 'Usuários', icon: 'ti-user', class: 'pi-pink' },
  { path: 'providers', title: 'Fornecedores', icon: 'ti-id-badge', class: 'pi-pink' },
  { path: 'advirtising', title: 'Propagandas', icon: 'ti-image', class: 'pi-pink' },
  { path: 'sac', title: 'SAC', icon: 'ti-comment', class: 'pi-pink' },
  { path: 'returnandloss', title: 'Devolução e extravio', icon: 'ti-truck', class: 'pi-pink' },
  { path: 'monitoring-report', title: 'Relatórios de Acompanhamento', icon: 'ti-truck', class: 'pi-pink' }
];


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent implements OnInit {

  public menuItems: any[];
  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

}
