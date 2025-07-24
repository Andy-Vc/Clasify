import { Component } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterLinkWithHref,
  RouterOutlet,
} from '@angular/router';
import { ThemeService } from '../../shared/services/theme.service';
import { AuthService } from '../../auth/service/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profesor-layout',
  templateUrl: './profesor-layout.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
    RouterLinkWithHref,
    RouterLinkActive
  ],
  styleUrls: ['profesor-layout.component.css'],
})
export class ProfesorLayoutComponent {
  darkMode = false;
  constructor(
    private router: Router,
    private authService: AuthService,
    public themeService: ThemeService
  ) {}
  toggleDarkMode(): void {
    this.themeService.toggleDarkMode();
  }
  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
