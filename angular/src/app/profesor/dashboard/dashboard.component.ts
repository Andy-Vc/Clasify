import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin/service/admin.service';
import { UsuarioDTO } from '../../shared/model/usuarioDTO.model';
import { ProfesorService } from '../service/curso.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone:true
})
export class DashboardComponent implements OnInit{
  usuario!: UsuarioDTO;

  constructor(private profesorService: ProfesorService ) { 
  }

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuario');
    if (usuarioGuardado) {
      this.usuario = JSON.parse(usuarioGuardado);
    }
  }
}
