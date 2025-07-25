export interface NotaDTO{
    idNota?:number;
    idCurso?:string;
    idUsuario?:number;
    nroNota: number;
    calificacion: number;
    comentario: string;
    fechaRegistro?: Date;
}