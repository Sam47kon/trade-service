import {Component, OnInit} from '@angular/core';
import {Order} from '../../model/order';
import {OrderService} from '../../service/order.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[];

  constructor(private route: ActivatedRoute, private router: Router, private orderService: OrderService) {
  }

  ngOnInit() {
    this.orderService.findAll().subscribe(data => {
      this.orders = data;
    });
  }

  public deleteOrder(id): void {
    this.orderService.deleteOrder(id).subscribe(result => this.ngOnInit());
  }

  public updateOrder(id: number): void {
    this.router.navigate(['orders/edit', id]);
  }

  orderDetails(orderId: number) {
    this.router.navigate(['orders', orderId]);
  }
}
