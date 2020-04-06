import { Injectable } from '@angular/core';

import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { admin } from '../Models/admin';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  adminpoint: string = 'http://localhost:4000/admin';
  gouvpoint: string = 'http://localhost:4000/gouv';
  villepoint: string = 'http://localhost:4000/ville';
  destinationpoint: string = 'http://localhost:4000/destination';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  currentAdmin = {};
  constructor(private http: HttpClient, public router: Router) { }

   //////////////////////////////////////////////////////
  //admin
  /////////////////////////////////////////////////////
   // Sign-up
   signUp(admin: admin): Observable<any> {
    let api = `${this.adminpoint}/register-admin`;
    return this.http.post(api, admin)
      .pipe(
        catchError(this.handleError)
      )
  }

  // Sign-in
  signIn(admin: admin) {
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
  /*--------------------gouvernorat--------------------------*/
  createGouv(obejct): Observable<any> {
    let api = `${this.gouvpoint}/create`;
    return this.http.post(api, obejct)
      .pipe(
        catchError(this.handleError)
      )

  }

  getGouvs() {
    return this.http.get(`${this.gouvpoint}/all`);
  }
  /*----------------------ville-------------------------*/
  createVille(obejct): Observable<any> {
    let api = `${this.villepoint}/create`;
    return this.http.post(api, obejct)
      .pipe(
        catchError(this.handleError)
      )

  }
  getVilles() {
    return this.http.get(`${this.villepoint}`);
  }
    /*----------------------Destination-------------------------*/
  createDestination(obejct): Observable<any> {
    let api = `${this. destinationpoint}/create`;
    return this.http.post(api, obejct)
      .pipe(
        catchError(this.handleError)
      )

  }
  getDestinations() {
    return this.http.get(`${this.destinationpoint}`);
  }
  addPosition(id, data): Observable<any> {
    let url = `${this.destinationpoint}/addPosition/${id}`;
    return this.http.post(url, data, { headers: this.headers }).pipe(
      catchError(this.handleError)
    )
  }
  
}
