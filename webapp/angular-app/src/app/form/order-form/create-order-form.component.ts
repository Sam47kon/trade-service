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
  countProduct: number[] = [];

  orderItemsMap: Map<Product, number> = new Map<Product, number>();

  constructor(private route: ActivatedRoute, private router: Router, private orderService: OrderService,
              private productService: ProductService) {
    this.order = new Order();
  }

  ngOnInit() {
    this.productService.findAll().subscribe(data => {
      this.products = data;
    });
  }

  onSubmit() {
    this.order.orderItems = [];
    let index = 0;
    for (const item of this.orderItemsMap) {
      this.order.orderItems[index] = new OrderItem();
      this.order.orderItems[index].product = new Product();
      this.order.orderItems[index].product.productId = item[0].productId;
      this.order.orderItems[index++].count = item[1];
    }
    this.orderService.addOrder(this.order).subscribe(result => this.goToOrderList());
  }

  private goToOrderList() {
    this.router.navigate(['/orders']);
  }

  public addProductToOrder(product: Product, count: number) {
    this.orderItemsMap.set(product, count);
  }

  public deleteProductWithOrder(product: Product) {
    this.orderItemsMap.delete(product);
  }

  public getTotal(): number {
    let total = 0;
    for (const product of this.orderItemsMap.keys()) {
      const count = this.orderItemsMap.get(product);
      for (let index = 0; index < count; index++) {
        total += product.price;
      }
    }
    return total;
  }

  public getQuantity(): number {
    let quantity = 0;
    for (const count of this.orderItemsMap.values()) {
      quantity += count;
    }
    return quantity;
  }
}
