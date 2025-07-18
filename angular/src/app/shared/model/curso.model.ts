import { Grado } from "./grado.model";
import { Usuario } from "./usuario.model";

export class Curso{
    idCurso?: string;
    nombreCurso? : string;
    idProfesor?: Usuario;
    idGrado?: Grado;
    flgEliminado?: boolean;
    estudiantes?: any[];
}