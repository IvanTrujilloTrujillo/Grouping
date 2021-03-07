import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Groups } from '../models/groups';
import { Review } from '../models/review';
import { Site } from '../models/site';

@Injectable({
  providedIn: 'root'
})
export class EdgeService {

  private baseUrl: string = "http://localhost:8083/";

  constructor(
    private http: HttpClient
  ) { }

  getAllGroups(): Observable<Groups[]> {
    const url: string = "groups/";
    return this.http.get<Groups[]>(this.baseUrl + url);
  }

  getSitesByGroupId(id: number): Observable<Site[]> {
    const url: string = "sites/group/";
    return this.http.get<Site[]>(this.baseUrl + url + id);
  }

  saveNewReview(review: Review): Observable<{}> {
    const url: string = "reviews";
    let body = JSON.stringify(review);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<{}>(this.baseUrl + url, body);
  }

  saveNewSite(site: Site): Observable<Site> {
    const url: string = "sites";
    let body = JSON.stringify(site);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<Site>(this.baseUrl + url, body);
  }
}
