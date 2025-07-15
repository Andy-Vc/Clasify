export class Usuario{
    idUsuario? :number;
    nombreUsuario?: string;
    apellidoUsuario?: string;
    email?:string;
    contrasenia?:string;
    rol?:string;
    flgEliminado?:boolean;
    cursosInscritos?: any[];
    rolDescripcion?:string;
}