import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Groups } from '../models/groups';
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
    const url: string = "sites/group";
    return this.http.get<Site[]>(this.baseUrl + url + id);
  }
}
