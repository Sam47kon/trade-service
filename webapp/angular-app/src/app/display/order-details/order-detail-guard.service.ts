import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {OrderService} from '../../service/order.service';

export class OrderDetailGuardService implements CanActivate {
  constructor(private orderService: OrderService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const order = this.orderService.getOrderById(+route.paramMap.get('id')).subscribe(value => {
      if (value) {
        return value;
      } else {
        return null;
      }
    });

    if (order !== null) {
      return true;
    } else {
      this.router.navigate(['not-found']);
      return false;
    }
  }
}
