import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EventServiceService {
  eventpoint: string = 'http://localhost:4000/event';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient, public router: Router) { }
  /*------------------event-*/
 createEventByAdmin(obejct): Observable<any> {
  let api = `${this.eventpoint}/createEventByAdmin`;
  return this.http.post(api, obejct).pipe(
    map((res: Response) => {
      return res || {}
    }),
    catchError(this.handleError)
  )
}


getEvents() {
  return this.http.get(`${this.eventpoint}/allPublic`);
}
geteventsByGouv(gouv): Observable<any> {
  let url = `${this.eventpoint}/byGouv/${gouv}`;
  return this.http.get(url, {headers: this.headers}).pipe(
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
