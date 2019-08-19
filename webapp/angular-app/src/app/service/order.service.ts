import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Order} from '../model/order';
import {catchError} from 'rxjs/operators';

@Injectable()
export class OrderService {
  private readonly orderUrl: string;

  constructor(private http: HttpClient) {
    this.orderUrl = 'http://localhost:8080/orders';
  }

  private static handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error('Backend returned code ${error.status},', 'body was: ${error.error}');
    }
    return throwError('Something bad happened; please try again later.');
  }

  public findAll(): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl)
      .pipe(catchError(OrderService.handleError));
  }

  public deleteOrder(id: number) {
    return this.http.delete(`${this.orderUrl}/${id}`)
      .pipe(catchError(OrderService.handleError));
  }

  public updateOrder(id: number, order: Order) {
    return this.http.put(this.orderUrl + '/' + id, order)
      .pipe(catchError(OrderService.handleError));
  }

  addOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(this.orderUrl + '/', order, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    })
      .pipe(catchError(OrderService.handleError));
  }
}
