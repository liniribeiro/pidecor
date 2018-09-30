
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpEvent, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs/Observable";


export class AuthInterceptor implements HttpInterceptor {

    token: string;
    constructor() {        
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>  {
        let authRequest : any;
        this.token = sessionStorage.getItem('id_token');
        if(this.token != null){
            authRequest = req.clone({
                setHeaders: {
                    'Authorization' : this.token
                }
            });
            return next.handle(authRequest);
        } else {
            return next.handle(req);
        }
    }   
}