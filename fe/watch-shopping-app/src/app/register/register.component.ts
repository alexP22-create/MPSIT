import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})

export class RegisterComponent {
  showSuccessMessage: boolean = false;
  constructor(private router: Router, private http: HttpClient) {}

  register(registerForm: NgForm) {
    this.showSuccessMessage = true;
    console.log("la")
    if (registerForm.valid) {
      const json = { ...registerForm.value };
      console.log(json)
      this.http.post<any>('http://localhost:8080/addNewUser', json)
        .subscribe(
          (response) => {
            console.log('Registration successful:', response);
          },
          (error) => {
            console.error('Error during registration:', error);
            // Handle error, e.g., display an error message
          }
        );
    }
  }

  returnToLogin() {
    this.router.navigate(['/']);
    this.showSuccessMessage = false;
  }

}
