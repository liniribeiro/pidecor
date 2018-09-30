import { Provider } from '../provider.model';
import { Component, OnInit, ViewContainerRef} from '@angular/core';
import { ProvidersService } from '../providers.service';
import { ResponseApi } from '../../../../model/response-api.model';
import { ToastsManager } from '../../../../../../node_modules/ng2-toastr';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-providers-list',
  templateUrl: './providers-list.component.html',
  styleUrls: ['./providers-list.component.css']
})
export class ProvidersListComponent implements OnInit {

  public listProvider: Provider[];

  constructor(private providerService: ProvidersService,
    private router: Router,
    private route: ActivatedRoute,
    public toastr: ToastsManager, vcr: ViewContainerRef) { 
      this.toastr.setRootViewContainerRef(vcr)
    }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.providerService.findMyProviders().subscribe((responseApi: ResponseApi) => {
      this.listProvider = responseApi.data;
    }, err => {
      this.showError();
    });
  }

  showSuccess() {
    this.toastr.success('You are awesome!', 'Success!');
  }

  showError() {
    this.toastr.error('This is not good!', 'Oops!');
  }

  detailProvider(id : string){
    this.router.navigate(['/providers', id]);
  }
}
