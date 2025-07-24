import { cursoEstudianteGrafic } from "./cursoEstudianteGrafic.model";
import { UsuarioDTO } from "./usuarioDTO.model";

export interface dashboardResponse {
  totalProfesores: number;
  totalEstudiantes: number;
  totalCursos: number;
  estudiantesPorCurso?: cursoEstudianteGrafic[];
  estudiantesRecientes?: UsuarioDTO[];
}
