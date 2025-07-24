import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GenericService } from '../../shared/services/generic.service';
import { CursoDTO } from '../../shared/model/cursoDTO.model';
import { CursoEstudiantesDTO } from '../../shared/model/cursoEstudiantesDTO.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CursoService extends GenericService<CursoDTO> {
  private baseUrl = 'http://localhost:8080/admin/curso';

  constructor(http: HttpClient) {
    super(http); 
  }

  listCurso() {
    return super.list(this.baseUrl);
  }

  createCurso(curso: CursoDTO) {
    return super.create(this.baseUrl, curso);
  }

  getCursoStudents(id: string): Observable<CursoEstudiantesDTO> {
    return this.http.get<CursoEstudiantesDTO>(`${this.baseUrl}/detalle/${id}`);
  }

  getByIdCurso(id: string) {
    return super.getById(this.baseUrl, id);
  }

  updateCurso(curso: CursoDTO) {
    return super.update(this.baseUrl, curso);
  }

  deleteCurso(id: string) {
    return super.delete(this.baseUrl, id);
  }
}