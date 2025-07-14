import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../model/usuario.model'; 
import { Observable } from 'rxjs';
import { AutenticarUsuario } from '../dto/autenticarUsuario.model';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = 'http://localhost:8080/auth'; 

  constructor(private http: HttpClient) {}

  login(credentials: AutenticarUsuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.baseUrl}/login`, credentials);
  }

  registrar(usuario: Usuario): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, usuario);
  }
}