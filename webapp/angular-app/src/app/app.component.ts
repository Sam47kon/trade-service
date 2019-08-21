import {Component} from '@angular/core';

import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  urlGitHub: string;

  constructor(private http: HttpClient) {
    this.title = 'Trade Service';
    this.urlGitHub = 'https://github.com/Sam47kon/Project-for-21-Century';
  }
}
