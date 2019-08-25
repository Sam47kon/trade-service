import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Product} from '../../model/product';
import {ProductService} from '../../service/product.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  @ViewChild('productForm', {static: false}) public productForm: NgForm;
  product: Product = new Product();
  panelTitle: string;
  private id: number;

  constructor(private route: ActivatedRoute, private router: Router, private productService: ProductService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
      if (this.id !== 0) {
        this.panelTitle = 'Редактирование товара';
        this.productService.getProductById(this.id).subscribe(product => {
          this.product = product;
        });
      } else {
        this.panelTitle = 'Добавление товара';
        this.productForm.reset();
      }
    });
  }

  onSubmit() {
    if (this.id !== 0) {
      this.productService.editProduct(this.product, this.id).subscribe(value => {
        this.product = Object.assign(new Product(), value)
      });
      this.gotoProductList();
    } else {
      this.productService.addProduct(this.product).subscribe(result => this.gotoProductList());
    }
  }

  private gotoProductList() {
    this.router.navigate(['/products']);
  }
}
