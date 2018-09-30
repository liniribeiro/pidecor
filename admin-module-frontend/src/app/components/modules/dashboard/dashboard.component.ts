import { ReceivedValueByMonthDTO } from './model/dashboard-valuereceived-bymonth.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as Chartist from 'chartist';
import { ResponseApi } from '../../../model/response-api.model';
import { DashboardService } from './dashboard.service';

import { ClientOrderMonthsEvolutionDTO } from './model/dashboard-ordermnthsevolution.model';
import { ClientOrderByStatusDTO } from './model/dashboard-orderbystatus.model';

export class ChartorderStatusProfiles {
  title: string
  class: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  totalClients: string = '0';
  receivedValueCurentMonth: string = '0';
  clientMonthEvolutionList: ClientOrderMonthsEvolutionDTO[] = [];
  orderByStatusList: ClientOrderByStatusDTO[] = [];
  receivedValueByMonth: ReceivedValueByMonthDTO[] = [];
  statusChartLegend: ChartorderStatusProfiles[] = [];

  constructor(
    private dashboardService: DashboardService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.getTotalOfClients();
    this.getReceivedValueCurentMonth();
    this.getOrderEvolutionValue();
    this.getAllByStatus();
    this.getReceivedValueByMonth();
  }

  getTotalOfClients() {
    this.dashboardService.getTotalClients().subscribe((responseApi: ResponseApi) => {
      this.totalClients = responseApi.data;
    })
  }

  getReceivedValueCurentMonth() {
    this.dashboardService.getReceivedValueCurentMonth().subscribe((responseApi: ResponseApi) => {
      this.receivedValueCurentMonth = responseApi.data;
    })
  }

  getOrderEvolutionValue() {
    this.dashboardService.getOrderEvolutionValue().subscribe((responseApi: ResponseApi) => {
      this.clientMonthEvolutionList = responseApi.data;

      var options = {
        low: -1, high: 15 ,
        seriesBarDistance: 10,
        axisX: {
            showGrid: false
        },
        height: "245px"
    };

 
      let months: string[] = this.clientMonthEvolutionList.map(evolution => { return evolution.month; });
      let ordersAmmount: number[] = this.clientMonthEvolutionList.map(evolution => { return evolution.ordersAmmount; });

      var data = {
        labels: months,
        series: [ordersAmmount]
      };

      new Chartist.Line('#chartActivity',
        data, options)
    }, err => {

    });
  }

  getAllByStatus() {
    this.dashboardService.getAllByStatus().subscribe((responseApi: ResponseApi) => {
      this.orderByStatusList = responseApi.data;
      this.createChartPieLegends();

      var options = {
        labelInterpolationFnc: function(value) {
          return value[0]
        }
      };

      var responsiveOptions = [
        ['screen and (min-width: 640px)', {
          chartPadding: 10,
          labelOffset: 100,
         // labelDirection: 'explode',
          labelInterpolationFnc: function(value) {
            return value;
          }
        }],
        ['screen and (min-width: 1024px)', {
          labelOffset: 20,
          chartPadding: 20
        }]
      ];

      new Chartist.Pie('#chartorderStatus', {
        labels: this.orderByStatusList.map(profile => { return profile.amount; }),
        series: this.orderByStatusList.map(profile => { return profile.amount; })
      }, options, responsiveOptions);
    }, err => {

    });
  }

  createChartPieLegends() {
    for (var order of this.orderByStatusList) {
      var chartPies: ChartorderStatusProfiles = {
        title: order.status,
        class: this.getChartPieClasses(this.orderByStatusList.indexOf(order))
      }
      this.statusChartLegend.push(chartPies);
    }
  }

  getChartPieClasses(index: number): string {

    if (index == 0) {
      return 'fa fa-circle text-slice-pi-a';
    }
    if (index == 1) {
      return 'fa fa-circle text-slice-pi-b';
    }
    if (index == 2) {
      return 'fa fa-circle text-slice-pi-c';
    }
    if (index == 3) {
      return 'fa fa-circle text-slice-pi-d';
    }
    if (index == 4) {
      return 'fa fa-circle text-slice-pi-e';
    }
  }

  getReceivedValueByMonth() {
    this.dashboardService.getReceivedValueByMonth().subscribe((responseApi: ResponseApi) => {
      this.receivedValueByMonth = responseApi.data;

      var optionsSales = {
        low: -1, high: 150,
        showArea: true,
        height: "245px",
        axisX: {
          showGrid: false,
        },
        lineSmooth: Chartist.Interpolation.simple({
          divisor: 3
        }),
        showLine: true,
        showPoint: true,
      };

      let months: string[] = this.receivedValueByMonth.map(evolution => { return evolution.month; });
      let ordersAmmount: number[] = this.receivedValueByMonth.map(evolution => { return evolution.receivedValue; });

      var data = {
        labels: months,
        series: [ordersAmmount]
      };

      new Chartist.Line('#getReceivedValueByMonth', data, optionsSales);


    }, err => {

    });
  }
}
