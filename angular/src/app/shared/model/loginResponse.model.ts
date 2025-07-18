import { UsuarioDTO } from './usuarioDTO.model';

export interface LoginResponse {
  token: string;
  usuario: UsuarioDTO;
}
