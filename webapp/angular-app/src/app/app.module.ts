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
import { OrderDetailsComponent } from './display/order-details/order-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    OrderListComponent,
    CreateProductFormComponent,
    CreateOrderFormComponent,
    OrderDetailsComponent
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
    CreateOrderCanDeactivateGuardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
