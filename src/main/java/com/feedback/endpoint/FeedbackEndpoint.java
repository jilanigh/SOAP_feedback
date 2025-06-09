package com.feedback.endpoint;

import com.feedback.model.Feedback;
import com.feedback.model.SubmitFeedbackRequest;
import com.feedback.model.SubmitFeedbackResponse;
import com.feedback.model.GetFeedbacksByProductRequest;
import com.feedback.model.GetFeedbacksByProductResponse;
import com.feedback.model.GetFeedbacksByUserRequest;
import com.feedback.model.GetFeedbacksByUserResponse;
import com.feedback.model.GetAverageRatingRequest;
import com.feedback.model.GetAverageRatingResponse;
import com.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.List;

@Endpoint
public class FeedbackEndpoint {

    private static final String NAMESPACE_URI = "http://feedback.com/service";

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackEndpoint(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitFeedbackRequest")
    @ResponsePayload
    public SubmitFeedbackResponse submitFeedback(@RequestPayload SubmitFeedbackRequest request) {
        Feedback feedback = feedbackService.submitFeedback(
            request.getUserId(),
            request.getProductId(),
            request.getRating(),
            request.getComment()
        );
        SubmitFeedbackResponse response = new SubmitFeedbackResponse();
        response.setFeedback(feedback);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFeedbacksByProductRequest")
    @ResponsePayload
    public GetFeedbacksByProductResponse getFeedbacksByProduct(@RequestPayload GetFeedbacksByProductRequest request) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByProduct(request.getProductId());
        GetFeedbacksByProductResponse response = new GetFeedbacksByProductResponse();
        response.setFeedback(feedbacks);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFeedbacksByUserRequest")
    @ResponsePayload
    public GetFeedbacksByUserResponse getFeedbacksByUser(@RequestPayload GetFeedbacksByUserRequest request) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByUser(request.getUserId());
        GetFeedbacksByUserResponse response = new GetFeedbacksByUserResponse();
        response.setFeedback(feedbacks);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAverageRatingRequest")
    @ResponsePayload
    public GetAverageRatingResponse getAverageRating(@RequestPayload GetAverageRatingRequest request) {
        Double avg = feedbackService.getAverageRating(request.getProductId());
        GetAverageRatingResponse response = new GetAverageRatingResponse();
        response.setAverageRating(avg);
        return response;
    }
} 