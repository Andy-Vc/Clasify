import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResultadoResponse } from '../model/resultadoResponse.model';

@Injectable({
  providedIn: 'root',
})
export class GenericService<T> {
  constructor(protected http: HttpClient) {}

  list(baseUrl: string): Observable<T[]> {
    return this.http.get<T[]>(`${baseUrl}/list`);
  }

  create(baseUrl: string, entity: T): Observable<ResultadoResponse> {
    return this.http.post<ResultadoResponse>(`${baseUrl}/register`, entity);
  }

  getById(baseUrl: string, id: number | string): Observable<T> {
    return this.http.get<T>(`${baseUrl}/id/${id}`);
  }

  update(baseUrl: string, entity: T): Observable<ResultadoResponse> {
    return this.http.patch<ResultadoResponse>(`${baseUrl}/update`, entity);
  }

  delete(baseUrl: string, id: number | string): Observable<ResultadoResponse> {
    return this.http.delete<ResultadoResponse>(`${baseUrl}/delete/${id}`);
  }
}