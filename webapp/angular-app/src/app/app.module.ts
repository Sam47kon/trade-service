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
import {ProductFormComponent} from './form/product-form/product-form.component';
import {OrderFormComponent} from './form/order-form/order-form.component';
import {ProductFormCanDeactivateGuardService} from './form/product-form/product-form-can-deactivate-guard.service';
import {OrderFormCanDeactivateGuardService} from './form/order-form/order-form-can-deactivate-guard.service';
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
    ProductFormComponent,
    OrderFormComponent,
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
    ProductFormCanDeactivateGuardService,
    OrderFormCanDeactivateGuardService,
    ProductListResolverService,
    OrderDetailGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
