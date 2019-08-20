import {Product} from './product';

export class OrderItem {
  product: Product;
  count: number;

  constructor(product?: Product) {
    this.product = product;
  }
}
