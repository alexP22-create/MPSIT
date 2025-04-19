// feedback.component.ts
import { Component } from '@angular/core';
import { FeedbackService } from 'src/app/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {
  myFeedback: string = '';
  feedbackList: string[] = [];
  // see: boolean = true;

  constructor(private feedbackService: FeedbackService) {}

  sendFeedback(): void {
    if (this.myFeedback.trim() !== '') {
      this.feedbackService.addFeedback(this.myFeedback);
    }
  }

  seeFeedback(): void {
    // this.see = true;
    this.feedbackList = this.feedbackService.getFeedbackList();
    console.log(this.feedbackList);
  }
}
