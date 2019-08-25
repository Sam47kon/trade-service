import {Injectable} from '@angular/core';
import {CanDeactivate} from '@angular/router';
import {OrderFormComponent} from './order-form.component';

@Injectable({
  providedIn: 'root'
})
export class OrderFormCanDeactivateGuardService implements CanDeactivate<OrderFormComponent> {
  canDeactivate(component: OrderFormComponent): boolean {
    if (component.orderForm.dirty && !component.orderForm.submitted) {
      return confirm('Вы уверены что хотите покинуть страницу, не заполнив данные?');
    }
    return true;
  }
}
