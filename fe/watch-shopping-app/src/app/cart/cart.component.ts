// cart.component.ts
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  totalPrice: number = 0;
  expectedDeliveryTime: number = 0;

  phoneNumber: string = '';
  address: string = '';

  commandRegistered: boolean = false;

  cartItems: string[] = [];

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.commandRegistered = false;
    this.cartItems = this.cartService.getCartItemNames();
    this.cartService.getCartitemsFromDb();
    this.totalPrice = this.cartService.getPrice();
    this.expectedDeliveryTime = this.cartService.getDelivery();
  }

  checkout() {
    this.commandRegistered = true;
  }

}
