package com.example.demo.controller;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.entity.Feedback;
import com.example.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = {"/addNewFeedback"})
    public void addNewFeedback(@RequestBody FeedbackDTO dto) {

        try {
            Feedback feedback = dto.generateFeedback();
            feedbackService.saveFeedback(feedback);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping({"/getAllFeedbacks"})
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

}
