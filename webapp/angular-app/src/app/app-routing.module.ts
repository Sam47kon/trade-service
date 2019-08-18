import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ProductListComponent} from './product-list/product-list.component';
import {CreateProductFormComponent} from './product-form/create-product-form.component';

const routes: Routes = [
  {path: 'products', component: ProductListComponent},
  {path: 'products/create', component: CreateProductFormComponent},
  {path: '', redirectTo: 'products', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
