import { Component, OnDestroy, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastsManager } from 'ng2-toastr';
import { Subscription } from 'rxjs/Subscription';
import { Provider } from '../provider.model';
import { ProvidersService } from '../providers.service';
import { ResponseApi } from './../../../../model/response-api.model';

@Component({
  selector: 'app-providers-detail',
  templateUrl: './providers-detail.component.html',
  styleUrls: ['./providers-detail.component.css']
})
export class ProvidersDetailComponent implements OnInit, OnDestroy  {

  @ViewChild("form")
  form: NgForm;

  currentProvider = new Provider (null, null,  null, null, null, null, null);
  inscricao: Subscription;

  constructor(private route: ActivatedRoute,
    private providerService: ProvidersService,
    private router: Router,
    public toastr: ToastsManager, vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr)
  }

  ngOnInit() {
    this.inscricao = this.route.params.subscribe(
      (params: any) => {
        let id = params['id'];
        if (id != undefined) {
          this.findById(id);
        }
      }
    );
  }

  findById(id: string) {
    console.log('id --> ', id);
    this.providerService.findById(id).subscribe((responseApi: ResponseApi) => {
      this.currentProvider = responseApi.data;
       //Toast Sucesso
    }, err => {
        //Toast Error
    });
  }

  disableProvider(){
    this.providerService.disableProvider(this.currentProvider.id).subscribe((responseApi: ResponseApi) => {
      this.router.navigate(['/providers/list']);
      //Toast Sucesso
    }, err => {
    //Toast Error
    });
  }

  ngOnDestroy(){
    this.inscricao.unsubscribe();
  }

}
