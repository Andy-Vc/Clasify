import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../shared/model/usuario.model';
import { Observable } from 'rxjs';
import { AutenticarUsuario } from '../../shared/model/autenticarUsuario.model';
import { ResultadoResponse } from '../../shared/model/resultadoResponse.model';
import { LoginResponse } from '../../shared/model/loginResponse.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  login(credentials: AutenticarUsuario): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, credentials);
  }

  registrar(usuario: Usuario): Observable<ResultadoResponse> {
    return this.http.post<ResultadoResponse>(
      `${this.baseUrl}/register`,
      usuario
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
  }
}
