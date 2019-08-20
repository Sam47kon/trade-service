import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Product} from '../../model/product';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './create-product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class CreateProductFormComponent {
  product: Product;

  constructor(private route: ActivatedRoute, private router: Router, private productService: ProductService) {
    this.product = new Product();
  }

  onSubmit() {
    this.productService.addProduct(this.product).subscribe(result => this.gotoProductList());
  }

  private gotoProductList() {
    this.router.navigate(['/products']);
  }
}
