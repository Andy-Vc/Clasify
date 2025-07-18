import { Component, AfterViewInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Usuario } from '../../shared/model/usuario.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AlertService } from '../../util/alert.service';
import { Router } from '@angular/router';
import Swiper from 'swiper';
import { Autoplay } from 'swiper/modules';
import { ThemeService } from '../../shared/services/theme.service';
Swiper.use([Autoplay]);

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  styleUrls: ['register.component.css'],
})
export class RegisterComponent implements AfterViewInit {
  ngAfterViewInit() {
    new Swiper('.mySwiper', {
      loop: true,
      autoplay: {
        delay: 4000,
        disableOnInteraction: false,
      },
    });
  }
  nombre = '';
  apellido = '';
  email = '';
  contrasenia = '';

  constructor(
    private AuthService: AuthService,
    private router: Router,
    private themeService: ThemeService
  ) {}
  darkMode = false;
  toggleDarkMode(): void {
    this.themeService.toggleDarkMode();
  }
  onRegister() {
    const usuario: Usuario = {
      nombreUsuario: this.nombre,
      apellidoUsuario: this.apellido,
      email: this.email,
      contrasenia: this.contrasenia,
    };

    this.AuthService.registrar(usuario).subscribe({
      next: (response) => {
        AlertService.success(response.mensaje);
        console.log('Registro exitoso:', response);
        this.router.navigate(['/login']);
      },
      error: (error) => {
        AlertService.error('Error en el registro:', error);
      },
    });
  }
  onLogin() {
    this.router.navigate(['/auth/login']);
  }

  mostrarPassword: boolean = false;

  togglePassword() {
    this.mostrarPassword = !this.mostrarPassword;
  }
}
