import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';

import {ProductService} from './service/product.service';
import {OrderService} from './service/order.service';
import {ProductListComponent} from './display/product-list/product-list.component';
import {OrderListComponent} from './display/order-list/order-list.component';
import {CreateProductFormComponent} from './form/product-form/create-product-form.component';
import {CreateOrderFormComponent} from './form/order-form/create-order-form.component';
import {CreateProductCanDeactivateGuardService} from './form/product-form/create-product-can-deactivate-guard.service';
import {CreateOrderCanDeactivateGuardService} from './form/order-form/create-order-can-deactivate-guard.service';
import {OrderDetailsComponent} from './display/order-details/order-details.component';
import {ProductFilterPipe} from './pipe/product-filter.pipe';
import {ProductListResolverService} from './display/product-list/product-list-resolver.service';
import {PageNotFoundComponent} from './display/page-not-found.component';
import {OrderDetailGuardService} from './display/order-details/order-detail-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    OrderListComponent,
    CreateProductFormComponent,
    CreateOrderFormComponent,
    OrderDetailsComponent,
    ProductFilterPipe,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ProductService,
    OrderService,
    CreateProductCanDeactivateGuardService,
    CreateOrderCanDeactivateGuardService,
    ProductListResolverService,
    OrderDetailGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
