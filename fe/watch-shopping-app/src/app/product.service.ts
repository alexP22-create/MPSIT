import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'your_backend_api_url'; // Replace with your actual backend API URL

  constructor(private http: HttpClient) {}

  addProduct(productData: any): void {
    this.http.post<any>('http://localhost:8080/addNewProduct', productData)
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
