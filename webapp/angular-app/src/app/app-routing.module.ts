import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ProductListComponent} from './display/product-list/product-list.component';
import {CreateProductFormComponent} from './form/product-form/create-product-form.component';
import {OrderListComponent} from './display/order-list/order-list.component';
import {CreateOrderFormComponent} from './form/order-form/create-order-form.component';
import {CreateProductCanDeactivateGuardService} from './form/product-form/create-product-can-deactivate-guard.service';
import {CreateOrderCanDeactivateGuardService} from './form/order-form/create-order-can-deactivate-guard.service';
import {OrderDetailsComponent} from './display/order-details/order-details.component';

const routes: Routes = [
  {path: 'products', component: ProductListComponent},
  {
    path: 'products/create',
    component: CreateProductFormComponent,
    canDeactivate: [CreateProductCanDeactivateGuardService]
  },
  {path: '', redirectTo: 'products', pathMatch: 'full'},
  {path: 'orders', component: OrderListComponent},
  {
    path: 'orders/create',
    component: CreateOrderFormComponent,
    canDeactivate: [CreateOrderCanDeactivateGuardService]
  },
  {path: 'orders/:id', component: OrderDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
