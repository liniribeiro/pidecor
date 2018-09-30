
import { Component, OnInit, ViewContainerRef, ViewChild } from '@angular/core';
import { ResponseApi } from '../../../../model/response-api.model';
import { ToastsManager } from 'ng2-toastr';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProvidersService } from '../providers.service';
import { Provider } from '../provider.model';

@Component({
  selector: 'app-new-providers',
  templateUrl: './new-providers.component.html',
  styleUrls: ['./new-providers.component.css']
})
export class NewProvidersComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;


  allProviders: Provider[];
  currentProvider = new Provider(null, null, null, null, null, null, null);
  constructor(
    private providerService: ProvidersService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastsManager, vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr)
  }

  ngOnInit() {
    this.findAllProviders();
  }

  findAllProviders() {
    this.providerService.findAllProviders().subscribe((responseApi: ResponseApi) => {
      this.allProviders = responseApi.data;
    }, err => {
      this.showError();
    });
  }

  showSuccess() {
   // this.toastr.success('Operação realizada com sucesso!', 'Sucesso!');
  }

  showError() {
   // this.toastr.error('This is not good!', 'Oops!');
  }

  saveProvider() {
      this.providerService.activateProvider(this.currentProvider).subscribe((responseApi: ResponseApi) => {
        this.showSuccess();
        this.router.navigate(['/providers/list']);
      }, err => {
        this.showError();
      })
  }

  validateProvider(provider: Provider): boolean {
    return provider.providersAPIId != null;
  }

  clear(){
    this.currentProvider = new Provider(null, null, null, null, null, null, null);
  }
}
