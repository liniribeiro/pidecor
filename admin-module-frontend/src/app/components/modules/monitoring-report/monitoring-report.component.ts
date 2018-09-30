import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseApi } from '../../../model/response-api.model';
import { customerHeaderConfig, customerOrderHeaderConfig, providersHeaderConfig, itemHeaderConfig, userHeaderConfig } from './model/consts-table-headers.api';
import { TableDTO } from './model/tableDTO.model';
import { MonitoringReportService } from './monitoring-report.service';
import { DatePipe } from '@angular/common';

declare interface Table {
  type: string;
  name: string;
}

declare interface TableData {
  headerRow: string[];
  dataRows: string[][];
}

declare interface HeaderConfig {
  title: string;
  attribute: string;
}

@Component({
  selector: 'app-monitoring-report',
  templateUrl: './monitoring-report.component.html',
  styleUrls: ['./monitoring-report.component.css']
})
export class MonitoringReportComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  datePipe = new DatePipe('en-US');
  currentDate = this.datePipe.transform(new Date(), 'yyyy-MM');;
  startDate = this.currentDate
  endDate: string = this.currentDate;
  tableResponse: any[] = [];
  selectedTable: Table = null;
  tableData1: TableData;
  headerConfig: HeaderConfig[];

  allTables: Table[] = [
    { type: 'PROVIDERS', name: 'Fornecedores' },
    { type: 'CUSTOMER', name: 'Clientes' },
    { type: 'CUSTOMER_ORDER', name: 'Pedidos' },
    { type: 'USERS', name: 'Usuários' },
    { type: 'ITEMS', name: 'Items' }
  ];


  constructor(
    private monitoringReportService: MonitoringReportService,
    private router: Router,
    private route: ActivatedRoute, ) { }


  ngOnInit() {
  }

  callDataTables() {
    let tableDto = new TableDTO(this.selectedTable.type, this.startDate, this.endDate)
    console.log('id --> ', tableDto);
    this.monitoringReportService.getTableInformations(tableDto).subscribe((responseApi: ResponseApi) => {
      this.tableResponse = responseApi.data;
      //Toast Sucesso
    }, err => {
      //Toast Error
    });
  }


  getTableOfData(): TableData {
    this.getTableHeaders();
    this.tableData1 = {

      headerRow: this.headerConfig.map((config) => config.title),
      dataRows: this.tableResponse.map((user) => {
        return this.headerConfig.map((config) => user[config.attribute])
      })
    };
    return this.tableData1;
  }

  getTableHeaders() {
    if (this.selectedTable.type == 'PROVIDERS') {
      this.headerConfig = providersHeaderConfig;
    }

    if (this.selectedTable.type == 'CUSTOMER_ORDER') {
      this.headerConfig = customerOrderHeaderConfig;
    }

    if (this.selectedTable.type == 'CUSTOMER') {
      this.headerConfig = customerHeaderConfig;
    }

    if (this.selectedTable.type == 'ITEMS') {
      this.headerConfig = itemHeaderConfig;
    }

    if (this.selectedTable.type == 'USERS') {
      this.headerConfig = userHeaderConfig;
    }
  }


  public returnPDF() {
    return xepOnline.Formatter.Format('content', { render: 'download', cssStyle: [{ orientation: 'landscape' }, { fontWeight: 'bold' }] });
  }

  clear() {
    this.selectedTable = null;
    this.startDate = null;
    this.endDate = null;
    this.router.navigate(['/monitoring-report']);
  }

  getShowReport(): boolean {
    return this.selectedTable!= null && this.startDate != null && this.endDate != null;
  }
}
