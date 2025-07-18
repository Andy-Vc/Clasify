import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../service/admin.service';
import { dashboardResponse } from '../../shared/model/dashboardResponse.model';
import { UsuarioDTO } from '../../shared/model/usuarioDTO.model';

@Component({
  standalone: true,
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  dataInfo!: dashboardResponse;
  usuario!: UsuarioDTO;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuario');
    if (usuarioGuardado) {
      this.usuario = JSON.parse(usuarioGuardado);
    }
    
    this.onDashboard();
  }

  onDashboard(){
    this.adminService.getDashboard().subscribe({
      next: (data) => {
        this.dataInfo = data;
      },
      error: (err) => {
        console.error('Error al cargar informacion', err);
      }
    });
  }

}
