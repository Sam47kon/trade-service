import {Component, OnInit} from '@angular/core';
import {Order} from '../../model/order';
import {OrderService} from '../../service/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[];

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.orderService.findAll().subscribe(data => {
      this.orders = data;
    });
  }

  public deleteOrder(id): void {
    this.orderService.deleteOrder(id).subscribe(result => this.ngOnInit());
  }

  public updateOrder(id: number, order: Order): void {
    this.orderService.updateOrder(id, order).subscribe(result => this.ngOnInit());
  }
}
