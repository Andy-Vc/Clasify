import { EstudianteProfesorDTO } from './estudianteProfesorDTO.model';
import { Grado } from './grado.model';
import { UsuarioDTO } from './usuarioDTO.model';

export interface CursoEstudiantesDTO {
  idCurso: string;
  nombreCurso?: string;
  idProfesor?: UsuarioDTO;
  idGrado?: Grado;
  flgEliminado?: boolean;
  estudiantes?: EstudianteProfesorDTO[];
}
