import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ProductListComponent} from './display/product-list/product-list.component';
import {CreateProductFormComponent} from './form/product-form/create-product-form.component';
import {OrderListComponent} from './display/order-list/order-list.component';
import {CreateOrderFormComponent} from './form/order-form/create-order-form.component';
import {CreateProductCanDeactivateGuardService} from './form/product-form/create-product-can-deactivate-guard.service';
import {CreateOrderCanDeactivateGuardService} from './form/order-form/create-order-can-deactivate-guard.service';
import {OrderDetailsComponent} from './display/order-details/order-details.component';
import {ProductListResolverService} from './display/product-list/product-list-resolver.service';
import {PageNotFoundComponent} from './display/page-not-found.component';
import {OrderDetailGuardService} from './display/order-details/order-detail-guard.service';

const routes: Routes = [
  {
    path: 'products',
    component: ProductListComponent,
    resolve: {productList: ProductListResolverService}
  },
  {
    path: 'products/create',
    component: CreateProductFormComponent,
    canDeactivate: [CreateProductCanDeactivateGuardService]
  },
  {path: 'orders', component: OrderListComponent},
  {
    path: 'orders/create',
    component: CreateOrderFormComponent,
    canDeactivate: [CreateOrderCanDeactivateGuardService]
  },
  {
    path: 'orders/:id', component: OrderDetailsComponent,
    canActivate: [OrderDetailGuardService]
  },
  {path: '', redirectTo: 'products', pathMatch: 'full'},
  {path: 'not-found', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes
    , {enableTracing: true}
  )],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
