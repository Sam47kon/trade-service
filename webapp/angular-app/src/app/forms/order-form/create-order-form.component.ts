import {Component, OnInit} from '@angular/core';
import {Order} from '../../model/order';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderService} from '../../service/order.service';
import {ProductService} from '../../service/product.service';
import {Product} from '../../model/product';
import {OrderItem} from '../../model/order-item';

@Component({
  selector: 'app-order-form',
  templateUrl: './create-order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class CreateOrderFormComponent implements OnInit {
  order: Order;
  products: Product[];

  constructor(private route: ActivatedRoute, private router: Router, private orderService: OrderService,
              private productService: ProductService) {
    this.order = new Order();
    this.order.orderItems = new OrderItem()[10]; // TODO
  }

  ngOnInit() {
    this.productService.findAll().subscribe(data => {
      this.products = data;
    });
  }

  onSubmit() {
    this.orderService.addOrder(this.order).subscribe(result => this.goToOrderList());
  }

  private goToOrderList() {
    this.router.navigate(['/orders']);
  }
}
