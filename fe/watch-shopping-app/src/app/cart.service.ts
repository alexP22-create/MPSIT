import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class CartService {

  cartItems: string[] = [];
  totalPrice: number = 0;
  expectedDeliveryTime: number = 0;

  constructor(private http: HttpClient, private userService: UserService) {}

  addToCart(itemName: string, json: any): void {
    this.cartItems.push(itemName);
    console.log(json)
    this.http.post<any>('http://localhost:8080/addNewProductOrder', json)
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

  getCartItemNames(): string[] {
    return this.cartItems;
  }

  getCartitemsFromDb(): any {
    this.http.get<any>(`http://localhost:8080/getProductOrdersByUsername?username=${this.userService.getCurrentUser().username}`)
      .subscribe(
        (response) => {
          console.log('Registration successful:', response);
          console.log(response);
          this.totalPrice = response.reduce((sum: any, entry: any) => sum + entry.product.price, 0);
          console.log(this.totalPrice);
          this.expectedDeliveryTime = response.reduce((max: any, entry: any) => Math.max(max, entry.product.deliveryTime), -Infinity);
          console.log(this.expectedDeliveryTime);
        },
        (error) => {
          console.error('Error during registration:', error);
          // Handle error, e.g., display an error message
        }
      );
  }

  getPrice(): number {
    return this.totalPrice;
  }

  getDelivery(): number {
    return this.expectedDeliveryTime;
  }

}
