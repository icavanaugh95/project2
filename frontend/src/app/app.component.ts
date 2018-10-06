import { Component } from '@angular/core';
import { RequestsService } from './services/requests.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private requestService: RequestsService) {}

  title = 'Project 2 Front End';
  info = "Nothing yet";
  data:Observable<any> = this.requestService.getSomething();

  getData(){
    this.data.subscribe((resp) => { 
      this.info = `${resp[0].name}: ${resp[0].Age}`; 
      console.log(resp); 
    }, // end success function
      (err) => { 
        this.info = err; 
      }); // end err
  }
}
