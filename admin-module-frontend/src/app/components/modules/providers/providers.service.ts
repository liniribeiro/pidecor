import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LOCALHOST } from '../../../services/consts.api';


@Injectable()
export class ProvidersService {

  constructor(private http: HttpClient) { }

  delete(id: string) {
    return this.http.delete(`${LOCALHOST}/api/provider/${id}`);
  }
  
  findAllProviders() {
    return this.http.get(`${LOCALHOST}/api/provider/all`);
  }

  findMyProviders() {
    return this.http.get(`${LOCALHOST}/api/provider/my`);
  }

  activateProvider(provider: any) {
    return this.http.post(`${LOCALHOST}/api/provider/activate`, provider);
  }

  disableProvider(id: string) {
    return this.http.delete(`${LOCALHOST}/api/provider/disable/${id}`);
  }
  
  findById(id : string) {
    return this.http.get(`${LOCALHOST}/api/provider/find/${id}`);
  }

  getTotalProviders() {
    return this.http.get(`${LOCALHOST}/api/provider/totalProviders`);
  }
}
