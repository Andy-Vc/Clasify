import { Component, AfterViewInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { AutenticarUsuario } from '../../shared/model/autenticarUsuario.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AlertService } from '../../util/alert.service';
import { Router } from '@angular/router';
import Swiper from 'swiper';
import { Autoplay } from 'swiper/modules';
import { LoginResponse } from '../../shared/model/loginResponse.model';
import { ThemeService } from '../../shared/services/theme.service';
Swiper.use([Autoplay]);

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  styleUrls: ['login.component.css'],
})
export class LoginComponent implements AfterViewInit {
  ngAfterViewInit() {
    new Swiper('.mySwiper', {
      loop: true,
      autoplay: {
        delay: 4000,
        disableOnInteraction: false,
      },
    });
  }
  email = '';
  contrasenia = '';

  constructor(private AuthService: AuthService, private router: Router, private themeService: ThemeService) {}
darkMode = false;
  onLogin() {
    const credentials: AutenticarUsuario = {
      email: this.email,
      contrasenia: this.contrasenia,
    };

    this.AuthService.login(credentials).subscribe({
      next: (response: LoginResponse) => {
        const usuario = response.usuario;
        const token = response.token;

        localStorage.setItem('token', token);
        localStorage.setItem('usuario', JSON.stringify(usuario));
        console.log('Token guardado:', token);
        AlertService.success(
          `Bienvenido, ${usuario.nombreUsuario} ${usuario.apellidoUsuario}`,
          'Login exitoso'
        );

        switch (usuario.rol) {
          case 'A':
            this.router.navigate(['/admin/dashboard']);
            break;
          case 'E':
            this.router.navigate(['/estudiante/dashboard']);
            break;
          case 'P':
            this.router.navigate(['/profesor/dashboard']);
            break;
          default:
            this.router.navigate(['/']);
            break;
        }
      },

      error: (error) => {
        console.error('Error', error);
        AlertService.error(error.error || 'Credenciales inválidas', 'Error');
      },
    });
  }
toggleDarkMode(): void {
  this.themeService.toggleDarkMode();
}
  onRegister() {
    this.router.navigate(['/auth/register']);
  }

  mostrarPassword: boolean = false;

  togglePassword() {
    this.mostrarPassword = !this.mostrarPassword;
  }
}
