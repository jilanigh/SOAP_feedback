package com.feedback.service;

import com.feedback.model.Feedback;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService(name = "FeedbackService", targetNamespace = "http://feedback.com/service")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface FeedbackService {

    @WebMethod
    @WebResult(name = "feedback", targetNamespace = "http://feedback.com/service")
    Feedback submitFeedback(
        @WebParam(name = "userId", targetNamespace = "http://feedback.com/service") Long userId,
        @WebParam(name = "productId", targetNamespace = "http://feedback.com/service") Long productId,
        @WebParam(name = "rating", targetNamespace = "http://feedback.com/service") Integer rating,
        @WebParam(name = "comment", targetNamespace = "http://feedback.com/service") String comment
    );

    @WebMethod
    @WebResult(name = "feedback")
    List<Feedback> getFeedbacksByProduct(
        @WebParam(name = "productId") Long productId
    );

    @WebMethod
    @WebResult(name = "feedback")
    List<Feedback> getFeedbacksByUser(
        @WebParam(name = "userId") Long userId
    );

    @WebMethod
    @WebResult(name = "averageRating")
    Double getAverageRating(
        @WebParam(name = "productId") Long productId
    );
} 