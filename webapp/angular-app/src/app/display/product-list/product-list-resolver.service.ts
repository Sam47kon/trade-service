import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Product} from '../../model/product';
import {Observable} from 'rxjs';
import {ProductService} from '../../service/product.service';

export class ProductListResolverService implements Resolve<Product[]> {
  constructor(private productService: ProductService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product[]> {
    return this.productService.findAll();
  }
}
