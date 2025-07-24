import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Usuario } from '../../shared/model/usuario.model';
import { AlertService } from '../../util/alert.service';
import { Router } from '@angular/router';
import Swiper from 'swiper';
import { Autoplay } from 'swiper/modules';
import { ThemeService } from '../../shared/services/theme.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

Swiper.use([Autoplay]);

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  styleUrls: ['register.component.css'],
})
export class RegisterComponent implements OnInit, AfterViewInit {
  registerForm!: FormGroup;
  mostrarPassword = false;
  darkMode = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private themeService: ThemeService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nombreUsuario: ['', Validators.required],
      apellidoUsuario: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contrasenia: ['', Validators.required],
    });
  }

  ngAfterViewInit(): void {
    new Swiper('.mySwiper', {
      loop: true,
      autoplay: {
        delay: 4000,
        disableOnInteraction: false,
      },
    });
  }

  togglePassword(): void {
    this.mostrarPassword = !this.mostrarPassword;
  }

  toggleDarkMode(): void {
    this.themeService.toggleDarkMode();
  }

  onRegister(): void {
    if (this.registerForm.invalid) {
      AlertService.error('Completa todos los campos correctamente.');
      this.registerForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    const usuario: Usuario = this.registerForm.value;

    this.authService.registrar(usuario).subscribe({
      next: (res) => {
        this.loading = false;
        AlertService.success(res.mensaje);
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.loading = false;
        AlertService.error(err?.mensaje || 'Error en el registro.');
      }
    });
  }

  onLogin(): void {
    this.router.navigate(['/auth/login']);
  }
}