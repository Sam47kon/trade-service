import {Component, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.findAll().subscribe(data => {
      this.products = data;
    });
  }

  public deleteProduct(id): void {
    this.productService.deleteProduct(id).subscribe(result => this.ngOnInit());
  }
}
