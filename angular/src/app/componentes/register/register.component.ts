import { Component, AfterViewInit } from '@angular/core';
import { AuthService } from '../../servicio/auth.service';
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

  constructor(private AuthService: AuthService, private router: Router) {}

  onRegister() {
    const usuario: Usuario = {
      nombreUsuario: this.nombre,
      apellidoUsuario: this.apellido,
      email: this.email,
      contrasenia: this.contrasenia,
    };

    this.AuthService.registrar(usuario).subscribe({
      next: (response) => {
        AlertService.success(
          `Registro exitoso, ${usuario.nombreUsuario} ${usuario.apellidoUsuario}`
        );
        console.log('Registro exitoso:', response);
        this.router.navigate(['/login']);
      },
      error: (error) => {
        AlertService.error('Error en el registro:', error);
      },
    });
  }
  onLogin() {
    this.router.navigate(['/login']);
  }

  mostrarPassword: boolean = false;
  
  togglePassword() {
    this.mostrarPassword = !this.mostrarPassword;
  }
}
