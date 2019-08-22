import {Injectable} from '@angular/core';
import {CanDeactivate} from '@angular/router';
import {CreateOrderFormComponent} from './create-order-form.component';

@Injectable({
  providedIn: 'root'
})
export class CreateOrderCanDeactivateGuardService implements CanDeactivate<CreateOrderFormComponent> {
  canDeactivate(component: CreateOrderFormComponent): boolean {
    if (component.createOrderForm.dirty && !component.createOrderForm.submitted) {
      return confirm('Вы уверены что хотите покинуть страницу, не заполнив данные?');
    }
    return true;
  }
}
