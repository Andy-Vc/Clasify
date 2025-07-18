import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../shared/model/usuario.model';
import { GenericService } from '../../shared/services/generic.service';

@Injectable({
  providedIn: 'root',
})
export class ProfesorService extends GenericService<Usuario> {
  private baseUrl = 'http://localhost:8080/profesor';

  constructor(http: HttpClient) {
    super(http); 
  }

  listProfesor() {
    return super.list(this.baseUrl);
  }

  createProfesor(profesor: Usuario) {
    return super.create(this.baseUrl, profesor);
  }

  getByIdProfesor(id: number) {
    return super.getById(this.baseUrl, id);
  }

  updateProfesor(profesor: Usuario) {
    return super.update(this.baseUrl, profesor);
  }

  deleteProfesor(id: number) {
    return super.delete(this.baseUrl, id);
  }
}
