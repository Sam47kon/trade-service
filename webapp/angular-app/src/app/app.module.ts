import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';

import {ProductService} from './service/product.service';
import {OrderService} from './service/order.service';
import {ProductListComponent} from './list/product-list/product-list.component';
import {OrderListComponent} from './list/order-list/order-list.component';
import {CreateProductFormComponent} from './form/product-form/create-product-form.component';
import {CreateOrderFormComponent} from './form/order-form/create-order-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    OrderListComponent,
    CreateProductFormComponent,
    CreateOrderFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ProductService, OrderService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
