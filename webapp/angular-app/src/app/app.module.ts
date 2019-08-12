import {BrowserModule}from'@angular/platform-browser';
import {NgModule }from '@angular/core';

import {AppRoutingModule}from './app-routing.module';
import {FormsModule}from '@angular/forms';
import { HttpClientModule}from '@angular/common/http';
import {AppComponent}from './app.component';

import {ProductListComponent }from './product-list/product-list.component';
import {ProductFormComponent}from './product-form/product-form.component';
import {ProductService}from './service/product-service.service';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    ProductFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {}
