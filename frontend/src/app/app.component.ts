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

  // You probably need a variable for the output
  qualityAuditResults = "Nothing right now :(";
  
  data:Observable<any> = this.requestService.getSomething();

  // Assign a variable to the method you created to hit the servlet
  qualityAudit:Observable<any> = this.requestService.runQualityAuditTests();


  getData(){
    this.data.subscribe((resp) => { 
      this.info = `${resp[0].name}: ${resp[0].Age}`; 
      console.log(resp); 
    }, // end success function
      (err) => { 
        this.info = err; 
      }); // end err
  };

  // Do something with the data
  // This gets bound to the button in the app.component.html
  getQualityAuditTestResults(){
    this.qualityAudit.subscribe((resp) => {
      // what to do if success
      this.qualityAuditResults = resp;

    }, // end succes function
      (err) => {
        // what to do if fails
        this.qualityAuditResults = err;

    }); // end failure function
  };
}
