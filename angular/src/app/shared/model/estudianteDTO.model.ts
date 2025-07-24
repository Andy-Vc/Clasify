export interface EstudianteDTO {
  idUsuario: number;
  nombreUsuario: string;
  apellidoUsuario: string;
  email: string;
  rolDescripcion: string;
  flgEliminado: boolean;
  cursosInscritos?: Array<{
    curso: {
      nombreCurso: string;
      idProfesor: {
        nombreUsuario: string;
        apellidoUsuario:string;
      }
    }
  }>;
}
