package com.feedback.service.impl;

import com.feedback.model.Feedback;
import com.feedback.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@WebService(endpointInterface = "com.feedback.service.FeedbackService",
            targetNamespace = "http://feedback.com/service")
public class FeedbackServiceImpl implements FeedbackService {

    private final Map<Long, Feedback> feedbacks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Feedback submitFeedback(Long userId, Long productId, Integer rating, String comment) {
        Feedback feedback = new Feedback();
        feedback.setId(idGenerator.getAndIncrement());
        feedback.setUserId(userId);
        feedback.setProductId(productId);
        feedback.setRating(rating);
        if (comment != null && !comment.trim().isEmpty()) {
            feedback.setComment(comment);
        }
        feedback.setCreatedAt(new Date());

        feedbacks.put(feedback.getId(), feedback);
        return feedback;
    }

    @Override
    public List<Feedback> getFeedbacksByProduct(Long productId) {
        return feedbacks.values().stream()
                .filter(f -> f.getProductId() != null && f.getProductId().equals(productId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Feedback> getFeedbacksByUser(Long userId) {
        return feedbacks.values().stream()
                .filter(f -> f.getUserId() != null && f.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageRating(Long productId) {
        List<Feedback> productFeedbacks = getFeedbacksByProduct(productId);
        if (productFeedbacks.isEmpty()) {
            return 0.0;
        }
        
        return productFeedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);
    }
} 