import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GouvServiceService {
  gouvpoint: string = 'http://localhost:4000/gouv';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient, public router: Router) { }

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

SearchGouv(search): Observable<any> {
let url = `${this.gouvpoint}/search/${search}`;
return this.http.get(url, {headers: this.headers}).pipe(
  map((res: Response) => {
    return res || {}
  }),
  catchError(this.handleError)
)
}
getGouv(id): Observable<any> {
let url = `${this.gouvpoint}/read/${id}`;
return this.http.get(url, {headers: this.headers}).pipe(
  map((res: Response) => {
    return res || {}
  }),
  catchError(this.handleError)
)
}
// Update gouv
updateGouv(id, data): Observable<any> {
let url = `${this.gouvpoint}/update/${id}`;
return this.http.put(url, data, { headers: this.headers }).pipe(
  catchError(this.handleError)
)
}

// Delete Gouv
deleteGouv(gouv): Observable<any> {
let url = `${this.gouvpoint}/delete/${gouv}`;
return this.http.delete(url, { headers: this.headers }).pipe(
  catchError(this.handleError)
)
}
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
