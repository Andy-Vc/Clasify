import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProfesorService } from '../../../servicio/profesor.service';
import { Usuario } from '../../../model/usuario.model';
import { AlertService } from '../../../util/alert.service';

@Component({
  selector: 'app-create-profesor',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './create-profesor.component.html',
})
export class CreateProfesorComponent implements OnInit {
  profesorForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private ps: ProfesorService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.profesorForm = this.fb.group({
      nombreUsuario: ['', Validators.required],
      apellidoUsuario: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      contrasenia: ['', Validators.required],
    });
  }

  guardar() {
    if (this.profesorForm.valid) {
      const crearProfesor: Usuario = this.profesorForm.value;

      this.ps.create(crearProfesor).subscribe({
        next: (res) => {
          if (res.valor) {
            AlertService.success(res.mensaje);
            this.router.navigate(['../'], { relativeTo: this.route });
          } else {
            AlertService.error(res.mensaje);
          }
        },
        error: (err) => {
          console.error('Server error', err);
          AlertService.error(err.mensaje || 'Error al crear profesor');
        },
      });
    } else {
      AlertService.error('Por favor, completa todos los campos obligatorios');
    }
  }

  list() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
}
