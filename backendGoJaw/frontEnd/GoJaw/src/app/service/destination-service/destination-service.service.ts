import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DialogDesComponent } from 'src/app/des/dialog-des/dialog-des.component';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DestinationServiceService {
  destinationpoint: string = 'http://localhost:4000/destination';
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient, public router: Router,public dialog: MatDialog) { }
  openDialogEditDestination(value): void {
    const dialogRef = this.dialog.open(DialogDesComponent, {
      data:{
        id_destination:value
      }  
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result!=null){
        location.reload();
        console.log(result);
      }

      
    
    });
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
  
    getDestinationByGouv(gouv): Observable<any> {
      let url = `${this.destinationpoint}/byGouv/${gouv}`;
      return this.http.get(url, {headers: this.headers}).pipe(
        map((res: Response) => {
          return res || {}
        }),
        catchError(this.handleError)
      )
    }
    getDestinationById(id): Observable<any> {
      let url = `${this.destinationpoint}/read/${id}`;
      return this.http.get(url, {headers: this.headers}).pipe(
        map((res: Response) => {
          return res || {}
        }),
        catchError(this.handleError)
      )
    }
    updateDestination(id, data): Observable<any> {
      let url = `${this.destinationpoint}/update/${id}`;
      return this.http.put(url, data, { headers: this.headers }).pipe(
        catchError(this.handleError)
      )
    }
    deleteDestination(gouv): Observable<any> {
      let url = `${this.destinationpoint}/delete/${gouv}`;
      return this.http.delete(url, { headers: this.headers }).pipe(
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
