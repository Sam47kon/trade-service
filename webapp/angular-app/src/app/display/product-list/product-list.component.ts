import {Component, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[];

  constructor(private route: ActivatedRoute, private router: Router, private productService: ProductService) {
    this.products = this.route.snapshot.data.productList;
  }

  ngOnInit() {
  }

  public deleteProduct(id): void {
    this.productService.deleteProduct(id).subscribe(
      () => this.productService.findAll()
        .subscribe(data => this.products = data));
  }

  public updateProduct(id): void {
    this.router.navigate(['/']);
  }
}
