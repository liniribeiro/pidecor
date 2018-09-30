import { Component, OnInit, ViewChild, Renderer, ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { ROUTES } from '../menu/menu.component';
import { LoginService } from '../../security/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})


export class HeaderComponent implements OnInit {
  private listTitles: any[];
  location: Location;
  private nativeElement: Node;
  private toggleButton;
  private sidebarVisible: boolean;

  @ViewChild("app-header") button;

  constructor(location:Location, private renderer : Renderer, private element : ElementRef, private loginService : LoginService) {
      this.location = location;
      this.nativeElement = element.nativeElement;
      this.sidebarVisible = false;
  }

  ngOnInit(){
      this.listTitles = ROUTES.filter(listTitle => listTitle);
      var navbar : HTMLElement = this.element.nativeElement;
      this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];
  }

  getTitle(){
      var titlee = window.location.pathname;
      titlee = titlee.substring(1);
      for(var item = 0; item < this.listTitles.length; item++){
          if(this.listTitles[item].path === titlee){
              return this.listTitles[item].title;
          }
      }
      return 'PIDECOR';
  }
  
  sidebarToggle(){
      var toggleButton = this.toggleButton;
      var body = document.getElementsByTagName('body')[0];

      if(this.sidebarVisible == false){
          setTimeout(function(){
              toggleButton.classList.add('toggled');
          },500);
          body.classList.add('nav-open');
          this.sidebarVisible = true;
      } else {
          this.toggleButton.classList.remove('toggled');
          this.sidebarVisible = false;
          body.classList.remove('nav-open');
      }
  }

  public logout() {
    this.loginService.logout();
  };


}
