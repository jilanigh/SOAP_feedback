package com.feedback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "feedback"
})
@XmlRootElement(name = "submitFeedbackResponse", namespace = "http://feedback.com/service")
public class SubmitFeedbackResponse {
    
    @XmlElement(name = "feedback", namespace = "http://feedback.com/service", required = true)
    private Feedback feedback;

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
} 