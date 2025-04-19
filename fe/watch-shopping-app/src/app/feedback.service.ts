import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private feedbackList: string[] = [];

  constructor(private http: HttpClient) {}

  addFeedback(feedback: string): void {
    const json = { comment: feedback };

    this.http.post<any>(`http://localhost:8080/addNewFeedback`, json)
    .subscribe(
      (response) => {
        if (response) {
          console.log("hey");
        } else {
          console.log("hey");
        }
      },
      (error) => {
        console.log("hey");
      }
    );

    // this.feedbackList = this.getFeedbackList();
  }

  getFeedbackList(): string[] {
    this.http.get<any>(`http://localhost:8080/getAllFeedbacks`)
    .subscribe(
      (response) => {
        if (response) {
          this.feedbackList = response.map((entry: { comment: any; }) => entry.comment);
        } else {
          console.log("hey");
        }
      },
      (error) => {
        console.log("hey");
      }
    );

    return this.feedbackList;
  }
}
