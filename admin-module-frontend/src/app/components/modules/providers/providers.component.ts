import { ProvidersService } from './providers.service';
import { Component, OnInit } from '@angular/core';
import { ResponseApi } from '../../../model/response-api.model';

@Component({
  selector: 'app-providers',
  templateUrl: './providers.component.html',
  styleUrls: ['./providers.component.css']
})
export class ProvidersComponent implements OnInit {

  providers: string = '0';
  constructor(private providerService: ProvidersService) { }

  ngOnInit() {
    this.getTotalOfProviders();
  }

  getTotalOfProviders(){
    this.providerService.getTotalProviders().subscribe((responseApi: ResponseApi) => {
      this.providers = responseApi.data;
    })
  }

}
