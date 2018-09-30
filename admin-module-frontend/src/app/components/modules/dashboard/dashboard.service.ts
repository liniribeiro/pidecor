import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LOCALHOST } from '../../../services/consts.api';

@Injectable()
export class DashboardService {

  constructor(private http: HttpClient) { }


  getTotalClients() {
    return this.http.get(`${LOCALHOST}/api/customer/totalClients`);
  }

  getOrderEvolutionValue() {
    return this.http.get(`${LOCALHOST}/api/order/getOrderEvolutionValue`);
  }
  
  getAllByStatus() {
    return this.http.get(`${LOCALHOST}/api/order/allByStatus`);
  }
  
  getReceivedValueByMonth() {
    return this.http.get(`${LOCALHOST}/api/order/receivedValueByMonth`);
  }

  getReceivedValueCurentMonth() {
    return this.http.get(`${LOCALHOST}/api/order/receivedValueCurentMonth`);
  }

}
