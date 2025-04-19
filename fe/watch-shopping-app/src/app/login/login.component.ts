import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  showErrorMessage: boolean = false;

  constructor(private router: Router, private http: HttpClient, private userService: UserService) {}

  loginFormSubmit(loginForm: NgForm) {
    console.log('LogIn');
    if (loginForm.valid) {
      console.log('IsValid');
      const username = loginForm.value.username;
      
      this.http.get<any>(`http://localhost:8080/getUser?name=${username}`)
        .subscribe(
          (response) => {
            if (response) {
              console.log('Send');
              this.router.navigate(['/home']);
              this.showErrorMessage = false;
              this.userService.setUser(response);
            } else {
              // TODO
            console.log('Empty');

              this.showErrorMessage = true;
            }
          },
          (error) => {
            console.error('Error fetching user:', error);
            // Handle error, e.g., display an error message
          }
        );
    }
  }

  navigateToRegister() {
    this.router.navigate(['/register']);
    this.showErrorMessage = false;
  }
  
}