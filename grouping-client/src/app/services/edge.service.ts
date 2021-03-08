import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { Groups } from '../models/groups';
import { Review } from '../models/review';
import { Site } from '../models/site';
import { Tocken } from '../models/tocken';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class EdgeService {

  private baseUrl: string = "http://localhost:8083/";

  public tocken: string | null = '';

  constructor(
    private http: HttpClient,
    private app: AppComponent,
    private router: Router
  ) {
    let tockenStorage = localStorage.getItem('tockenLogin');
    if(!this.router.url.includes('login') && tockenStorage === null) {
      this.router.navigate(['/login']);
    } else {
      this.tocken = tockenStorage;
    }
  }

  register(user: User): Observable<Tocken> {
    const url: string = "new-user";
    let body = JSON.stringify(user);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');
    return this.http.post<Tocken>(this.baseUrl + url, body);
  }

  login(user: User): Observable<Tocken> {
    const url: string = "user";
    let body = JSON.stringify(user);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');
    return this.http.post<Tocken>(this.baseUrl + url, body);
  }

  logout(): void {
    localStorage.removeItem('tockenLogin');
    this.tocken = '';
  }

  getAllGroups(): Observable<Groups[]> {
    const url: string = "groups/";
    return this.http.get<Groups[]>(this.baseUrl + url);
  }

  getSitesByGroupId(id: number): Observable<Site[]> {
    const url: string = "sites/group/";
    return this.http.post<Site[]>(this.baseUrl + url + id, this.tocken);
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
