import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ProductListComponent} from './display/product-list/product-list.component';
import {ProductFormComponent} from './form/product-form/product-form.component';
import {OrderListComponent} from './display/order-list/order-list.component';
import {OrderFormComponent} from './form/order-form/order-form.component';
import {ProductFormCanDeactivateGuardService} from './form/product-form/product-form-can-deactivate-guard.service';
import {OrderFormCanDeactivateGuardService} from './form/order-form/order-form-can-deactivate-guard.service';
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
    path: 'products/edit/:id',
    component: ProductFormComponent,
    canDeactivate: [ProductFormCanDeactivateGuardService]
  },
  {
    path: 'orders',
    component: OrderListComponent
  },
  {
    path: 'orders/edit/:id',
    component: OrderFormComponent,
    canDeactivate: [OrderFormCanDeactivateGuardService]
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
    // , {enableTracing: true}
  )],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
