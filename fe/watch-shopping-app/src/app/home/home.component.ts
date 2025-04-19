import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user.service';
import { User } from 'src/app/models/User';
import { HttpClient } from '@angular/common/http';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  currentUser: User | null = null;

  constructor(private userService: UserService, private router: Router, private http: HttpClient, private cartService: CartService) {}

  watches: { imageUrl: string, name: string }[] = [];

  getWatchesFromDb() {
    this.http.get<any>(`http://localhost:8080/getAllProducts`)
    .subscribe(
      (response) => {
        if (response) {
          console.log(response);
          this.watches = response.map((watch: { image: { url: any; }; name: any; }) => ({
            imageUrl: watch.image.url,
            name: watch.name,
          }));
        } else {
          console.log("hey");
        }
      },
      (error) => {
        console.log("hey");
      }
    );
  }

  ngOnInit(): void {
    // Retrieve the current user information from the service
    this.currentUser = this.userService.getCurrentUser();
    // this.watches = 
    this.getWatchesFromDb()
  }

  addToCart(productName: any): void {
    const json = { productName: productName, nr: 1, username: this.userService.getCurrentUser().username };
    this.cartService.addToCart(productName, json);
  }

  showProduct(productName: any): void {
    console.log("Meowww");
    this.router.navigate(['/product', productName]);
  } 

  goToLogin() {
      this.router.navigate(['/']);
    }

  goToFeedback() {
    this.router.navigate(['/feedback']);
  }

  goToUser() {
    this.router.navigate(['/user']);
  }

  goToCart() {
    this.router.navigate(['/cart']);
  }

  goToAddProduct(): void {
      this.router.navigate(['/addproduct']);
    }

}
