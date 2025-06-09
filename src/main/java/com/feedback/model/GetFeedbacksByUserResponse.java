package com.feedback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "feedback"
})
@XmlRootElement(name = "getFeedbacksByUserResponse", namespace = "http://feedback.com/service")
public class GetFeedbacksByUserResponse {
    
    @XmlElement(name = "feedback", namespace = "http://feedback.com/service", required = true)
    private List<Feedback> feedback;

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }
} 