import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { Groups } from '../models/groups';
import { InvitationCode } from '../models/invitation-code';
import { Review } from '../models/review';
import { Site } from '../models/site';
import { SiteWithReview } from '../models/site-with-review';
import { Tocken } from '../models/tocken';
import { User } from '../models/user';



@Injectable({
  providedIn: 'root'
})
export class EdgeService {

  private baseUrl: string = "http://localhost:8083/";

  public tocken: string | null = '';

  public groupList: Groups[] = [];
  public siteList: Site[] = [];

  public selectedGroup: number = 1;
  public selectedSiteId: number = 0;
  public userId: number = 0;

  public newSite: Site = new Site(1, '', '', '');

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

  getGroupsByUser(): Observable<Groups[]> {
    const url: string = "groups";
    return this.http.post<Groups[]>(this.baseUrl + url, this.tocken);
  }

  getSitesByGroupId(id: number): Observable<SiteWithReview[]> {
    const url: string = "sites/group/";
    return this.http.post<SiteWithReview[]>(this.baseUrl + url + id, this.tocken);
  }

  saveNewReview(review: Review): Observable<{}> {
    const url: string = "reviews";
    review.tocken = this.tocken;
    let body = JSON.stringify(review);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<{}>(this.baseUrl + url, body);
  }

  saveNewSite(site: Site): Observable<Site> {
    const url: string = "sites";
    site.tocken = this.tocken;
    let body = JSON.stringify(site);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<Site>(this.baseUrl + url, body);
  }

  saveNewGroup(group: Groups): Observable<Groups> {
    const url: string = "new-group";
    let body = JSON.stringify(group);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<Groups>(this.baseUrl + url, body);
  }

  joinGroup(code: string): Observable<Groups> {
    const url: string = "join-group";
    const invitationCode: InvitationCode = new InvitationCode(code, this.tocken);
    let body = JSON.stringify(invitationCode);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<Groups>(this.baseUrl + url, body);
  }

  chargeReviews(groupId: number, site: any): Observable<Review[]> {
    const url: string = "reviews-by-group/";
    site.tocken = this.tocken;
    let body = JSON.stringify(site);
    //Need to remove '_' from the names of the properties
    body = body.replace(/"_/g, '"');

    return this.http.post<Review[]>(this.baseUrl + url + groupId, body);
  }

  getAllSites(): Observable<Site[]> {
    const url: string = "all-sites";
    return this.http.post<Site[]>(this.baseUrl + url, this.tocken);
  }
}
