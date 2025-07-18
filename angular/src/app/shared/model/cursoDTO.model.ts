import { Grado } from "./grado.model";
import { UsuarioDTO } from "./usuarioDTO.model";

export class CursoDTO{
    idCurso?: string;
    nombreCurso? : string;
    idProfesor?: UsuarioDTO;
    idGrado?: Grado;
    flgEliminado?: boolean;
}