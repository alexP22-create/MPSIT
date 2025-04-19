package com.example.demo.unit_tests.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.entity.Feedback;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFeedbacks() {
        List<Feedback> feedbackList = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        List<Feedback> result = feedbackService.getAllFeedbacks();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    public void testSaveFeedback() {
        Feedback feedback = new Feedback("value1");

        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback result = feedbackService.saveFeedback(feedback);
        assertNotNull(result);
        assertEquals("value1", result.getComment());
        verify(feedbackRepository, times(1)).save(feedback);
    }
}
