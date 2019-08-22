import {CanDeactivate} from '@angular/router';
import {CreateProductFormComponent} from './create-product-form.component';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CreateProductCanDeactivateGuardService implements CanDeactivate<CreateProductFormComponent> {
  canDeactivate(component: CreateProductFormComponent): boolean {
    if (component.createProductForm.dirty && !component.createProductForm.submitted) {
      return confirm('Вы уверены что хотите покинуть страницу, не заполнив данные?');
    }
    return true;
  }
}
