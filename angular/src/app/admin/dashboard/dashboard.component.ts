import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../service/admin.service';
import { BaseChartDirective } from 'ng2-charts';
import { dashboardResponse } from '../../shared/model/dashboardResponse.model';
import { UsuarioDTO } from '../../shared/model/usuarioDTO.model';
import { Chart, ChartData, ChartOptions, registerables } from 'chart.js';
import { Router } from '@angular/router';

Chart.register(...registerables);

@Component({
  standalone: true,
  selector: 'app-dashboard',
  imports: [CommonModule, BaseChartDirective],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {
  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  dataInfo!: dashboardResponse;
  usuario!: UsuarioDTO;
  ultimosUsuarios!: UsuarioDTO[];

  public barChartData: ChartData<'bar'> = {
    labels: [],
    datasets: [{ data: [], label: 'Estudiantes' }],
  };

  public barChartType: 'bar' = 'bar';

  public get barChartOptions(): ChartOptions<'bar'> {
    const dark = this.isDarkMode;
    return {
      responsive: true,
      scales: {
        x: {
          ticks: {
            color: dark ? '#fff' : '#000',
            precision: 0,
          },
          grid: {
            color: dark ? 'rgba(255,255,255,0.2)' : 'rgba(0,0,0,0.1)',
          },
        },
        y: {
          beginAtZero: true,
          ticks: {
            color: dark ? '#fff' : '#000',
            precision: 0,
          },
          grid: {
            color: dark ? 'rgba(255,255,255,0.2)' : 'rgba(0,0,0,0.1)',
          },
        },
      },
      plugins: {
        legend: {
          labels: {
            color: dark ? '#fff' : '#000',
          },
        },
      },
    };
  }

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    const usuarioGuardado = localStorage.getItem('usuario');
    if (usuarioGuardado) {
      this.usuario = JSON.parse(usuarioGuardado);
    }
    this.onDashboard();
  }

  onDashboard() {
    this.adminService.getDashboard().subscribe({
      next: (data) => {
        this.dataInfo = data;
        if (this.dataInfo.estudiantesPorCurso) {
          this.barChartData.labels = this.dataInfo.estudiantesPorCurso.map(
            (e) => e.nombreCurso
          );
          this.barChartData.datasets[0].data =
            this.dataInfo.estudiantesPorCurso.map((e) => e.cantidadEstudiantes);
        }
        if (this.dataInfo.estudiantesRecientes) {
          this.ultimosUsuarios = this.dataInfo.estudiantesRecientes;
        }
      },
      error: (err) => {
        console.error('Error al cargar informacion', err);
      },
    });
  }
  get isDarkMode() {
    return document.body.classList.contains('dark-mode');
  }

  getCrearCurso() {
    this.router.navigate(['admin/curso/crear']);
  }

  getCrearProfesor() {
    this.router.navigate(['admin/profesor/crear']);
  }
  
  getCrearGrado() {
    this.router.navigate(['admin/grado/crear']);
  }
}
