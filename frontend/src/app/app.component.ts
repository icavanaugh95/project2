import { Component } from '@angular/core';
import { RequestsService } from './services/requests.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private requestService: RequestsService, private http: HttpClient) {}

  title = 'Project 2 Front End';
  info = "";

  // You probably need a variable for the output
  qualityAuditResults = "Nothing yet :(";
  protractorResults = "Nothing yet :(";
  createBatchResults = "Nothing yet :(";
  manageBatchResults = "Nothing yet :(";
  trainerResults = "Nothing yet :(";
  navBarResults = "Nothing yet :(";
  assessBatchResults = "Nothing yet :(";

  data:Observable<any> = this.requestService.getSomething();

  // Assign a variable to the method you created to hit the servlet
  qualityAudit:Observable<any> = this.requestService.runQualityAuditTests();
  protractor:Observable<any> = this.requestService.runProtractorTests();
  createBatch:Observable<any> = this.requestService.runCreateBatch();
  manageBatch:Observable<any> = this.requestService.runManageBatch();
  trainerTests:Observable<any> = this.requestService.runTrainerTests();
  navBarTests:Observable<any> = this.requestService.runNavBarTests();
  assessBatch:Observable<any> = this.requestService.runAssessBatchTests();

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
    this.qualityAuditResults = "Let me get that for you";

    this.qualityAudit.subscribe((resp) => {
      // success
      this.qualityAuditResults = resp;
    }, // end succes function
      (err) => {
        // failure
        this.qualityAuditResults = err.error.text;
    }); // end failure function
  };


  getProtractorResults(){
    this.protractorResults = "Let me get that for you";
    this.protractor.subscribe((resp) => {
      // success
      this.protractorResults = resp;
      console.log(this.protractorResults);
    }, (err) => {
      // failure
      this.protractorResults = err.error.text;
    });
  };

  getCreateBatchResults(){
    this.createBatchResults = "Let me get that for you";
    this.createBatch.subscribe((resp) => {
      // success
      this.createBatchResults = resp;
      console.log(this.createBatchResults);
    }, (err) => {
      // failure
      this.createBatchResults = err.error.text;
    });
  };

  getManageBatchResults(){
    this.manageBatchResults = "Let me get that for you";
    this.manageBatch.subscribe((resp) => {
      // success
      this.manageBatchResults = resp;
      console.log(this.manageBatchResults);
    }, (err) => {
      // failure
      this.manageBatchResults = err.error.text;
    });
  };

  getTrainerTestResults(){
    this.trainerResults = "Let me get that for you";
    this.trainerTests.subscribe((resp) => {
      // success
      this.trainerResults = resp;
      console.log(this.trainerResults);
    }, (err) => {
      // failure
      this.trainerResults = err.error.text;
    });
  };

  getNavBarResults(){
    this.navBarResults = "Let me get that for you";
    this.navBarTests.subscribe((resp) => {
      // success
      this.navBarResults = resp;
      console.log(this.navBarResults);
    }, (err) => {
      // failure
      this.navBarResults = err.error.text;
    });
  };

  getAssessBatchResults(){
    this.assessBatchResults = "Let me get that for you";
    this.assessBatch.subscribe((resp) => {
      // success
      this.assessBatchResults = resp;
      console.log(this.assessBatchResults);
    }, (err) => {
      // failure
      this.assessBatchResults = err.error.text;

    });
  };

}
