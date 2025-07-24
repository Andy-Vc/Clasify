import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Grado } from '../../shared/model/grado.model';
import { GenericService } from '../../shared/services/generic.service';

@Injectable({
  providedIn: 'root',
})
export class GradoService extends GenericService<Grado> {
  private baseUrl = 'http://localhost:8080/admin/grado';

  constructor(http: HttpClient) {
    super(http); 
  }

  listGrado() {
    return super.list(this.baseUrl);
  }

  createGrado(grado: Grado) {
    return super.create(this.baseUrl, grado);
  }

  getByIdGrado(id: number) {
    return super.getById(this.baseUrl, id);
  }

  updateGrado(grado: Grado) {
    return super.update(this.baseUrl, grado);
  }

  deleteGrado(id: number) {
    return super.delete(this.baseUrl, id);
  }
}