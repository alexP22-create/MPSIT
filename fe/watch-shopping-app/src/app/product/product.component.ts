// product.component.ts
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {

  name: string = '';
  description: string = '';
  imageUrl: string = '';
  price: number = 0.0;
  deliveryTime: number = 0;
  nrInStock: number = 0;

  showEditForm = false;
  editPrice: number = 0;
  editNrInStock: number = 0;
  editDeliveryTime: number = 0;
  editDescription: string = '';

  sendForm: boolean = false;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    this.sendForm = false;
    // Retrieve the product name from the route parameter
    this.route.paramMap.subscribe((params) => {
      this.name = params.get('name') || '';
    });

    this.http.get<any>(`http://localhost:8080/getProduct?name=${this.name}`)
        .subscribe(
          (response) => {
            if (response) {
              this.description = response.description;
              this.imageUrl = response.image.url;
              this.price = response.price;
              this.deliveryTime = response.deliveryTime;
              this.nrInStock = response.nrInStock;
            } else {
            console.log('Empty');
            }
          },
          (error) => {
            console.error('Error fetching user:', error);
            // Handle error, e.g., display an error message
          }
        );
  }

  updateProductInfo(updateProductForm: NgForm) {
    this.sendForm = true;
    if (updateProductForm.valid) {
      const json = { 
        name: this.name,
        price: this.editPrice,
        deliveryTime: this.editDeliveryTime,
        description: this.editDescription,
        nrInStock: this.editNrInStock
        };
      this.http.put<any>(`http://localhost:8080/updateProduct`, json)
      .subscribe(
        (response) => {
          if (response) {
            console.log("Good");
          } else {
          console.log('Empty');
          }
        },
        (error) => {
          console.error('Error fetching user:', error);
          // Handle error, e.g., display an error message
        }
      );
    }
  }

  toggleEditForm() {
    this.showEditForm = !this.showEditForm;
  }

}
