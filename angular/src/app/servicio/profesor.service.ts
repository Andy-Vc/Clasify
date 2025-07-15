import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../model/usuario.model';
import { Observable } from 'rxjs';
import { ResultadoResponse } from '../dto/resultadoResponse.model';

@Injectable({
  providedIn: 'root',
})
export class ProfesorService {
  private baseUrl = 'http://localhost:8080/profesor';

  constructor(private http: HttpClient) {}

  list(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.baseUrl}/list`);
  }

  create(usuario: Usuario): Observable<ResultadoResponse> {
    return this.http.post<ResultadoResponse>(
      `${this.baseUrl}/register`,
      usuario
    );
  }

  getById(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.baseUrl}/id/${id}`);
  }

  update(usuario: Usuario): Observable<ResultadoResponse> {
    return this.http.patch<ResultadoResponse>(
      `${this.baseUrl}/update`,
      usuario
    );
  }

  delete(id: number): Observable<ResultadoResponse> {
    return this.http.delete<ResultadoResponse>(`${this.baseUrl}/delete/${id}`);
  }
}
