import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/User'; // Import your User model

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUser: User;

  constructor( private http: HttpClient) {
    this.currentUser = new User();
  }

  getCurrentUser(): User {
        return this.currentUser;
  }

  setUser(getResponse: any): void {
    this.currentUser.username = getResponse.username;
    this.currentUser.email = getResponse.email;
    this.currentUser.favoriteWatch = getResponse.favoriteWatch;
    this.currentUser.aboutMe = getResponse.aboutMe;
    this.currentUser.myBudget = getResponse.myBudget;
    this.currentUser.role = getResponse.role.role;
  }

}
