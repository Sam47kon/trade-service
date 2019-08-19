import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ProductListComponent} from './list/product-list/product-list.component';
import {CreateProductFormComponent} from './forms/product-form/create-product-form.component';
import {OrderListComponent} from './list/order-list/order-list.component';
import {CreateOrderFormComponent} from './forms/order-form/create-order-form.component';

const routes: Routes = [
  {path: 'products', component: ProductListComponent},
  {path: 'products/create', component: CreateProductFormComponent},
  {path: '', redirectTo: 'products', pathMatch: 'full'},
  {path: 'orders', component: OrderListComponent},
  {path: 'orders/create', component: CreateOrderFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
