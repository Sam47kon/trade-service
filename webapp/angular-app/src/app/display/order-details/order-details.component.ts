import {Component, OnInit} from '@angular/core';
import {OrderService} from '../../service/order.service';
import {Order} from '../../model/order';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderItem} from '../../model/order-item';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  order: Order;
  orderItems: OrderItem[];
  id: number;

  constructor(private route: ActivatedRoute, private router: Router, private orderService: OrderService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
      this.orderService.getOrderById(this.id).subscribe(order => {
        this.orderItems = order.orderItems;
        this.order = order;
      });
    });
  }

  goBack() {
    this.router.navigate(['orders']);
  }
}
