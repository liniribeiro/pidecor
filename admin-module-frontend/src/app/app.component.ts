import { Component, Inject, ViewContainerRef } from '@angular/core';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showTemplate: boolean = false;
  token: string;
  isMonitoringReport: string;


  ngOnInit() {
  }

  getShowTemplate(): boolean {
    this.token = sessionStorage.getItem('id_token');
    this.isMonitoringReport = sessionStorage.getItem('monitoring_report');
    return this.token != null || (this.isMonitoringReport != null && this.isMonitoringReport == 'true');
  }

  showContentWrapper() {
    return {
      'wrapper': this.showTemplate
    }
  }

  constructor(public toastr: ToastsManager, vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);
  }
  showSuccess() {
    this.toastr.success('You are awesome!', 'Success!');
  }

  showError() {
    this.toastr.error('This is not good!', 'Oops!');
  }

  showWarning() {
    this.toastr.warning('You are being warned.', 'Alert!');
  }

  showInfo() {
    this.toastr.info('Just some information for you.');
  }

  showCustom() {
    this.toastr.custom('<span style="color: red">Message in red.</span>', null, { enableHTML: true });
  }

}
