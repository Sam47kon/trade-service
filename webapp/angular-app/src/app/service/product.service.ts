import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';

import {Product} from '../model/product';
import {Observable} from 'rxjs/Observable';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';
import 'rxjs-compat/add/operator/delay';


@Injectable()
export class ProductService {
  private readonly productsUrl: string;
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {
    this.productsUrl = 'http://localhost:8080/products';
    // this.productsUrl = 'http://localhost:8080/trade-service/products';
  }

  private static handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error('Backend returned code ${error.status},', 'body was: ${error.error}');
    }
    return throwError('Something bad happened; please try again later.');
  }

  public findAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsUrl)
    .pipe(catchError(ProductService.handleError))
    .delay(300);
  }

  public addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.productsUrl}/`, product,
      {headers: this.headers})
    .pipe(catchError(ProductService.handleError));
  }

  public deleteProduct(id: number) {
    return this.http.delete(`${this.productsUrl}/${id}`)
    .pipe(catchError(ProductService.handleError));
  }

  public editProduct(product: Product, id: number): Observable<Object> {
    return this.http.put(`${this.productsUrl}/${id}`, product,
      {headers: this.headers})
    .pipe(catchError(ProductService.handleError));
  }

  public getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.productsUrl}/${id}`)
    .pipe(catchError(ProductService.handleError));
  }
}
