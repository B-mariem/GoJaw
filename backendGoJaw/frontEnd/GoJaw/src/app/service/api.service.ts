import { Injectable } from '@angular/core';

import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DialogDesComponent } from '../des/dialog-des/dialog-des.component';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  adminpoint: string = 'http://localhost:4000/admin';

  headers = new HttpHeaders().set('Content-Type', 'application/json');
  currentAdmin = {};
  constructor(private http: HttpClient, public router: Router) { }
 
   //////////////////////////////////////////////////////
  //admin
  /////////////////////////////////////////////////////
   // Sign-up
   signUp(admin): Observable<any> {
    let api = `${this.adminpoint}/register-admin`;
    return this.http.post(api, admin)
      .pipe(
        catchError(this.handleError)
      )
  }

  // Sign-in
  signIn(admin) {
    return this.http.post<any>(`${this.adminpoint}/signin`, admin)
      .subscribe((res: any) => {
        localStorage.setItem('access_token', res.token)
        this.getAdminProfile(res._id).subscribe((res) => {
          this.currentAdmin = res;
          this.router.navigate(['admin-profile/' + res.msg._id]);
        })
      })
  }

  getToken() {
    return localStorage.getItem('access_token');
  }

  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('access_token');
    return (authToken !== null) ? true : false;
  }

  doLogout() {
    let removeToken = localStorage.removeItem('access_token');
    if (removeToken == null) {
      this.router.navigate(['admin']);
    }
  }

  // User profile
  getAdminProfile(id): Observable<any> {
    let api = `${this.adminpoint}/admin-profile/${id}`;
    return this.http.get(api, { headers: this.headers }).pipe(
      map((res: Response) => {
        return res || {}
      }),
      catchError(this.handleError)
    )
  }

   // Error 
   handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }
 

 



 

 
}
