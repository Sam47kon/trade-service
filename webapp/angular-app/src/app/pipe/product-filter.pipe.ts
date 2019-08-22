import {Pipe, PipeTransform} from '@angular/core';
import {Product} from '../model/product';

@Pipe({
  name: 'productFilter',
  pure: false
})

export class ProductFilterPipe implements PipeTransform {
  transform(products: Product[], searchTerm: string): Product[] {
    if (!products || !searchTerm) {
      return products;
    }

    return products.filter(product =>
      product.name.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1);
  }
}
