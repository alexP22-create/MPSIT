package com.example.demo.dto;

import com.example.demo.entity.Feedback;
import lombok.Data;

@Data
public class FeedbackDTO {
    private String comment;
    public Feedback generateFeedback() {
        return new Feedback(comment);
    }
}
