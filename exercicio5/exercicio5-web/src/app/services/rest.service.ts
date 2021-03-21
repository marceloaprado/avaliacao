import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";

import { Observable } from "rxjs";
import { environment } from 'src/environments/environment';
import { makeErrorResponse } from "../payloads/errorresponse";

@Injectable({
  providedIn: "root"
})
export class RestService {
  private API_URL:  string;

  constructor(
    public httpClient: HttpClient
  ) {
    this.API_URL = environment.urlBase;
  }

  private static HEADERS_UNSIGN = {
    headers: new HttpHeaders({
      "Content-Type": "application/json"      
    })
  };

  public getRequestUnsign(url: string): Observable<any> {
    const source = new Observable(observer => {
      const URI = encodeURI(this.API_URL + url);
      this.httpClient.get(URI, RestService.HEADERS_UNSIGN).subscribe(
        (response) => {
          observer.next(response);
          observer.complete();
        },
        (error) => observer.error(makeErrorResponse(error))
      );
    });

    return source;
  }

  public postRequestUnsign(url: string, request): Observable<any> {
    const source = new Observable(observer => {
      const URI = encodeURI(this.API_URL + url);
      this.httpClient.post(URI, request, RestService.HEADERS_UNSIGN).subscribe(
        (response) => {
          observer.next(response);
          observer.complete();
        },
        (error) => observer.error(makeErrorResponse(error))
      );
    });

    return source;
  }
  
  public putRequestUnsign(url: string, request): Observable<any> {
    const source = new Observable(observer => {
      const URI = encodeURI(this.API_URL + url);
      this.httpClient.put(URI, request, RestService.HEADERS_UNSIGN).subscribe(
        (response) => {
          observer.next(response);
          observer.complete();
        },
        (error) => observer.error(makeErrorResponse(error))
      );
    });

    return source;
  }

  public patchRequestUnsign(url: string, request): Observable<any> {
    const source = new Observable(observer => {
      const URI = encodeURI(this.API_URL + url);
      this.httpClient.patch(URI, request, RestService.HEADERS_UNSIGN).subscribe(
        (response) => {
          observer.next(response);
          observer.complete();
        },
        (error) => observer.error(makeErrorResponse(error))
      );
    });

    return source;
  }

  public deleteRequestUnsign(url: string): Observable<any> {
    const source = new Observable(observer => {
      const URI = encodeURI(this.API_URL + url);
      this.httpClient.delete(URI, RestService.HEADERS_UNSIGN).subscribe(
        (response) => {
          observer.next(response);
          observer.complete();
        },
        (error) => observer.error(makeErrorResponse(error))
      );
    });

    return source;
  }
}
