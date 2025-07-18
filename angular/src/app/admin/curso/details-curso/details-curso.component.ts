import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CursoService } from '../../service/curso.service';
import { CursoEstudiantesDTO } from '../../../shared/model/cursoEstudiantesDTO.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-details-curso',
  imports: [CommonModule],
  templateUrl: './details-curso.component.html',
})
export class DetailsCursoComponent implements OnInit {
  loading = true;
  curso?: CursoEstudiantesDTO;
  idCurso!: string;

  constructor(
    private route: ActivatedRoute,
    private cursoService: CursoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.idCurso = params['id'];
      this.cargarCurso();
    });
  }

  cargarCurso(): void {
    this.loading = true;
    this.cursoService.getCursoStudents(this.idCurso).subscribe({
      next: (data) => {
        this.curso = data;
        this.loading = false;
        console.log('Curso cargado:', data);
      },
      error: (err) => {
        console.error('Error al cargar el curso:', err);
        this.loading = false;
      },
    });
  }
  get hayEstudiantes(): boolean {
    return !!this.curso?.estudiantes?.length;
  }

  list() {
    console.log('Ruta actual:', this.route.snapshot.url);
  console.log('Ruta completa:', this.route.snapshot);
    this.router.navigate(['../../'], { relativeTo: this.route });
  }
}
