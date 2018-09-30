import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LOCALHOST } from '../../../services/consts.api';
import { TableDTO } from './model/tableDTO.model';

@Injectable()
export class MonitoringReportService {

  constructor(private http: HttpClient) { }

  getTableInformations(tableDTO: TableDTO) {
    return this.http.post(`${LOCALHOST}/api/monitoring-report/findData`, tableDTO);
  }
}
