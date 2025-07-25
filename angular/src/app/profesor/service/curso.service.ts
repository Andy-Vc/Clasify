import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CursoEstudiantesDTO } from '../../shared/model/cursoEstudiantesDTO.model';

@Injectable({
  providedIn: 'root',
})
export class ProfesorService {
  private baseUrl = 'http://localhost:8080/profesor/curso/list';

  constructor(private http: HttpClient) {}
  getCursosProfesor(token: string): Observable<CursoEstudiantesDTO[]> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<CursoEstudiantesDTO[]>(this.baseUrl, { headers });
  }
  
}
