import { Grado } from './grado.model';
import { UsuarioDTO } from './usuarioDTO.model';

export class CursoEstudiantesDTO {
  idCurso?: string;
  nombreCurso?: string;
  idProfesor?: UsuarioDTO;
  idGrado?: Grado;
  flgEliminado?: boolean;
  estudiantes?: UsuarioDTO[];
}
