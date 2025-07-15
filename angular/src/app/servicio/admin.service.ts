import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { dashboardResponse } from '../dto/dashboardResponse.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl = 'http://localhost:8080/admin';

  constructor(private http: HttpClient) {}

  getDashboard(): Observable<dashboardResponse> {
    return this.http.get<dashboardResponse>(`${this.baseUrl}/dashboard`);
  }
}
