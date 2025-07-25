import { Injectable } from '@angular/core';
import { HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { NotaDTO } from '../../shared/model/notaDTO.model';
import { ResultadoResponse } from '../../shared/model/resultadoResponse.model';
import { GenericService } from '../../shared/services/generic.service';

@Injectable({
  providedIn: 'root',
})
export class NotaProfesorService extends GenericService<NotaDTO> {
  private baseUrl = 'http://localhost:8080/profesor/nota';

  constructor(http: HttpClient) {
    super(http);
  }

  saveOrUpdateNota(nota: NotaDTO): Observable<ResultadoResponse> {
    return this.http.post<ResultadoResponse>(`${this.baseUrl}/saveOrUpdate`, nota);
  }

  getByIdNota(id: number) {
    return super.getById(this.baseUrl, id);
  }

  getNota(idCurso: string, idUsuario: number, nroNota: number): Observable<NotaDTO> {
    const params = new HttpParams()
      .set('idCurso', idCurso)
      .set('idUsuario', idUsuario.toString())
      .set('nroNota', nroNota.toString());

    return this.http.get<NotaDTO>(`${this.baseUrl}/nota`, { params });
  }
}
