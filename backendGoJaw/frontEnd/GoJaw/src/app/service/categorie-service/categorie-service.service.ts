import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategorieServiceService {
  categoriepoint: string = 'http://localhost:4000/categorie';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient, public router: Router) { }
 /*--------------------categorie--------------------------*/
 createCategorie(obejct): Observable<any> {
  let api = `${this.categoriepoint}/create`;
  return this.http.post(api, obejct)
    .pipe(
      catchError(this.handleError)
    )

}

getCategories() {
  return this.http.get(`${this.categoriepoint}/all`);
}

getcategorie(id): Observable<any> {
let url = `${this.categoriepoint}/read/${id}`;
return this.http.get(url, {headers: this.headers}).pipe(
  map((res: Response) => {
    return res || {}
  }),
  catchError(this.handleError)
)
}
// Update gouv
updateCategorie(id, data): Observable<any> {
let url = `${this.categoriepoint}/update/${id}`;
return this.http.put(url, data, { headers: this.headers }).pipe(
  catchError(this.handleError)
)
}

// Delete Gouv
deleteCategorie(id): Observable<any> {
let url = `${this.categoriepoint}/delete/${id}`;
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
