import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) { }

  getSomething():Observable<any>{
    return this.http.get("http://54.174.104.191:8080/Project2/Servlet/getSomething");
  }

  // This is what you need to do to get your tests to run from angular
  // dont forget to add to app.component.ts
  runQualityAuditTests():Observable<any>{
    return this.http.get("http://54.174.104.191:8080/Project2/Servlet/QualityAuditTests");
  }

  runProtractorTests():Observable<any>{
    return this.http.get("http://54.174.104.191:8080/Project2/Servlet/ProtractorTests");
  }
}
