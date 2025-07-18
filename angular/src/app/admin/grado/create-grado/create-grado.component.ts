import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { GradoService } from '../../service/grado.service';
import { Grado } from '../../../shared/model/grado.model';
import { AlertService } from '../../../util/alert.service';

@Component({
  selector: 'app-create-grado',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './create-grado.component.html',
})
export class CreateGradoComponent implements OnInit {
  gradoForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private gs: GradoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.gradoForm = this.fb.group({
      nombreGrado: ['', Validators.required],
    });
  }

  guardar() {
    if (this.gradoForm.valid) {
      const crearGrado: Grado = this.gradoForm.value;

      this.gs.createGrado(crearGrado).subscribe({
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
          AlertService.error(err.mensaje || 'Error al crear el grado');
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
