import { Component, AfterViewInit } from '@angular/core';
import { AuthService } from '../../servicio/auth.service';
import { AutenticarUsuario } from '../../dto/autenticarUsuario.model';
import { Usuario } from '../../model/usuario.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AlertService } from '../../util/alert.service';
import { Router } from '@angular/router';
import Swiper from 'swiper';
import { Autoplay } from 'swiper/modules';
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

  constructor(private AuthService: AuthService, private router: Router) {}

  onLogin() {
    const credentials: AutenticarUsuario = {
      email: this.email,
      contrasenia: this.contrasenia,
    };

    this.AuthService.login(credentials).subscribe({
      next: (usuario: Usuario) => {
        console.log('Usuario logueado:', usuario);
        AlertService.success(
          `Bienvenido, ${usuario.nombreUsuario} ${usuario.apellidoUsuario}`,
          'Login exitoso'
        );
        switch (usuario.rol) {
          case 'A':
            console.log('Redirigiendo a: /admin/dashboard');
            this.router.navigate(['/admin/dashboard']);
            break;
          case 'E':
            console.log('Redirigiendo a: /estudiante/dashboard');
            this.router.navigate(['/estudiante/dashboard']);
            break;
          case 'P':
            console.log('Redirigiendo a: /profesor/dashboard');
            this.router.navigate(['/profesor/dashboard']);
            break;
          default:
            console.log('Redirigiendo a: /');
            this.router.navigate(['/']);
            break;
        }
      },
      error: (error) => {
        console.error('Error', error);
        AlertService.error(
          error.error || 'Credenciales inválidas',
          'Error'
        );
      },
    });
  }

  onRegister() {
    this.router.navigate(['/register']);
  }

  mostrarPassword: boolean = false;

  togglePassword() {
    this.mostrarPassword = !this.mostrarPassword;
  }
}
