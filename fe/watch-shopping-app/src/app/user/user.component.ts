// user.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/user.service'; // Adjust the path accordingly

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User = new User(); // Initialize with default values

  constructor(private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    // Retrieve the current user from the service
    this.user = this.userService.getCurrentUser();
  }

  goBack(): void {
    // Navigate back to the homepage
    this.router.navigate(['/home']);
  }
}